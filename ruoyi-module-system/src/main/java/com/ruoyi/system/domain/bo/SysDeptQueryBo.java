package com.ruoyi.system.domain.bo;

import com.ruoyi.common.enums.CommonNormalDisableEnum;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;

/**
 * 部门查询对象
 * @author weibocy
 */
@Data
@Schema(description = "部门查询对象")
public class SysDeptQueryBo implements Serializable {

    private static final long serialVersionUID=1L;

    /**
     * 部门ID
     */
    @Schema(description = "部门id")
    private Long deptId;

    /**
     * 部门名称
     */
    @Schema(description = "部门名称")
    private String deptName;


    /**
     * 父菜单ID
     */
    @Schema(description = "父菜单ID")
    private Long parentId;


    /**
     * 部门状态（NORMAL=正常 DISABLE=停用）
     */
    @Schema(description = "部门状态（NORMAL=正常 DISABLE=停用）")
    private CommonNormalDisableEnum status;


}
