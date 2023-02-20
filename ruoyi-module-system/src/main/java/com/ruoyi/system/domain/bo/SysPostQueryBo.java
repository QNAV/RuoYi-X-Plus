package com.ruoyi.system.domain.bo;

import com.ruoyi.common.enums.CommonNormalDisableEnum;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;

/**
 * 岗位信息查询对象
 * @author weibocy
 */
@Schema(description = "岗位信息查询对象")
@Data
public class SysPostQueryBo implements Serializable {

    private static final long serialVersionUID=1L;

    /**
     * 岗位编码
     */
    @Schema(description = "岗位编码")
    private String postCode;

    /**
     * 岗位名称
     */
    @Schema(description = "岗位名称")
    private String postName;


    /**
     * 状态（NORMAL=正常 DISABLE=停用）
     */
    @Schema(description = "状态（NORMAL=正常 DISABLE=停用）")
    private CommonNormalDisableEnum status;

}
