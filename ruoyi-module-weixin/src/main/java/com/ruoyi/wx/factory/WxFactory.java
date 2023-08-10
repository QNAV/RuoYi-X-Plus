package com.ruoyi.wx.factory;

import com.ruoyi.common.constant.CacheNames;
import com.ruoyi.common.utils.JsonUtils;
import com.ruoyi.common.utils.redis.CacheUtils;
import com.ruoyi.common.utils.redis.RedisUtils;
import com.ruoyi.common.utils.spring.SpringUtils;
import com.ruoyi.wx.config.WxMaProperties;
import com.ruoyi.wx.config.WxMpProperties;
import com.ruoyi.wx.domain.SysWxConfig;
import com.ruoyi.wx.exception.WxException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.context.event.EventListener;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import static me.chanjar.weixin.common.api.WxConsts.AppIdType.MINI_TYPE;
import static me.chanjar.weixin.common.api.WxConsts.AppIdType.MP_TYPE;

/**
 * @Author Administrator
 * @Date 2023/8/7 15:04
 */
@Slf4j
public class WxFactory {

    public static void init() {
        log.info("初始化WX工厂");
        refresh();
    }


    public static void refresh() {
        Set<Object> keys = CacheUtils.keys(CacheNames.SYS_WX_CONFIG);
        if(!CollectionUtils.isEmpty(keys)){
            List<SysWxConfig> sysWxConfigs=new ArrayList<>(keys.size());
            for (Object key : keys) {
                String json = CacheUtils.get(CacheNames.SYS_WX_CONFIG, key);
                SysWxConfig sysWxConfig = JsonUtils.parseObject(json, SysWxConfig.class);
                sysWxConfigs.add(sysWxConfig);
            }
            createProperties(sysWxConfigs);
        }
    }

    private static void  createProperties(List<SysWxConfig> sysWxConfigs){
        List<WxMaProperties.Config> maConfigs = sysWxConfigs.stream().filter(f -> f.getWxType().equals(MINI_TYPE)).map(wxConfig -> {
            WxMaProperties.Config config = new WxMaProperties.Config();
            BeanUtils.copyProperties(wxConfig, config);
            return config;
        }).collect(Collectors.toList());

        List<WxMpProperties.MpConfig> mpConfig = sysWxConfigs.stream().filter(f -> f.getWxType().equals(MP_TYPE)).map(wxConfig -> {
            WxMpProperties.MpConfig config = new WxMpProperties.MpConfig();
            BeanUtils.copyProperties(wxConfig, config);
            return config;
        }).collect(Collectors.toList());
        //发布配置信息
        publishWxMaEvent(maConfigs);
        publishWxMpEvent(mpConfig);
    }

    /**
     * 发布微信小程序配置
     * @param maConfigs
     */
    private static void publishWxMaEvent( List<WxMaProperties.Config> maConfigs){
        if(!CollectionUtils.isEmpty(maConfigs)){
            WxMaProperties wxMaProperties=new WxMaProperties();
            wxMaProperties.setConfigs(maConfigs);
            SpringUtils.context().publishEvent(wxMaProperties);
        }
    }

    /**
     * 发布微信公众号配置
     * @param mpConfigs
     */
    private static void publishWxMpEvent( List<WxMpProperties.MpConfig> mpConfigs){
        if(!CollectionUtils.isEmpty(mpConfigs)){
            WxMpProperties wxMpProperties=new WxMpProperties();
            wxMpProperties.setConfigs(mpConfigs);
            SpringUtils.context().publishEvent(wxMpProperties);
        }
    }


}
