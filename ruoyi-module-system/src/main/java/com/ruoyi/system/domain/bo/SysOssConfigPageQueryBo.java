package com.ruoyi.system.domain.bo;

import com.ruoyi.common.core.domain.bo.PageQuery;
import com.ruoyi.common.enums.CommonNormalDisableEnum;
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
     * 状态（NORMAL=正常 DISABLE=停用）
     */
    @Schema(description = "状态（NORMAL=正常 DISABLE=停用）")
    private CommonNormalDisableEnum status;

}
