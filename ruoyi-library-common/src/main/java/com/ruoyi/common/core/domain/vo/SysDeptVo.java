package com.ruoyi.common.core.domain.vo;

import com.ruoyi.common.core.domain.TreeVo;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 部门视图对象
 *
 * @author weibocy
 */

@Data
@EqualsAndHashCode(callSuper = true)
@Schema(description = "部门视图对象")
public class SysDeptVo extends TreeVo<SysDeptVo> {

    private static final long serialVersionUID = 1L;

    /**
     * 部门ID
     */
    @Schema(description = "部门id", required = true)
    private Long deptId;

    /**
     * 部门名称
     */
    @Schema(description = "部门名称", required = true)
    private String deptName;

    /**
     * 显示顺序
     */
    @Schema(description = "显示顺序", required = true)
    private Integer orderNum;

    /**
     * 负责人
     */
    @Schema(description = "负责人")
    private String leader;

    /**
     * 联系电话
     */
    @Schema(description = "联系电话")
    private String phone;

    /**
     * 邮箱
     */
    @Schema(description = "邮箱")
    private String email;

    /**
     * 部门状态:0正常,1停用
     */
    @Schema(description = "部门状态:0正常,1停用", required = true)
    private String status;

    /**
     * 删除标志（0代表存在 2代表删除）
     */
    @Schema(description = "删除标志（0代表存在 2代表删除）", required = true)
    private String delFlag;

    /**
     * 祖级列表
     */
    @Schema(description = "祖级列表")
    private String ancestors;

}
