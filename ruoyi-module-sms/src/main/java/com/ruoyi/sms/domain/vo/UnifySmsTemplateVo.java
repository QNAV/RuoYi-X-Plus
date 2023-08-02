package com.ruoyi.sms.domain.vo;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDateTime;

/**
 * @Author Administrator
 * @Date 2023/7/11 11:42
 */
@Data
@NoArgsConstructor
@ToString
public class UnifySmsTemplateVo {

    private String auditStatus; //审核状态

    private String reason;//审核失败原因

    private String orderId; //编号

    private Integer templateType ;//模板类型 ,0：验证码。1：短信通知。2：推广短信。3：国际/港澳台消息。

    private String templateCode ;//模板编号

    private String templateContent ;//模板内容

    private String templateName ;//模板名称

    private String createDate ;//创建时间

    private Integer templateStatus;//模板审核状态 ,0：审核中。1：审核通过。2：审核未通过，请在返回参数Reason中查看审核失败原因。10：取消审核
}
