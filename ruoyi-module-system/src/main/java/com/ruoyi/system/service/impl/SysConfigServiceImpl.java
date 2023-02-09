package com.ruoyi.system.service.impl;

import cn.hutool.core.convert.Convert;
import cn.hutool.core.util.ObjectUtil;
import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ruoyi.common.constant.CacheNames;
import com.ruoyi.common.constant.UserConstants;
import com.ruoyi.common.core.domain.bo.PageQuery;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.core.service.ConfigService;
import com.ruoyi.common.exception.ServiceException;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.utils.redis.CacheUtils;
import com.ruoyi.system.domain.SysConfig;
import com.ruoyi.system.domain.bo.SysConfigQueryBo;
import com.ruoyi.system.domain.vo.SysConfigVo;
import com.ruoyi.system.mapper.SysConfigMapper;
import com.ruoyi.system.service.ISysConfigService;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.Arrays;
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
    @Cacheable(cacheNames = CacheNames.SYS_CONFIG, key = "#configKey")
    @Override
    public String selectConfigByKey(String configKey) {
        SysConfig retConfig = baseMapper.selectOne(new LambdaQueryWrapper<SysConfig>()
            .eq(SysConfig::getConfigKey, configKey));
        if (ObjectUtil.isNotNull(retConfig)) {
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
    public boolean selectCaptchaEnabled() {
        String captchaEnabled = selectConfigByKey("sys.account.captchaEnabled");
        if (StringUtils.isEmpty(captchaEnabled)) {
            return true;
        }
        return Convert.toBool(captchaEnabled);
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
    @CachePut(cacheNames = CacheNames.SYS_CONFIG, key = "#config.configKey")
    @Override
    public String insertConfig(SysConfig config) {
        int row = baseMapper.insert(config);
        if (row > 0) {
            return config.getConfigValue();
        }
        throw new ServiceException("操作失败");
    }

    /**
     * 修改参数配置
     *
     * @param config 参数配置信息
     * @return 结果
     */
    @CachePut(cacheNames = CacheNames.SYS_CONFIG, key = "#config.configKey")
    @Override
    public String updateConfig(SysConfig config) {
        // * fix 修复 修改参数键名时 未移除过期缓存配置
        int row = 0;
        if (config.getConfigId() != null) {
            // fix 修复 根据key更新参数配置报null问题
            SysConfig temp = baseMapper.selectById(config.getConfigId());
            if (!StringUtils.equals(temp.getConfigKey(), config.getConfigKey())) {
                CacheUtils.evict(CacheNames.SYS_CONFIG, temp.getConfigKey());
            }
            row = baseMapper.updateById(config);
        } else {
            row = baseMapper.update(config, new LambdaQueryWrapper<SysConfig>()
                    .eq(SysConfig::getConfigKey, config.getConfigKey()));
        }
        if (row > 0) {
            return config.getConfigValue();
        }
        throw new ServiceException("操作失败");
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
            CacheUtils.evict(CacheNames.SYS_CONFIG, config.getConfigKey());
        }
        baseMapper.deleteBatchIds(Arrays.asList(configIds));
    }

    /**
     * 加载参数缓存数据
     */
    @Override
    public void loadingConfigCache() {
        List<SysConfig> configsList = selectConfigList(new SysConfigQueryBo());
        configsList.forEach(config ->
                CacheUtils.put(CacheNames.SYS_CONFIG, config.getConfigKey(), config.getConfigValue()));
    }

    /**
     * 清空参数缓存数据
     */
    @Override
    public void clearConfigCache() {
        CacheUtils.clear(CacheNames.SYS_CONFIG);
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
        long configId = ObjectUtil.isNull(config.getConfigId()) ? -1L : config.getConfigId();
        SysConfig info = baseMapper.selectOne(new LambdaQueryWrapper<SysConfig>().eq(SysConfig::getConfigKey, config.getConfigKey()));
        if (ObjectUtil.isNotNull(info) && info.getConfigId() != configId) {
            return UserConstants.NOT_UNIQUE;
        }
        return UserConstants.UNIQUE;
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

}
