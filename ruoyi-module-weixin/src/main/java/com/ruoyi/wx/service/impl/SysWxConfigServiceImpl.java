package com.ruoyi.wx.service.impl;


import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import com.ruoyi.common.constant.CacheNames;
import com.ruoyi.common.enums.CommonYesOrNoEnum;
import com.ruoyi.common.exception.ServiceException;
import com.ruoyi.common.utils.JsonUtils;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.utils.redis.CacheUtils;
import com.ruoyi.common.utils.redis.RedisUtils;
import com.ruoyi.common.utils.spring.SpringUtils;
import com.ruoyi.wx.domain.SysWxConfig;
import com.ruoyi.wx.domain.bo.SysWxConfigEditBo;
import com.ruoyi.wx.exception.WxException;
import com.ruoyi.wx.factory.WxFactory;
import com.ruoyi.wx.mapper.SysWxConfigMapper;
import com.ruoyi.wx.service.ISysWxConfigService;
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
public class SysWxConfigServiceImpl implements ISysWxConfigService {

    private final SysWxConfigMapper baseMapper;

    /**
     * 项目启动时，初始化参数到缓存，加载配置类
     */
    @Override
    public void init() {
        //加载所有配置
        List<SysWxConfig> wxConfigs = baseMapper.selectList();
        CacheUtils.clear(CacheNames.SYS_WX_CONFIG);
        if(!CollectionUtils.isEmpty(wxConfigs)){
            for (SysWxConfig config : wxConfigs) {
                CacheUtils.put(CacheNames.SYS_WX_CONFIG, config.getAppId(), JsonUtils.toJsonString(config));
            }
            WxFactory.init();
        }
    }

    @Override
    public Boolean insertByBo(SysWxConfigEditBo bo) {
        SysWxConfig sysWxConfig = addConfig(bo);
        boolean flag= sysWxConfig==null ? false : true;
        if(flag){
            updateConfigCache(sysWxConfig);
        }
        return flag;
    }


    @Transactional(rollbackFor = Exception.class)
    public SysWxConfig addConfig(SysWxConfigEditBo bo) {
        SysWxConfig config = BeanUtil.toBean(bo, SysWxConfig.class);
        validEntityBeforeSave(config);
        if(config.getId()==null){
            config.setId(IdWorker.getId());
        }
        return baseMapper.insert(config) > 0 ? config : null;
    }

    @Override
    public Boolean updateByBo(SysWxConfigEditBo bo) {
        SysWxConfig sysWxConfig = updateConfig(bo);
        boolean flag= sysWxConfig==null ? false : true;
        if(flag){
            updateConfigCache(sysWxConfig);
        }
        return flag;
    }

    @Transactional(rollbackFor = Exception.class)
    public SysWxConfig updateConfig(SysWxConfigEditBo bo) {
        if(bo.getId()==null){
            throw new WxException("微信配置ID不能为空!");
        }
        SysWxConfig config = BeanUtil.toBean(bo, SysWxConfig.class);
        validEntityBeforeSave(config);
        LambdaUpdateWrapper<SysWxConfig> luw = new LambdaUpdateWrapper<>();
        luw.eq(SysWxConfig::getId, config.getId());
        return baseMapper.update(config, luw) > 0 ? config :null;
    }

    @Override
    public Boolean deleteWithValidByIds(Collection<Long> ids) {
        List<SysWxConfig> sysWxConfigs = deleteByIds(ids);
        boolean flag= !CollectionUtils.isEmpty(sysWxConfigs) ? true : false;
        if(flag){
            deleteListConfigCache(sysWxConfigs);
        }
        return flag;
    }

    @Transactional(rollbackFor = Exception.class)
    public   List<SysWxConfig> deleteByIds(Collection<Long> ids) {
        if(CollectionUtils.isEmpty(ids)){
            throw new WxException("删除的配置ID不能为空!");
        }
        List<SysWxConfig> list = Lists.newArrayList();
        for (Long configId : ids) {
            SysWxConfig config = baseMapper.selectById(configId);
            list.add(config);
        }
        return baseMapper.deleteBatchIds(ids) > 0 ? list : null;
    }

    private void validEntityBeforeSave(SysWxConfig config) {
        if (StringUtils.isNotEmpty(config.getAppId())
                && CommonYesOrNoEnum.NO.equals(checkConfigKeyUnique(config))) {
            throw new ServiceException("操作配置'" + config.getAppId() + "'失败, 配置key已存在!");
        }
    }

    private CommonYesOrNoEnum checkConfigKeyUnique(SysWxConfig sysWxConfig) {
        long id = ObjectUtil.isNull(sysWxConfig.getId()) ? -1L : sysWxConfig.getId();
        SysWxConfig info = baseMapper.selectOne(new LambdaQueryWrapper<SysWxConfig>()
                .select( SysWxConfig::getId,SysWxConfig::getAppId )
                .eq(SysWxConfig::getAppId, sysWxConfig.getAppId()));
        if (ObjectUtil.isNotNull(info) && info.getId() != id) {
            return CommonYesOrNoEnum.NO;
        }
        return CommonYesOrNoEnum.YES;
    }

    /**
     * 更新配置缓存
     *
     * @param config 配置
     */
    public void updateConfigCache(SysWxConfig config) {
        CacheUtils.put(CacheNames.SYS_WX_CONFIG, config.getAppId(), JsonUtils.toJsonString(config));
        WxFactory.refresh();

    }

    /**
     * 删除配置信息
     * @param configs
     */
    public void deleteListConfigCache(List<SysWxConfig> configs) {
        configs.forEach(wxConfig ->
                CacheUtils.evict(CacheNames.SYS_WX_CONFIG, wxConfig.getAppId()));
        WxFactory.refresh();
    }
}
