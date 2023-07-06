package com.ruoyi.sms.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.ruoyi.common.constant.CacheNames;
import com.ruoyi.common.enums.CommonYesOrNoEnum;
import com.ruoyi.common.exception.ServiceException;
import com.ruoyi.common.utils.JsonUtils;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.utils.redis.CacheUtils;
import com.ruoyi.common.utils.redis.RedisUtils;
import com.ruoyi.common.utils.spring.SpringUtils;

import com.ruoyi.sms.constant.SmsConstant;
import com.ruoyi.sms.factory.SmsFactory;
import com.ruoyi.sms.domain.SysSmsConfig;
import com.ruoyi.sms.domain.bo.SysSmsConfigEditBo;
import com.ruoyi.sms.mapper.SysSmsConfigMapper;
import com.ruoyi.sms.service.ISysSmsConfigService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.compress.utils.Lists;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.Collection;
import java.util.List;

/**
 * 短信存储配置Service业务层处理
 *
 * @author Lion Li
 */
@Slf4j
@RequiredArgsConstructor
@Service
public class SysSmsConfigServiceImpl implements ISysSmsConfigService {

    private final SysSmsConfigMapper baseMapper;

    /**
     * 项目启动时，初始化参数到缓存，加载配置类
     */
    @Override
    public void init() {
        List<SysSmsConfig> list = baseMapper.selectList();
        if(!CollectionUtils.isEmpty(list)){
            for (SysSmsConfig config : list) {
                String accessKeyId = config.getAccessKeyId();
                if (config.getEnabled()) {
                    RedisUtils.setCacheObject(SmsConstant.DEFAULT_CONFIG_KEY, accessKeyId);
                }
                SpringUtils.context().publishEvent(config);
            }
            SmsFactory.init();
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean insertByBo(SysSmsConfigEditBo bo) {
        SysSmsConfig config = BeanUtil.toBean(bo, SysSmsConfig.class);
        validEntityBeforeSave(config);
        boolean flag = baseMapper.insert(config) > 0;
        if (flag) {
            SpringUtils.context().publishEvent(config);
        }
        return flag;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean updateByBo(SysSmsConfigEditBo bo) {
        SysSmsConfig config = BeanUtil.toBean(bo, SysSmsConfig.class);
        validEntityBeforeSave(config);
        LambdaUpdateWrapper<SysSmsConfig> luw = new LambdaUpdateWrapper<>();
        luw.set(ObjectUtil.isNull(config.getRemark()), SysSmsConfig::getRemark, "");
        luw.set(ObjectUtil.isNull(config.getSdkAppId()), SysSmsConfig::getSdkAppId, "");
        luw.eq(SysSmsConfig::getId, config.getId());
        boolean flag = baseMapper.update(config, luw) > 0;
        if (flag) {
            SpringUtils.context().publishEvent(config);
        }
        return flag;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean deleteWithValidByIds(Collection<Long> ids) {
        List<SysSmsConfig> list = Lists.newArrayList();
        for (Long configId : ids) {
            SysSmsConfig config = baseMapper.selectById(configId);
            list.add(config);
        }
        boolean flag = baseMapper.deleteBatchIds(ids) > 0;
        if (flag) {
            list.forEach(smsOssConfig ->
                    CacheUtils.evict(CacheNames.SYS_SMS_CONFIG, smsOssConfig.getAccessKeyId()));
        }
        return flag;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int updateSmsConfigStatus(SysSmsConfigEditBo bo) {
        SysSmsConfig sysSmsConfig = BeanUtil.toBean(bo, SysSmsConfig.class);
        int row = baseMapper.update(null, new LambdaUpdateWrapper<SysSmsConfig>()
                .set(SysSmsConfig::getEnabled, false));
        row += baseMapper.updateById(sysSmsConfig);
        if (row > 0) {
            RedisUtils.setCacheObject(SmsConstant.DEFAULT_CONFIG_KEY, sysSmsConfig.getAccessKeyId());
        }
        return row;
    }

    private void validEntityBeforeSave(SysSmsConfig config) {
        if (StringUtils.isNotEmpty(config.getAccessKeyId())
                && CommonYesOrNoEnum.NO.equals(checkConfigKeyUnique(config))) {
            throw new ServiceException("操作配置'" + config.getAccessKeyId() + "'失败, 配置key已存在!");
        }
    }

    private CommonYesOrNoEnum checkConfigKeyUnique(SysSmsConfig sysSmsConfig) {
        long smsConfigId = ObjectUtil.isNull(sysSmsConfig.getId()) ? -1L : sysSmsConfig.getId();
        SysSmsConfig info = baseMapper.selectOne(new LambdaQueryWrapper<SysSmsConfig>()
                .select(SysSmsConfig::getId, SysSmsConfig::getId)
                .eq(SysSmsConfig::getAccessKeyId, sysSmsConfig.getAccessKeyId()));
        if (ObjectUtil.isNotNull(info) && info.getId() != smsConfigId) {
            return CommonYesOrNoEnum.NO;
        }
        return CommonYesOrNoEnum.YES;
    }


    /**
     * 更新配置缓存
     *
     * @param config 配置
     */
    @EventListener
    public void updateConfigCache(SysSmsConfig config) {
        CacheUtils.put(CacheNames.SYS_SMS_CONFIG, config.getAccessKeyId(), JsonUtils.toJsonString(config));
        RedisUtils.publish(SmsConstant.DEFAULT_CONFIG_KEY, config.getAccessKeyId(), msg -> {
            log.info("发布刷新SMS配置 => " + msg);
        });
    }

}
