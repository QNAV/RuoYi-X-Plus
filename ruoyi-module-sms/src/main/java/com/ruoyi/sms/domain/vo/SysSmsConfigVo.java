package com.ruoyi.sms.domain.vo;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.ruoyi.common.core.domain.BaseEntity;
import com.ruoyi.common.core.domain.BaseVo;
import com.ruoyi.common.enums.CommonNormalDisableEnum;
import com.ruoyi.common.enums.CommonYesOrNoEnum;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * 短信配置对象 sys_oss_config
 *
 * @author Lion Li
 */
@Data
@NoArgsConstructor
public class SysSmsConfigVo extends BaseVo {

    /**
     * 主建
     */
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
     * 地区
     */
    private String region;

    /**
     * 备注
     */
    private String remark;

}