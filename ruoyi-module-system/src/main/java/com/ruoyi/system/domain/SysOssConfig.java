package com.ruoyi.system.domain;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.ruoyi.common.core.domain.BaseEntity;
import com.ruoyi.common.enums.CommonNormalDisableEnum;
import com.ruoyi.common.enums.CommonYesOrNoEnum;
import com.ruoyi.system.enums.AccessPolicyEnum;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 对象存储配置对象 sys_oss_config
 *
 * @author Lion Li
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("sys_oss_config")
public class SysOssConfig extends BaseEntity {

    /**
     * 主建
     */
    @TableId(value = "oss_config_id")
    private Long ossConfigId;

    /**
     * 配置key
     */
    private String configKey;

    /**
     * accessKey
     */
    private String accessKey;

    /**
     * 秘钥
     */
    private String secretKey;

    /**
     * 桶名称
     */
    private String bucketName;

    /**
     * 前缀
     */
    private String prefix;

    /**
     * 访问站点
     */
    private String endpoint;

    /**
     * 自定义域名
     */
    private String domain;

    /**
     * 是否https（NO=否 YES=是）
     */
    private CommonYesOrNoEnum isHttps;

    /**
     * 域
     */
    private String region;

    /**
     * 桶权限类型（PUBLIC=公开 PRIVATE=私有 EXCEPTION=自定义）
     */
    private AccessPolicyEnum accessPolicy;

    /**
     * 状态（NORMAL=正常 DISABLE=停用）
     */
    private CommonNormalDisableEnum status;

    /**
     * 扩展字段
     */
    private String ext1;

    /**
     * 备注
     */
    private String remark;

}
