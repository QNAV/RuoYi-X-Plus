package com.ruoyi.system.domain.vo;

import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.ruoyi.common.core.domain.BaseVo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;


/**
 * 对象存储配置视图对象 sys_oss_config
 *
 * @author weibocy
 */
@Data
@ApiModel(value = "SysOssConfigVo", description = "对象存储配置视图对象")
@ExcelIgnoreUnannotated
public class SysOssConfigVo extends BaseVo {

    private static final long serialVersionUID = 1L;

    /**
     * 主建
     */
    @ApiModelProperty(value = "主建", required = true)
    private Long ossConfigId;

    /**
     * 配置key
     */
    @ApiModelProperty(value = "配置key", required = true)
    private String configKey;

    /**
     * accessKey
     */
    @ApiModelProperty(value = "accessKey", required = true)
    private String accessKey;

    /**
     * 秘钥
     */
    @ApiModelProperty(value = "secretKey", required = true)
    private String secretKey;

    /**
     * 桶名称
     */
    @ApiModelProperty(value = "桶名称", required = true)
    private String bucketName;

    /**
     * 前缀
     */
    @ApiModelProperty("前缀")
    private String prefix;

    /**
     * 访问站点
     */
    @ApiModelProperty("访问站点")
    private String endpoint;

    /**
     * 自定义域名
     */
    @ApiModelProperty("自定义域名")
    private String domain;

    /**
     * 是否https（Y=是,N=否）
     */
    @ApiModelProperty(value = "是否https（Y=是,N=否）", required = true)
    private String isHttps;

    /**
     * 域
     */
    @ApiModelProperty("域")
    private String region;

    /**
     * 状态（0=正常,1=停用）
     */
    @ApiModelProperty(value = "状态（0=正常,1=停用）", required = true)
    private String status;

    /**
     * 扩展字段
     */
    @ApiModelProperty("扩展字段")
    private String ext1;

    /**
     * 备注
     */
    @ApiModelProperty("备注")
    private String remark;

}
