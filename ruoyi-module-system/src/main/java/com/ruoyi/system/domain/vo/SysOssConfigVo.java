package com.ruoyi.system.domain.vo;

import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.ruoyi.common.core.domain.BaseVo;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;


/**
 * 对象存储配置视图对象 sys_oss_config
 *
 * @author weibocy
 */
@Data
@Schema(description = "对象存储配置视图对象")
@ExcelIgnoreUnannotated
public class SysOssConfigVo extends BaseVo {

    private static final long serialVersionUID = 1L;

    /**
     * 主建
     */
    @Schema(description = "主建", required = true)
    private Long ossConfigId;

    /**
     * 配置key
     */
    @Schema(description = "配置key", required = true)
    private String configKey;

    /**
     * accessKey
     */
    @Schema(description = "accessKey", required = true)
    private String accessKey;

    /**
     * 秘钥
     */
    @Schema(description = "secretKey", required = true)
    private String secretKey;

    /**
     * 桶名称
     */
    @Schema(description = "桶名称", required = true)
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
    private String endpoint;

    /**
     * 自定义域名
     */
    @Schema(description = "自定义域名")
    private String domain;

    /**
     * 是否https（Y=是,N=否）
     */
    @Schema(description = "是否https（Y=是,N=否）", required = true)
    private String isHttps;

    /**
     * 域
     */
    @Schema(description = "域")
    private String region;

    /**
     * 状态（0=正常,1=停用）
     */
    @Schema(description = "状态（0=正常,1=停用）", required = true)
    private String status;

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
