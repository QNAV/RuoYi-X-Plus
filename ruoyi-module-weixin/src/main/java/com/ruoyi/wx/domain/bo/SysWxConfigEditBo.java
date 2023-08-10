package com.ruoyi.wx.domain.bo;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * @Author Administrator
 * @Date 2023/8/9 14:29
 */
@Data
public class SysWxConfigEditBo {

    private Long id;
    /**
     * appId
     */
    @NotBlank(message = "appId不能为空")
    private String appId;

    /**
     * Secret
     */
    @NotBlank(message = "secret不能为空")
    private String secret;

    /**
     * token
     */
    private String token;

    /**
     * EncodingAESKey
     */
    private String aesKey;

    /**
     * 消息格式，XML或者JSON
     */
    private String msgDataFormat;

    /**
     *  名称
     */
    @NotBlank(message = "名称不能为空")
    private String name;

    /**
     *  主体名称
     */
    private String principalName;

    /**
     * 类型:  mp-公众号；mini-小程序
     */
    @NotBlank(message = "类型不能为空")
    private String wxType;

    /**
     *  图标
     */
    private String logo;

    /**
     *  二维码地址
     */
    private String qrCode;

    /**
     * 商户号
     */
    private String mchId;

    /**
     * 商户Key
     */
    private String mchKey;

    /**
     * 备注
     */
    private String remarks;

    /**
     * 租户ID 预留
     */
    private String tenantId;

    /**
     * p12证书的位置，可以指定绝对路径，也可以指定类路径（以classpath:开头）
     */
    private String keyPath;

    /**
     * 认证类型 : 0-未认证 ； 1-已认证
     */
    private Integer verifyType;
}
