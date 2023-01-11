package com.ruoyi.system.service.impl;

import cn.hutool.core.convert.Convert;
import cn.hutool.core.util.ObjectUtil;
import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ruoyi.common.constant.Constants;
import com.ruoyi.common.constant.UserConstants;
import com.ruoyi.common.core.domain.bo.PageQuery;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.core.service.ConfigService;
import com.ruoyi.common.exception.ServiceException;
import com.ruoyi.common.utils.BeanCopyUtils;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.utils.redis.RedisUtils;
import com.ruoyi.system.domain.SysConfig;
import com.ruoyi.system.domain.bo.SysConfigQueryBo;
import com.ruoyi.system.domain.vo.SysConfigVo;
import com.ruoyi.system.mapper.SysConfigMapper;
import com.ruoyi.system.service.ISysConfigService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

/**
 * 参数配置 服务层实现
 *
 * @author ruoyi
 * @author Lion Li
 */
@RequiredArgsConstructor
@Service
public class SysConfigServiceImpl implements ISysConfigService, ConfigService {

    private final SysConfigMapper baseMapper;

    @Override
    public TableDataInfo<SysConfigVo> selectPageConfigList(SysConfigQueryBo configQuery, PageQuery pageQuery) {
        LambdaQueryWrapper<SysConfig> lqw = new LambdaQueryWrapper<SysConfig>()
            .like(configQuery != null && StringUtils.isNotBlank(configQuery.getConfigName()), SysConfig::getConfigName, configQuery.getConfigName())
            .eq(configQuery != null && StringUtils.isNotBlank(configQuery.getConfigType()), SysConfig::getConfigType, configQuery.getConfigType())
            .like(configQuery != null && StringUtils.isNotBlank(configQuery.getConfigKey()), SysConfig::getConfigKey, configQuery.getConfigKey())
            .between(configQuery != null && configQuery.getBeginTime() != null && configQuery.getBeginTime() != null,
                SysConfig::getCreateTime, configQuery.getBeginTime(), configQuery.getEndTime());
        Page<SysConfigVo> page = baseMapper.selectVoPage(pageQuery.build(), lqw, SysConfigVo.class);
        return TableDataInfo.build(page);
    }

    /**
     * 查询参数配置信息
     *
     * @param configId 参数配置ID
     * @return 参数配置信息
     */
    @Override
    @DS("master")
    public SysConfigVo selectConfigById(Long configId) {
        return baseMapper.selectVoById(configId, SysConfigVo.class);
    }

    /**
     * 根据键名查询参数配置信息
     *
     * @param configKey 参数key
     * @return 参数键值
     */
    @Override
    public String selectConfigByKey(String configKey) {
        String configValue = RedisUtils.getCacheObject(getCacheKey(configKey));
        if (StringUtils.isNotEmpty(configValue)) {
            return configValue;
        }
        SysConfig retConfig = baseMapper.selectOne(new LambdaQueryWrapper<SysConfig>()
            .eq(SysConfig::getConfigKey, configKey));
        if (ObjectUtil.isNotNull(retConfig)) {
            RedisUtils.setCacheObject(getCacheKey(configKey), retConfig.getConfigValue());
            return retConfig.getConfigValue();
        }
        return StringUtils.EMPTY;
    }

    /**
     * 获取验证码开关
     *
     * @return true开启，false关闭
     */
    @Override
    public boolean selectCaptchaOnOff() {
        String captchaOnOff = selectConfigByKey("sys.account.captchaOnOff");
        if (StringUtils.isEmpty(captchaOnOff)) {
            return true;
        }
        return Convert.toBool(captchaOnOff);
    }

    /**
     * 查询参数配置列表
     *
     * @param configQuery 参数配置查询对象
     * @return 参数配置集合
     */
    @Override
    public List<SysConfig> selectConfigList(SysConfigQueryBo configQuery) {
        LambdaQueryWrapper<SysConfig> lqw = new LambdaQueryWrapper<SysConfig>()
            .like(configQuery != null && StringUtils.isNotBlank(configQuery.getConfigName()), SysConfig::getConfigName, configQuery.getConfigName())
            .eq(configQuery != null && StringUtils.isNotBlank(configQuery.getConfigType()), SysConfig::getConfigType, configQuery.getConfigType())
            .like(configQuery != null && StringUtils.isNotBlank(configQuery.getConfigKey()), SysConfig::getConfigKey, configQuery.getConfigKey())
            .between(configQuery != null && configQuery.getBeginTime() != null && configQuery.getEndTime() != null,
                SysConfig::getCreateTime, configQuery.getBeginTime(), configQuery.getEndTime());
        return baseMapper.selectList(lqw);
    }

    /**
     * 新增参数配置
     *
     * @param config 参数配置信息
     * @return 结果
     */
    @Override
    public int insertConfig(SysConfig config) {
        int row = baseMapper.insert(config);
        if (row > 0) {
            RedisUtils.setCacheObject(getCacheKey(config.getConfigKey()), config.getConfigValue());
        }
        return row;
    }

    /**
     * 修改参数配置
     *
     * @param config 参数配置信息
     * @return 结果
     */
    @Override
    public int updateConfig(SysConfig config) {
        int row = 0;
        if (config.getConfigId() != null) {
            row = baseMapper.updateById(config);
        } else {
            row = baseMapper.update(config, new LambdaQueryWrapper<SysConfig>()
                .eq(SysConfig::getConfigKey, config.getConfigKey()));
        }
        if (row > 0) {
            RedisUtils.setCacheObject(getCacheKey(config.getConfigKey()), config.getConfigValue());
        }
        return row;
    }

    /**
     * 批量删除参数信息
     *
     * @param configIds 需要删除的参数ID
     */
    @Override
    public void deleteConfigByIds(Long[] configIds) {
        for (Long configId : configIds) {
            SysConfigVo config = selectConfigById(configId);
            if (StringUtils.equals(UserConstants.YES, config.getConfigType())) {
                throw new ServiceException(String.format("内置参数【%1$s】不能删除 ", config.getConfigKey()));
            }
            RedisUtils.deleteObject(getCacheKey(config.getConfigKey()));
        }
        baseMapper.deleteBatchIds(Arrays.asList(configIds));
    }

    /**
     * 加载参数缓存数据
     */
    @Override
    public void loadingConfigCache() {
        List<SysConfig> configsList = selectConfigList(new SysConfigQueryBo());
        for (SysConfig config : configsList) {
            RedisUtils.setCacheObject(getCacheKey(config.getConfigKey()), config.getConfigValue());
        }
    }

    /**
     * 清空参数缓存数据
     */
    @Override
    public void clearConfigCache() {
        Collection<String> keys = RedisUtils.keys(Constants.SYS_CONFIG_KEY + "*");
        RedisUtils.deleteObject(keys);
    }

    /**
     * 重置参数缓存数据
     */
    @Override
    public void resetConfigCache() {
        clearConfigCache();
        loadingConfigCache();
    }

    /**
     * 校验参数键名是否唯一
     *
     * @param config 参数配置信息
     * @return 结果
     */
    @Override
    public String checkConfigKeyUnique(SysConfig config) {
        Long configId = ObjectUtil.isNull(config.getConfigId()) ? -1L : config.getConfigId();
        SysConfig info = baseMapper.selectOne(new LambdaQueryWrapper<SysConfig>().eq(SysConfig::getConfigKey, config.getConfigKey()));
        if (ObjectUtil.isNotNull(info) && info.getConfigId().longValue() != configId.longValue()) {
            return UserConstants.NOT_UNIQUE;
        }
        return UserConstants.UNIQUE;
    }

    @Override
    public SysConfigVo getOne(SysConfigQueryBo configQuery) {
        SysConfig config = BeanCopyUtils.copy(configQuery, SysConfig.class);
        return baseMapper.selectVoOne(new LambdaQueryWrapper<>(config), SysConfigVo.class);
    }

    /**
     * 根据参数 key 获取参数值
     *
     * @param configKey 参数 key
     * @return 参数值
     */
    @Override
    public String getConfigValue(String configKey) {
        return selectConfigByKey(configKey);
    }

    /**
     * 设置cache key
     *
     * @param configKey 参数键
     * @return 缓存键key
     */
    private String getCacheKey(String configKey) {
        return Constants.SYS_CONFIG_KEY + configKey;
    }
}
