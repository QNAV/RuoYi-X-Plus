package com.ruoyi.system.domain.bo;

import com.ruoyi.common.core.domain.bo.PageQuery;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * 对象存储配置分页查询对象
 * @author weibocy
 */
@Schema(description = "对象存储配置分页查询对象")
@Data
public class SysOssConfigPageQueryBo extends PageQuery {

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
