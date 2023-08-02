package com.ruoyi.sms.core;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.date.DateTime;
import cn.hutool.core.util.ArrayUtil;
import com.ruoyi.common.utils.DateUtils;
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
import com.tencentcloudapi.common.Credential;
import com.tencentcloudapi.common.profile.ClientProfile;
import com.tencentcloudapi.common.profile.HttpProfile;
import com.tencentcloudapi.sms.v20190711.SmsClient;
import com.tencentcloudapi.sms.v20190711.models.*;
import lombok.SneakyThrows;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.util.CollectionUtils;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Tencent 短信模板
 *
 * @author Lion Li
 */
public class TencentSmsTemplate implements SmsTemplate {

    private SmsProperties properties;

    private SmsClient client;

    @SneakyThrows(Exception.class)
    public TencentSmsTemplate(SmsProperties smsProperties) {
        this.properties = smsProperties;
        Credential credential = new Credential(smsProperties.getAccessKeyId(), smsProperties.getAccessKeySecret());
        HttpProfile httpProfile = new HttpProfile();
        httpProfile.setEndpoint(smsProperties.getEndpoint());
        ClientProfile clientProfile = new ClientProfile();
        clientProfile.setHttpProfile(httpProfile);
        this.client = new SmsClient(credential, "", clientProfile);
    }

    @Override
    public SmsResult send(String phones, String templateId, Map<String, String> param) {
        if (StringUtils.isBlank(phones)) {
            throw new SmsException("手机号不能为空");
        }
        if (StringUtils.isBlank(templateId)) {
            throw new SmsException("模板ID不能为空");
        }
        SendSmsRequest req = new SendSmsRequest();
        Set<String> set = Arrays.stream(phones.split(StringUtils.SEPARATOR)).map(p -> "+86" + p).collect(Collectors.toSet());
        req.setPhoneNumberSet(ArrayUtil.toArray(set, String.class));
        if (CollUtil.isNotEmpty(param)) {
            req.setTemplateParamSet(ArrayUtil.toArray(param.values(), String.class));
        }
        req.setTemplateID(templateId);
        req.setSign(properties.getSignName());
        req.setSmsSdkAppid(properties.getSdkAppId());
        try {
            SendSmsResponse resp = client.SendSms(req);
            SmsResult.SmsResultBuilder builder = SmsResult.builder()
                .isSuccess(true)
                .message("send success")
                .response(JsonUtils.toJsonString(resp));
            for (SendStatus sendStatus : resp.getSendStatusSet()) {
                if (!"Ok".equals(sendStatus.getCode())) {
                    builder.isSuccess(false).message(sendStatus.getMessage());
                    break;
                }
            }
            return builder.build();
        } catch (Exception e) {
            throw new SmsException(e.getMessage());
        }
    }

    @Override
    public UnifySmsTemplatePageVo querySmsTemplateList(QuerySmsTemplateBo querySmsTemplateBo) {
        DescribeSmsTemplateListRequest describeSmsTemplateListRequest=new DescribeSmsTemplateListRequest();
        describeSmsTemplateListRequest.setInternational(querySmsTemplateBo.getInternational()==null ? 0 :querySmsTemplateBo.getInternational());
        describeSmsTemplateListRequest.set("Limit",querySmsTemplateBo.getPageSize());
        describeSmsTemplateListRequest.set("Offset",querySmsTemplateBo.getPageIndex()-1);
        UnifySmsTemplatePageVo unifySmsTemplatePageVo=new UnifySmsTemplatePageVo();
        unifySmsTemplatePageVo.setCurrentPage(querySmsTemplateBo.getPageIndex());
        unifySmsTemplatePageVo.setPageSize(querySmsTemplateBo.getPageSize());
        unifySmsTemplatePageVo.setTotalCount(0L);
        try {
            DescribeSmsTemplateListResponse smsTemplateListResponse = client.DescribeSmsTemplateList(describeSmsTemplateListRequest);
            if(smsTemplateListResponse.getDescribeTemplateStatusSet()!=null && smsTemplateListResponse.getDescribeTemplateStatusSet().length>0){
                DescribeTemplateListStatus[] describeTemplateStatusSet = smsTemplateListResponse.getDescribeTemplateStatusSet();
                List<UnifySmsTemplateVo> unifySmsTemplateVos=new ArrayList<>(describeTemplateStatusSet.length);
                for (DescribeTemplateListStatus describeTemplateListStatus : describeTemplateStatusSet) {
                    UnifySmsTemplateVo unifySmsTemplateVo=new UnifySmsTemplateVo();
                    unifySmsTemplateVo.setOrderId(describeTemplateListStatus.getTemplateId().toString());
                    unifySmsTemplateVo.setTemplateCode(describeTemplateListStatus.getTemplateId().toString());
                    unifySmsTemplateVo.setTemplateStatus(convertStatus(describeTemplateListStatus.getStatusCode()));
                    unifySmsTemplateVo.setTemplateType(1);
                    unifySmsTemplateVo.setTemplateName(describeTemplateListStatus.getTemplateName());
                    unifySmsTemplateVo.setCreateDate(DateFormatUtils.format(new Date(describeTemplateListStatus.getCreateTime()),DateUtils.YYYY_MM_DD_HH_MM_SS));
                    unifySmsTemplateVo.setReason(describeTemplateListStatus.getReviewReply());
                    unifySmsTemplateVos.add(unifySmsTemplateVo);
                }
                unifySmsTemplatePageVo.setTotalCount(Long.valueOf(describeTemplateStatusSet.length+""));
                unifySmsTemplatePageVo.setUnifySmsTemplateVos(unifySmsTemplateVos);
            }
        }catch (Exception e){
            throw new SmsException(e.getMessage());
        }
        return unifySmsTemplatePageVo;
    }



    @Override
    public String addSmsTemplate(AddSmsTemplateBo addSmsTemplateBo) {
        AddSmsTemplateRequest addSmsTemplateRequest=new AddSmsTemplateRequest();
        addSmsTemplateRequest.setInternational(addSmsTemplateBo.getInternational()== null ? 0 : addSmsTemplateBo.getInternational());
        addSmsTemplateRequest.setRemark(addSmsTemplateBo.getRemark());
        addSmsTemplateRequest.setSmsType(Long.valueOf(addSmsTemplateBo.getTemplateType()));
        addSmsTemplateRequest.setTemplateName(addSmsTemplateBo.getTemplateName());
        addSmsTemplateRequest.setTemplateContent(addSmsTemplateBo.getTemplateContent());
        try {
            AddSmsTemplateResponse addSmsTemplateResponse = client.AddSmsTemplate(addSmsTemplateRequest);
           return addSmsTemplateResponse.getAddTemplateStatus().getTemplateId();
        }catch (Exception e){
            throw new SmsException(e.getMessage());
        }
    }

    @Override
    public String modifySmsTemplate(ModifySmsTemplateBo modifySmsTemplateBo) {
        ModifySmsTemplateRequest modifySmsTemplateRequest=new ModifySmsTemplateRequest();
        modifySmsTemplateRequest.setInternational(modifySmsTemplateBo.getInternational()==null ? 0 :modifySmsTemplateBo.getInternational());
        modifySmsTemplateRequest.setRemark(modifySmsTemplateBo.getRemark());
        modifySmsTemplateRequest.setSmsType(Long.valueOf(modifySmsTemplateBo.getTemplateType()));
        modifySmsTemplateRequest.setTemplateContent(modifySmsTemplateBo.getTemplateContent());
        modifySmsTemplateRequest.setTemplateName(modifySmsTemplateBo.getTemplateName());
        modifySmsTemplateRequest.setTemplateId(Long.valueOf(modifySmsTemplateBo.getTemplateCode()));
        try {
            ModifySmsTemplateResponse modifySmsTemplateResponse = client.ModifySmsTemplate(modifySmsTemplateRequest);
            return modifySmsTemplateResponse.getModifyTemplateStatus().getTemplateId().toString();
        }catch (Exception e){
            throw new SmsException(e.getMessage());
        }
    }

    @Override
    public Boolean deleteSmsTemplate(String templateCode) {
        if(StringUtils.isBlank(templateCode)){
            throw new SmsException("短信模板ID不能为空!");
        }
        DeleteSmsTemplateRequest deleteSmsTemplateRequest=new DeleteSmsTemplateRequest();
        deleteSmsTemplateRequest.setTemplateId(Long.valueOf(templateCode));
        try {
            DeleteSmsTemplateResponse deleteSmsTemplateResponse = client.DeleteSmsTemplate(deleteSmsTemplateRequest);
            return deleteSmsTemplateResponse.getDeleteTemplateStatus().getDeleteStatus().contains("success") ? true : false;
        }catch (Exception e){
            throw new SmsException(e.getMessage());
        }
    }

    @Override
    public UnifySmsTemplateVo querySmsTemplate(String templateCode,Long international) {
        if(StringUtils.isBlank(templateCode)){
            throw new SmsException("短信模板ID不能为空!");
        }
        DescribeSmsTemplateListRequest describeSmsTemplateListRequest=new DescribeSmsTemplateListRequest();
        describeSmsTemplateListRequest.setTemplateIdSet(new Long[]{Long.valueOf(templateCode)});
        describeSmsTemplateListRequest.setInternational(international==null ? 0 : international);
        try {
            DescribeSmsTemplateListResponse smsTemplateListResponse = client.DescribeSmsTemplateList(describeSmsTemplateListRequest);
            UnifySmsTemplateVo unifySmsTemplateVo=new UnifySmsTemplateVo();
            if(smsTemplateListResponse.getDescribeTemplateStatusSet()!=null && smsTemplateListResponse.getDescribeTemplateStatusSet().length>0){
                DescribeTemplateListStatus describeTemplateListStatus = smsTemplateListResponse.getDescribeTemplateStatusSet()[0];
                unifySmsTemplateVo.setOrderId(describeTemplateListStatus.getTemplateId().toString());
                unifySmsTemplateVo.setTemplateCode(describeTemplateListStatus.getTemplateId().toString());
                unifySmsTemplateVo.setTemplateStatus(convertStatus(describeTemplateListStatus.getStatusCode()));
                unifySmsTemplateVo.setTemplateType(1);
                unifySmsTemplateVo.setTemplateName(describeTemplateListStatus.getTemplateName());
                unifySmsTemplateVo.setCreateDate(DateFormatUtils.format(new Date(describeTemplateListStatus.getCreateTime()),DateUtils.YYYY_MM_DD_HH_MM_SS));
                unifySmsTemplateVo.setReason(describeTemplateListStatus.getReviewReply());
            }
            return unifySmsTemplateVo;
        }catch (Exception e){
            throw new SmsException(e.getMessage());
        }
    }

    /**
     * 状态转换
     * @param status 腾讯云短信模板状态
     * @return
     */
    private Integer convertStatus(Long status) {
        //申请签名状态，其中0表示审核通过，1表示审核中。-1：表示审核未通过或审核失败。
        ////模板审核状态 ,0：审核中。1：审核通过。2：审核未通过。10：取消审核
        Integer newStatus=null;
        if(status==-1){
            newStatus=2;
        }else if(status==0){
            newStatus=1;
        }else if(status==1){
            newStatus=0;
        }
        return newStatus;
    }
}
