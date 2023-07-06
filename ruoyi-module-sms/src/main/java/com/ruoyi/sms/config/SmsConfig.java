package com.ruoyi.sms.config;

import com.ruoyi.sms.config.properties.SmsProperties;
import com.ruoyi.sms.core.AliyunSmsTemplate;
import com.ruoyi.sms.core.SmsTemplate;
import com.ruoyi.sms.core.TencentSmsTemplate;
import lombok.extern.log4j.Log4j2;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 短信配置类
 *
 * @author Lion Li
 */
//@Configuration
@Log4j2
public class SmsConfig {

//    @Configuration
//    @ConditionalOnProperty(value = "sms.enabled", havingValue = "true")
//    @ConditionalOnClass(com.aliyun.dysmsapi20170525.Client.class)
//    static class AliyunSmsConfig {
//
//        @Bean
//        public SmsTemplate aliyunSmsTemplate(SmsProperties smsProperties) {
//            log.info("加载阿里云短信配置:{}",smsProperties.toString());
//            return new AliyunSmsTemplate(smsProperties);
//        }
//
//    }
//
//    @Configuration
//    @ConditionalOnProperty(value = "sms.enabled", havingValue = "true")
//    @ConditionalOnClass(com.tencentcloudapi.sms.v20190711.SmsClient.class)
//    static class TencentSmsConfig {
//
//        @Bean
//        public SmsTemplate tencentSmsTemplate(SmsProperties smsProperties) {
//            log.info("加载腾讯短信配置:{}",smsProperties.toString());
//            return new TencentSmsTemplate(smsProperties);
//        }
//
//    }

}
