package com.ruoyi.sms.factory;

import com.ruoyi.common.constant.CacheNames;
import com.ruoyi.common.utils.JsonUtils;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.utils.redis.CacheUtils;
import com.ruoyi.common.utils.redis.RedisUtils;
import com.ruoyi.sms.config.properties.SmsProperties;
import com.ruoyi.sms.constant.ManufacturerNameEnum;
import com.ruoyi.sms.constant.SmsConstant;
import com.ruoyi.sms.core.AliyunSmsTemplate;
import com.ruoyi.sms.core.SmsTemplate;
import com.ruoyi.sms.core.TencentSmsTemplate;
import com.ruoyi.sms.exception.SmsException;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @Author Administrator
 * @Date 2023/7/3 14:58
 */
@Slf4j
public class SmsFactory {

    private static final Map<String, SmsTemplate> SMS_TEMPLATE_MAP=new ConcurrentHashMap<>();

    public static void init() {
        log.info("初始化SMS工厂");
        RedisUtils.subscribe(SmsConstant.DEFAULT_CONFIG_KEY, String.class, accessKeyId -> {
            SmsTemplate smsTemplate= getSmsTemplate(accessKeyId);
            if(smsTemplate!=null){
                refresh(accessKeyId);
                log.info("订阅刷新SMS配置 => " + accessKeyId);
            }
        });
    }

    /**
     * 获取默认实例
     * @return
     */
    public static SmsTemplate instance(){
        //获取redis 默认类型
      String accessKeyId=  RedisUtils.getCacheObject(SmsConstant.DEFAULT_CONFIG_KEY);
      if (StringUtils.isEmpty(accessKeyId)) {
            throw new SmsException("文件存储服务类型无法找到!");
      }
      return instance(accessKeyId);
    }

    /**
     * 根据Key获取实例
     * @param accessKeyId
     * @return
     */
    private static SmsTemplate instance(String accessKeyId) {
        SmsTemplate smsTemplate = getSmsTemplate(accessKeyId);
        if(smsTemplate==null){
            refresh(accessKeyId);
            return getSmsTemplate(accessKeyId);
        }
        return smsTemplate;
    }

    private static void refresh(String accessKeyId) {
        //从缓存中获取对应key的配置信息
        String json = CacheUtils.get(CacheNames.SYS_SMS_CONFIG, accessKeyId);
        if(json==null){
            throw new SmsException("系统异常, '" + accessKeyId + "'配置信息不存在!");
        }
        SmsProperties properties = JsonUtils.parseObject(json, SmsProperties.class);
        SMS_TEMPLATE_MAP.put(accessKeyId,createSmsTemplate(properties));
    }

    private static SmsTemplate createSmsTemplate(SmsProperties smsProperties) {
        ManufacturerNameEnum instance = ManufacturerNameEnum.getInstance(smsProperties.getManufacturer());
        if(instance==null){
            throw new SmsException("系统异常, '" + smsProperties.getManufacturer() + "'厂商不存在!");
        }
        SmsTemplate smsTemplate=null;
        switch (instance){
            case ALIYUN:  smsTemplate= new AliyunSmsTemplate(smsProperties); break;
            case TENCENT: smsTemplate=new TencentSmsTemplate(smsProperties); break;
        }
        return smsTemplate;
    }


    private static SmsTemplate getSmsTemplate(String accessKeyId) {
        return SMS_TEMPLATE_MAP.get(accessKeyId);
    }
}
