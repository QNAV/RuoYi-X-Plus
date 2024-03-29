package com.ruoyi.system.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ruoyi.common.constant.CacheNames;
import com.ruoyi.common.core.domain.bo.PageQuery;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.CommonYesOrNoEnum;
import com.ruoyi.common.exception.ServiceException;
import com.ruoyi.common.utils.JsonUtils;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.utils.redis.CacheUtils;
import com.ruoyi.common.utils.redis.RedisUtils;
import com.ruoyi.common.utils.spring.SpringUtils;
import com.ruoyi.oss.constant.OssConstant;
import com.ruoyi.oss.factory.OssFactory;
import com.ruoyi.system.domain.SysOssConfig;
import com.ruoyi.system.domain.bo.SysOssConfigEditBo;
import com.ruoyi.system.domain.bo.SysOssConfigQueryBo;
import com.ruoyi.system.domain.vo.SysOssConfigVo;
import com.ruoyi.system.mapper.SysOssConfigMapper;
import com.ruoyi.system.service.ISysOssConfigService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.compress.utils.Lists;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.List;

/**
 * 对象存储配置Service业务层处理
 *
 * @author Lion Li
 */
@Slf4j
@RequiredArgsConstructor
@Service
public class SysOssConfigServiceImpl implements ISysOssConfigService {

    private final SysOssConfigMapper baseMapper;

    /**
     * 项目启动时，初始化参数到缓存，加载配置类
     */
    @Override
    public void init() {
        List<SysOssConfig> list = baseMapper.selectList();
        // 加载OSS初始化配置
        for (SysOssConfig config : list) {
            String configKey = config.getConfigKey();
            if ("0".equals(config.getStatus())) {
                RedisUtils.setCacheObject(OssConstant.DEFAULT_CONFIG_KEY, configKey);
            }
            SpringUtils.context().publishEvent(config);
        }
        // 初始化OSS工厂
        OssFactory.init();
    }

    @Override
    public SysOssConfigVo queryById(Long ossConfigId) {
        return baseMapper.selectVoById(ossConfigId);
    }

    @Override
    public TableDataInfo<SysOssConfigVo> queryPageList(SysOssConfigQueryBo ossConfigQuery, PageQuery pageQuery) {
        LambdaQueryWrapper<SysOssConfig> lqw = buildQueryWrapper(ossConfigQuery);
        Page<SysOssConfigVo> result = baseMapper.selectVoPage(pageQuery.build(), lqw);
        return TableDataInfo.build(result);
    }


    private LambdaQueryWrapper<SysOssConfig> buildQueryWrapper(SysOssConfigQueryBo ossConfigQuery) {
        if (ObjectUtil.isNull(ossConfigQuery)){
            ossConfigQuery = new SysOssConfigQueryBo();
        }
        LambdaQueryWrapper<SysOssConfig> lqw = Wrappers.lambdaQuery();
        lqw.eq(StringUtils.isNotBlank(ossConfigQuery.getConfigKey()), SysOssConfig::getConfigKey, ossConfigQuery.getConfigKey());
        lqw.like(StringUtils.isNotBlank(ossConfigQuery.getBucketName()), SysOssConfig::getBucketName, ossConfigQuery.getBucketName());
        lqw.eq(ossConfigQuery.getStatus() != null, SysOssConfig::getStatus, ossConfigQuery.getStatus());
        return lqw;
    }

    @Override
    public Boolean insertByBo(SysOssConfigEditBo bo) {
        SysOssConfig config = BeanUtil.toBean(bo, SysOssConfig.class);
        validEntityBeforeSave(config);
        boolean flag = baseMapper.insert(config) > 0;
        if (flag) {
            SpringUtils.context().publishEvent(config);
        }
        return flag;
    }

    @Override
    public Boolean updateByBo(SysOssConfigEditBo bo) {
        SysOssConfig config = BeanUtil.toBean(bo, SysOssConfig.class);
        validEntityBeforeSave(config);
        LambdaUpdateWrapper<SysOssConfig> luw = new LambdaUpdateWrapper<>();
        luw.set(ObjectUtil.isNull(config.getPrefix()), SysOssConfig::getPrefix, "");
        luw.set(ObjectUtil.isNull(config.getRegion()), SysOssConfig::getRegion, "");
        luw.set(ObjectUtil.isNull(config.getExt1()), SysOssConfig::getExt1, "");
        luw.set(ObjectUtil.isNull(config.getRemark()), SysOssConfig::getRemark, "");
        luw.eq(SysOssConfig::getOssConfigId, config.getOssConfigId());
        boolean flag = baseMapper.update(config, luw) > 0;
        if (flag) {
            SpringUtils.context().publishEvent(config);
        }
        return flag;
    }

    /**
     * 保存前的数据校验
     */
    private void validEntityBeforeSave(SysOssConfig entity) {
        if (StringUtils.isNotEmpty(entity.getConfigKey())
            && CommonYesOrNoEnum.NO.equals(checkConfigKeyUnique(entity))) {
            throw new ServiceException("操作配置'" + entity.getConfigKey() + "'失败, 配置key已存在!");
        }
    }

    @Override
    public Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid) {
        if (isValid) {
            if (CollUtil.containsAny(ids, OssConstant.SYSTEM_DATA_IDS)) {
                throw new ServiceException("系统内置, 不可删除!");
            }
        }
        List<SysOssConfig> list = Lists.newArrayList();
        for (Long configId : ids) {
            SysOssConfig config = baseMapper.selectById(configId);
            list.add(config);
        }
        boolean flag = baseMapper.deleteBatchIds(ids) > 0;
        if (flag) {
            list.forEach(sysOssConfig ->
                    CacheUtils.evict(CacheNames.SYS_OSS_CONFIG, sysOssConfig.getConfigKey()));
        }
        return flag;
    }

    /**
     * 判断configKey是否唯一
     */
    private CommonYesOrNoEnum checkConfigKeyUnique(SysOssConfig sysOssConfig) {
        long ossConfigId = ObjectUtil.isNull(sysOssConfig.getOssConfigId()) ? -1L : sysOssConfig.getOssConfigId();
        SysOssConfig info = baseMapper.selectOne(new LambdaQueryWrapper<SysOssConfig>()
            .select(SysOssConfig::getOssConfigId, SysOssConfig::getConfigKey)
            .eq(SysOssConfig::getConfigKey, sysOssConfig.getConfigKey()));
        if (ObjectUtil.isNotNull(info) && info.getOssConfigId() != ossConfigId) {
            return CommonYesOrNoEnum.NO;
        }
        return CommonYesOrNoEnum.YES;
    }

    /**
     * 启用禁用状态
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public int updateOssConfigStatus(SysOssConfigEditBo bo) {
        SysOssConfig sysOssConfig = BeanUtil.toBean(bo, SysOssConfig.class);
        int row = baseMapper.update(null, new LambdaUpdateWrapper<SysOssConfig>()
            .set(SysOssConfig::getStatus, "1"));
        row += baseMapper.updateById(sysOssConfig);
        if (row > 0) {
            RedisUtils.setCacheObject(OssConstant.DEFAULT_CONFIG_KEY, sysOssConfig.getConfigKey());
        }
        return row;
    }

    /**
     * 更新配置缓存
     *
     * @param config 配置
     */
    @EventListener
    public void updateConfigCache(SysOssConfig config) {
        CacheUtils.put(CacheNames.SYS_OSS_CONFIG, config.getConfigKey(), JsonUtils.toJsonString(config));
        RedisUtils.publish(OssConstant.DEFAULT_CONFIG_KEY, config.getConfigKey(), msg -> {
            log.info("发布刷新OSS配置 => " + msg);
        });
    }
}
