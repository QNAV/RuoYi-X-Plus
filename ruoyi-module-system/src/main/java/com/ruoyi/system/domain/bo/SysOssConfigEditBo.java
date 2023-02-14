package com.ruoyi.system.domain.bo;

import com.ruoyi.common.enums.CommonNormalDisable;
import com.ruoyi.common.enums.CommonYesOrNo;
import com.ruoyi.system.enums.AccessPolicyEnum;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

/**
 * 对象存储配置修改业务对象
 *
 * @author weibocy
 */

@Data
@Schema(description = "对象存储配置修改业务对象")
public class SysOssConfigEditBo implements Serializable {

    private static final long serialVersionUID=1L;

    /**
     * 主建
     */
    @Schema(description = "主建", required = true)
    @NotNull(message = "主建不能为空")
    private Long ossConfigId;

    /**
     * 配置key
     */
    @Schema(description = "配置key")
    @Size(min = 2, max = 100, message = "configKey长度必须介于2和20 之间")
    private String configKey;

    /**
     * accessKey
     */
    @Schema(description = "accessKey")
    @Size(min = 2, max = 100, message = "accessKey长度必须介于2和100 之间")
    private String accessKey;

    /**
     * 秘钥
     */
    @Schema(description = "secretKey")
    @Size(min = 2, max = 100, message = "secretKey长度必须介于2和100 之间")
    private String secretKey;

    /**
     * 桶名称
     */
    @Schema(description = "桶名称")
    @Size(min = 2, max = 100, message = "bucketName长度必须介于2和100之间")
    private String bucketName;

    /**
     * 前缀
     */
    @Schema(description = "前缀")
    private String prefix;

    /**
     * 访问站点
     */
    @Schema(description = "访问站点")
    @Size(min = 2, max = 100, message = "endpoint长度必须介于2和100之间")
    private String endpoint;

    /**
     * 自定义域名
     */
    @Schema(description = "自定义域名")
    private String domain;

    /**
     * 是否https（NO=否 YES=是）
     */
    @Schema(description = "是否https（NO=否 YES=是）")
    private CommonYesOrNo isHttps;

    /**
     * 桶权限类型（PUBLIC=公开 PRIVATE=私有 EXCEPTION=自定义）
     */
    @Schema(description = "桶权限类型（PUBLIC=公开 PRIVATE=私有 EXCEPTION=自定义）")
    private AccessPolicyEnum accessPolicy;

    /**
     * 状态（NORMAL=正常 DISABLE=停用）
     */
    @Schema(description = "状态（NORMAL=正常 DISABLE=停用）")
    private CommonNormalDisable status;

    /**
     * 域
     */
    @Schema(description = "域")
    private String region;

    /**
     * 扩展字段
     */
    @Schema(description = "扩展字段")
    private String ext1;

    /**
     * 备注
     */
    @Schema(description = "备注")
    private String remark;

}
