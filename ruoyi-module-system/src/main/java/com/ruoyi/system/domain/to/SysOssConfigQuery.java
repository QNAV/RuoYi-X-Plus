package com.ruoyi.system.domain.to;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 对象存储配置查询对象
 * @author weibocy
 */
@ApiModel(value = "SysOssConfigQuery", description = "对象存储配置查询对象")
@Data
public class SysOssConfigQuery {

    /**
     * 配置key
     */
    @ApiModelProperty(value = "配置key")
    private String configKey;


    /**
     * 桶名称
     */
    @ApiModelProperty(value = "桶名称")
    private String bucketName;


    /**
     * 状态（0=正常,1=停用）
     */
    @ApiModelProperty("状态（0=正常,1=停用）")
    private String status;
}
