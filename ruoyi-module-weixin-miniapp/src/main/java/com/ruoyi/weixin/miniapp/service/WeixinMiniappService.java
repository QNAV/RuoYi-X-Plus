package com.ruoyi.weixin.miniapp.service;

import cn.binarywang.wx.miniapp.api.WxMaService;
import cn.binarywang.wx.miniapp.bean.WxMaJscode2SessionResult;
import cn.binarywang.wx.miniapp.util.WxMaConfigHolder;
import com.ruoyi.common.exception.ServiceException;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.weixin.miniapp.config.WxMaConfiguration;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.error.WxErrorException;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 微信小程序业务服务
 */
@Slf4j
@Service
public class WeixinMiniappService {

    /**
     * 获取ma服务
     * @param appid 小程序appid
     * @return
     */
    public WxMaService getMaService(String appid){
        return WxMaConfiguration.getMaService(appid);
    }

    /**
     * 获取 微信小程序session
     * @param appid 应用id
     * @param code 授权码
     * @return
     */
    public WxMaJscode2SessionResult getWxSession(String appid, String code) {
        if (StringUtils.isBlank(code)){
            throw new ServiceException("授权码无效");
        }
        WxMaService wxMaService = getMaService(appid);

        try {
            WxMaJscode2SessionResult session = wxMaService.getUserService().getSessionInfo(code);
            return session;
        } catch (WxErrorException e) {
            log.warn(e.getMessage(), e);
        }finally {
            WxMaConfigHolder.remove();//清理ThreadLocal
        }
        return null;
    }


}
