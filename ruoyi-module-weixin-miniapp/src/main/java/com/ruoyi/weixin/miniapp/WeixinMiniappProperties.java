package com.ruoyi.weixin.miniapp;

import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;


/**
 * 微信小程序模块配置
 * @author <a href="https://github.com/binarywang">Binary Wang</a>
 */
@ConfigurationProperties(prefix = "weixin.miniapp")
public class WeixinMiniappProperties {

    public WeixinMiniappProperties(List<Config> configs) {
        this.configs = configs;
    }


    /**
     * 是否开启微信小程序模块
     */
    private Boolean enable = false;

    /**
     * 小程序开发配置（支持单个或者多个小程序）
     */
    private List<Config> configs;

    public static class Config {
        /**
         * 设置微信小程序的appid
         */
        private String appid;

        /**
         * 设置微信小程序的Secret
         */
        private String secret;

        /**
         * 设置微信小程序消息服务器配置的token
         */
        private String token;

        /**
         * 设置微信小程序消息服务器配置的EncodingAESKey
         */
        private String aesKey;

        /**
         * 消息格式，XML或者JSON
         */
        private String msgDataFormat;

        public String getAppid() {
            return appid;
        }

        public void setAppid(String appid) {
            this.appid = appid;
        }

        public String getSecret() {
            return secret;
        }

        public void setSecret(String secret) {
            this.secret = secret;
        }

        public String getToken() {
            return token;
        }

        public void setToken(String token) {
            this.token = token;
        }

        public String getAesKey() {
            return aesKey;
        }

        public void setAesKey(String aesKey) {
            this.aesKey = aesKey;
        }

        public String getMsgDataFormat() {
            return msgDataFormat;
        }

        public void setMsgDataFormat(String msgDataFormat) {
            this.msgDataFormat = msgDataFormat;
        }
    }

    public List<Config> getConfigs() {
        return configs;
    }

    public void setConfigs(List<Config> configs) {
        this.configs = configs;
    }

    public Boolean getEnable() {
        return enable;
    }

    public void setEnable(Boolean enable) {
        this.enable = enable;
    }
}
