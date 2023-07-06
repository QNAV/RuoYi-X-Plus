package com.ruoyi.sms.domain;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.ruoyi.common.core.domain.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 对象存储配置对象 sys_oss_config
 *
 * @author Lion Li
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("sys_sms_config")
public class SysSmsConfig extends BaseEntity {

    /**
     * 主建
     */
    @TableId(value = "id")
    private Long id;

    /**
     * 厂商
     */
    private String manufacturer;

    /**
     * 是否启用
     */
    private Boolean enabled;

    /**
     * key
     */
    private String accessKeyId;

    /**
     * 密匙
     */
    private String accessKeySecret;

    /**
     * 短信签名
     */
    private String signName;

    /**
     * 短信应用ID (腾讯专属)
     */
    private String sdkAppId;


    /**
     * 备注
     */
    private String remark;



}
