package com.ruoyi.common.core.domain.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import com.ruoyi.common.core.domain.TreeEntity;
import com.ruoyi.common.enums.CommonNormalDisableEnum;
import com.ruoyi.common.enums.DeleteStatusEnum;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * 部门表 sys_dept
 *
 * @author ruoyi
 * @author Lion Li
 */

@Data
@EqualsAndHashCode(callSuper = true)
@TableName("sys_dept")
@Schema(description = "部门业务对象")
public class SysDept extends TreeEntity<SysDept> {
    private static final long serialVersionUID = 1L;

    /**
     * 部门ID
     */
    @Schema(description = "部门id")
    @TableId(value = "dept_id")
    private Long deptId;

    /**
     * 部门名称
     */
    @Schema(description = "部门名称", required = true)
    @NotBlank(message = "部门名称不能为空")
    @Size(min = 0, max = 30, message = "部门名称长度不能超过{max}个字符")
    private String deptName;

    /**
     * 显示顺序
     */
    @Schema(description = "显示顺序", required = true)
    @NotNull(message = "显示顺序不能为空")
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
    @Size(min = 0, max = 11, message = "联系电话长度不能超过{max}个字符")
    private String phone;

    /**
     * 邮箱
     */
    @Schema(description = "邮箱")
    @Email(message = "邮箱格式不正确")
    @Size(min = 0, max = 50, message = "邮箱长度不能超过{max}个字符")
    private String email;

    /**
     * 部门状态（NORMAL=正常 DISABLE=停用）
     */
    @Schema(description = "部门状态（NORMAL=正常 DISABLE=停用）")
    private CommonNormalDisableEnum status;

    /**
     * 删除标志（EXIST=代表存在 DELETED=代表删除）
     */
    @Schema(description = "删除标志（EXIST=代表存在 DELETED=代表删除）")
    @TableLogic
    private DeleteStatusEnum delFlag;

    /**
     * 祖级列表
     */
    @Schema(description = "祖级列表")
    private String ancestors;

}
