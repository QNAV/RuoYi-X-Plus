package com.ruoyi.system.domain.bo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;

/**
 * 对象存储配置查询对象
 * @author weibocy
 */
@Schema(description = "对象存储配置查询对象")
@Data
public class SysOssConfigQueryBo implements Serializable {

    private static final long serialVersionUID=1L;

    /**
     * 配置key
     */
    @Schema(description = "配置key")
    private String configKey;


    /**
     * 桶名称
     */
    @Schema(description = "桶名称")
    private String bucketName;


    /**
     * 是否默认（0=是,1=否）
     */
    @Schema(description = "是否默认（0=是,1=否）")
    private String status;
}
