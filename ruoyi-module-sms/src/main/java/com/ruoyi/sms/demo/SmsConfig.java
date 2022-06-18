package com.ruoyi.sms.demo;

import lombok.Data;
import org.hibernate.validator.constraints.Range;

import java.io.Serializable;

/**
 * 名称：SmsConfig <br>
 * 描述：短信配置信息<br>
 *
 */
@Data
public class SmsConfig implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 类型 1：腾讯云  应该用key值 配合到数据库好区分
     */
    @Range(min = 1, max = 3, message = "类型错误")
    private Integer type;

    /**
     * appid
     */
    private int appid;

    /**
     * key
     */
    private String appkey;

    /**
     * 签名
     */
    private String sign;
}
