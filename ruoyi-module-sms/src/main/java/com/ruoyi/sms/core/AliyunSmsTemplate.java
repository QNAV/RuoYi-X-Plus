package com.ruoyi.sms.core;

import com.aliyun.dysmsapi20170525.Client;
import com.aliyun.dysmsapi20170525.models.*;
import com.aliyun.teaopenapi.models.Config;
import com.aliyun.teautil.models.RuntimeOptions;
import com.ruoyi.common.utils.JsonUtils;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.sms.config.properties.SmsProperties;
import com.ruoyi.sms.domain.bo.AddSmsTemplateBo;
import com.ruoyi.sms.domain.bo.ModifySmsTemplateBo;
import com.ruoyi.sms.domain.bo.QuerySmsTemplateBo;
import com.ruoyi.sms.domain.vo.UnifySmsTemplatePageVo;
import com.ruoyi.sms.domain.vo.UnifySmsTemplateVo;
import com.ruoyi.sms.entity.SmsResult;
import com.ruoyi.sms.exception.SmsException;
import lombok.SneakyThrows;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Aliyun 短信模板
 *
 * @author Lion Li
 */
public class AliyunSmsTemplate implements SmsTemplate {

    private SmsProperties properties;

    private Client client;

    @SneakyThrows(Exception.class)
    public AliyunSmsTemplate(SmsProperties smsProperties) {
        this.properties = smsProperties;
        Config config = new Config()
            // 您的AccessKey ID
            .setAccessKeyId(smsProperties.getAccessKeyId())
            // 您的AccessKey Secret
            .setAccessKeySecret(smsProperties.getAccessKeySecret())
            // 访问的域名
            .setEndpoint(smsProperties.getEndpoint());
        this.client = new Client(config);
    }

    @Override
    public SmsResult send(String phones, String templateId, Map<String, String> param) {
        if (StringUtils.isBlank(phones)) {
            throw new SmsException("手机号不能为空");
        }
        if (StringUtils.isBlank(templateId)) {
            throw new SmsException("模板ID不能为空");
        }
        SendSmsRequest req = new SendSmsRequest()
            .setPhoneNumbers(phones)
            .setSignName(properties.getSignName())
            .setTemplateCode(templateId)
            .setTemplateParam(JsonUtils.toJsonString(param));
        try {
            SendSmsResponse resp = client.sendSms(req);
            return SmsResult.builder()
                .isSuccess("OK".equals(resp.getBody().getCode()))
                .message(resp.getBody().getMessage())
                .response(JsonUtils.toJsonString(resp))
                .build();
        } catch (Exception e) {
            throw new SmsException(e.getMessage());
        }
    }

    @Override
    public UnifySmsTemplatePageVo querySmsTemplateList(QuerySmsTemplateBo querySmsTemplateBo) {
        QuerySmsTemplateListRequest querySmsTemplateListRequest=new QuerySmsTemplateListRequest();
        querySmsTemplateListRequest.setPageIndex(querySmsTemplateBo.getPageIndex());
        querySmsTemplateListRequest.setPageSize(querySmsTemplateBo.getPageSize());
        RuntimeOptions runtimeOptions = new RuntimeOptions();
        UnifySmsTemplatePageVo unifySmsTemplatePageVo =new UnifySmsTemplatePageVo();
        try {
            QuerySmsTemplateListResponse querySmsTemplateListResponse = client.querySmsTemplateListWithOptions(querySmsTemplateListRequest, runtimeOptions);
            if(querySmsTemplateListResponse.getStatusCode().equals(HttpStatus.OK.value())){
                if(querySmsTemplateListResponse.getBody().getCode().equals("OK")){
                    QuerySmsTemplateListResponseBody body = querySmsTemplateListResponse.getBody();
                    BeanUtils.copyProperties(body, unifySmsTemplatePageVo);
                    if(!CollectionUtils.isEmpty(body.getSmsTemplateList())){
                        List<UnifySmsTemplateVo> unifySmsTemplateVos = body.getSmsTemplateList().stream().map(e -> {
                            UnifySmsTemplateVo unifySmsTemplateVo = new UnifySmsTemplateVo();
                            BeanUtils.copyProperties(e, unifySmsTemplateVo);
                            return unifySmsTemplateVo;
                        }).collect(Collectors.toList());
                        unifySmsTemplatePageVo.setUnifySmsTemplateVos(unifySmsTemplateVos);
                    }
                }else {
                    throw new SmsException(querySmsTemplateListResponse.getBody().getMessage());
                }
            }
        } catch (Exception e) {
            throw new SmsException(e.getMessage());
        }
        return unifySmsTemplatePageVo;
    }

    @Override
    public String addSmsTemplate(AddSmsTemplateBo addSmsTemplateBo) {
        AddSmsTemplateRequest addSmsTemplateRequest=new AddSmsTemplateRequest();
        RuntimeOptions runtimeOptions = new RuntimeOptions();
        BeanUtils.copyProperties(addSmsTemplateBo,addSmsTemplateRequest);
        try {
            AddSmsTemplateResponse addSmsTemplateResponse = client.addSmsTemplateWithOptions(addSmsTemplateRequest, runtimeOptions);
            if(addSmsTemplateResponse.getStatusCode().equals(HttpStatus.OK.value())){
                if(addSmsTemplateResponse.getBody().getCode().equals("OK")){
                    return addSmsTemplateResponse.getBody().getTemplateCode();
                }else {
                    throw new SmsException(addSmsTemplateResponse.getBody().getMessage());
                }
            }
        } catch (Exception e) {
            throw new SmsException(e.getMessage());
        }
        return null;
    }

    @Override
    public String modifySmsTemplate(ModifySmsTemplateBo modifySmsTemplateBo) {
        ModifySmsTemplateRequest modifySmsTemplateRequest=new ModifySmsTemplateRequest();
        RuntimeOptions runtimeOptions = new RuntimeOptions();
        BeanUtils.copyProperties(modifySmsTemplateBo,modifySmsTemplateRequest);
        try {
            ModifySmsTemplateResponse modifySmsTemplateResponse = client.modifySmsTemplateWithOptions(modifySmsTemplateRequest, runtimeOptions);
            if(modifySmsTemplateResponse.getStatusCode().equals(HttpStatus.OK.value())){
                if(modifySmsTemplateResponse.getBody().getCode().equals("OK")){
                    return modifySmsTemplateResponse.getBody().getTemplateCode();
                }else {
                    throw new SmsException(modifySmsTemplateResponse.getBody().getMessage());
                }
            }
        } catch (Exception e) {
            throw new SmsException(e.getMessage());
        }
        return null;
    }

    @Override
    public Boolean deleteSmsTemplate(String templateCode) {
        if(StringUtils.isBlank(templateCode)){
            throw new SmsException("短信模板ID不能为空!");
        }
        DeleteSmsTemplateRequest deleteSmsTemplateRequest=new DeleteSmsTemplateRequest();
        RuntimeOptions runtimeOptions = new RuntimeOptions();
        deleteSmsTemplateRequest.setTemplateCode(templateCode);
        try {
            DeleteSmsTemplateResponse deleteSmsTemplateResponse = client.deleteSmsTemplateWithOptions(deleteSmsTemplateRequest, runtimeOptions);
            if(deleteSmsTemplateResponse.getStatusCode().equals(HttpStatus.OK.value())){
                if(deleteSmsTemplateResponse.getBody().getCode().equals("OK")){
                    return true;
                }else {
                    throw new SmsException(deleteSmsTemplateResponse.getBody().getMessage());
                }
            }
        } catch (Exception e) {
            throw new SmsException(e.getMessage());
        }
        return false;
    }

    @Override
    public UnifySmsTemplateVo querySmsTemplate(String templateCode,Long international) {
        if(StringUtils.isBlank(templateCode)){
            throw new SmsException("短信模板ID不能为空!");
        }
        QuerySmsTemplateRequest querySmsTemplateRequest=new QuerySmsTemplateRequest();
        RuntimeOptions runtimeOptions = new RuntimeOptions();
        querySmsTemplateRequest.setTemplateCode(templateCode);
        try {
            QuerySmsTemplateResponse querySmsTemplateResponse = client.querySmsTemplateWithOptions(querySmsTemplateRequest, runtimeOptions);
            if(querySmsTemplateResponse.getStatusCode().equals(HttpStatus.OK.value())){
                if(querySmsTemplateResponse.getBody().getCode().equals("OK")){
                    UnifySmsTemplateVo unifySmsTemplateVo=new UnifySmsTemplateVo();
                    BeanUtils.copyProperties(querySmsTemplateResponse.getBody(),unifySmsTemplateVo);
                    return unifySmsTemplateVo;
                }else {
                    throw new SmsException(querySmsTemplateResponse.getBody().getMessage());
                }
            }
        } catch (Exception e) {
            throw new SmsException(e.getMessage());
        }
        return null;
    }


}
