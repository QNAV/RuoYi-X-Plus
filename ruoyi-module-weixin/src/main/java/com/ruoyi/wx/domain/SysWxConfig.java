package com.ruoyi.wx.domain;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.ruoyi.common.core.domain.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @Author Administrator
 * @Date 2023/8/7 14:24
 */

@Data
@EqualsAndHashCode(callSuper = true)
@TableName("sys_wx_config")
public class SysWxConfig extends BaseEntity {

    /**
     * appId
     */
    @TableId(value = "id")
    private Long id;

    /**
     * appId
     */
    private String appId;

    /**
     * Secret
     */
    private String secret;

    /**
     * 设token
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
     * 消息模板ID
     */
    private String newMsgTemplId;

    /**
     *  名称
     */
    private String name;

    /**
     *  主体名称
     */
    private String principalName;

    /**
     * 类型:  mp-公众号；mini-小程序
     */
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
     * 组织ID
     */
    private String originalId;

    /**
     * p12证书的位置，可以指定绝对路径，也可以指定类路径（以classpath:开头）
     */
    private String keyPath;

    /**
     * 认证类型 : 0-未认证 ； 1-已认证
     */
    private Integer verifyType;

}
