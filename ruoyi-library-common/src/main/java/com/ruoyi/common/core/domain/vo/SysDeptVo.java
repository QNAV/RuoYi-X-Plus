package com.ruoyi.common.core.domain.vo;

import com.ruoyi.common.core.domain.TreeVo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 部门视图对象
 *
 * @author weibocy
 */

@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "SysDeptVo", description = "部门视图对象", parent = TreeVo.class)
public class SysDeptVo extends TreeVo<SysDeptVo> {
    private static final long serialVersionUID = 1L;

    /**
     * 部门ID
     */
    @ApiModelProperty(value = "部门id", required = true)
    private Long deptId;

    /**
     * 部门名称
     */
    @ApiModelProperty(value = "部门名称", required = true)
    private String deptName;

    /**
     * 显示顺序
     */
    @ApiModelProperty(value = "显示顺序", required = true)
    private Integer orderNum;

    /**
     * 负责人
     */
    @ApiModelProperty(value = "负责人")
    private String leader;

    /**
     * 联系电话
     */
    @ApiModelProperty(value = "联系电话")
    private String phone;

    /**
     * 邮箱
     */
    @ApiModelProperty(value = "邮箱")
    private String email;

    /**
     * 部门状态:0正常,1停用
     */
    @ApiModelProperty(value = "部门状态:0正常,1停用", required = true)
    private String status;

    /**
     * 删除标志（0代表存在 2代表删除）
     */
    @ApiModelProperty(value = "删除标志（0代表存在 2代表删除）", required = true)
    private String delFlag;

    /**
     * 祖级列表
     */
    @ApiModelProperty(value = "祖级列表")
    private String ancestors;

}
