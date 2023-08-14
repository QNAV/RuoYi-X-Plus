/**
 * Copyright (C) 2018-2019
 * All rights reserved, Designed By www.joolun.com
 * 注意：
 * 本软件为www.joolun.com开发研制，项目使用请保留此说明
 */
package com.ruoyi.weixin.pay.config;

import com.github.binarywang.wxpay.config.WxPayConfig;
import com.github.binarywang.wxpay.service.WxPayService;
import com.github.binarywang.wxpay.service.impl.WxPayServiceImpl;
import com.google.common.collect.Maps;
import com.ruoyi.wx.config.WxMaProperties;
import com.ruoyi.wx.exception.WxException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.EventListener;
import org.springframework.util.CollectionUtils;

import java.util.Map;
import java.util.stream.Collectors;

/**
 * 微信支付Configuration
 * @author www.joolun.com
 *
 */
@Slf4j
@Configuration
public class WxPayConfiguration {

	private static Map<String ,WxPayService>  wxPayServiceMap =Maps.newHashMap();

	public static WxPayService getMxPayService(String appId) {
		if (wxPayServiceMap == null || (!CollectionUtils.isEmpty(wxPayServiceMap) && !wxPayServiceMap.containsKey(appId))) {
			throw new IllegalArgumentException(String.format("未找到对应appId=[%s]的配置，请核实！", appId));
		}
		return wxPayServiceMap.get(appId);
	}
	/**
	 *  获取WxMpService
	 * @return
	 */
	@EventListener
	public  void getPayService(WxMaProperties wxMaProperties) {
		wxMaProperties.getConfigs().forEach(config -> {
			if(StringUtils.isBlank(config.getMchId())){
				throw new WxException("微信支付商户号不能为空!");
			}
			if(StringUtils.isBlank(config.getMchKey())){
				throw new WxException("微信支付商户密钥不能为空!");
			}
		});
		wxPayServiceMap=	wxMaProperties.getConfigs().stream().map(config -> {
			WxPayService wxPayService = null;
			WxPayConfig payConfig = new WxPayConfig();
			payConfig.setAppId(config.getAppId());
			payConfig.setMchId(config.getMchId());
			payConfig.setMchKey(config.getMchKey());
			payConfig.setKeyPath(config.getKeyPath());
//		// 可以指定是否使用沙箱环境
			payConfig.setUseSandboxEnv(false);
			wxPayService = new WxPayServiceImpl();
			wxPayService.setConfig(payConfig);
			return wxPayService;
		}).collect(Collectors.toMap(s -> s.getConfig().getAppId(), a -> a));
    }

}
