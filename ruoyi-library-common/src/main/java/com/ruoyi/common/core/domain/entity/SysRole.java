package com.ruoyi.common.core.domain.entity;

import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import com.ruoyi.common.annotation.ExcelDictFormat;
import com.ruoyi.common.annotation.ExcelEnumFormat;
import com.ruoyi.common.constant.UserConstants;
import com.ruoyi.common.convert.ExcelDictConvert;
import com.ruoyi.common.convert.ExcelEnumConvert;
import com.ruoyi.common.core.domain.BaseEntity;
import com.ruoyi.common.enums.CommonNormalDisableEnum;
import com.ruoyi.common.enums.CommonYesOrNoEnum;
import com.ruoyi.common.enums.DataScopeType;
import com.ruoyi.common.enums.DeleteStatusEnum;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * 角色表 sys_role
 *
 * @author ruoyi
 * @author Lion Li
 *
 */

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@TableName("sys_role")
@ExcelIgnoreUnannotated
@Schema(description = "角色实体对象")
public class SysRole extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 角色ID
     */
    @Schema(description = "角色ID")
    @ExcelProperty(value = "角色序号")
    @TableId(value = "role_id")
    private Long roleId;

    /**
     * 角色名称
     */
    @Schema(description = "角色名称", required = true)
    @ExcelProperty(value = "角色名称")
    @NotBlank(message = "角色名称不能为空")
    @Size(min = 0, max = 30, message = "角色名称长度不能超过{max}个字符")
    private String roleName;

    /**
     * 角色权限
     */
    @Schema(description = "角色权限", required = true)
    @ExcelProperty(value = "角色权限")
    @NotBlank(message = "权限字符不能为空")
    @Size(min = 0, max = 100, message = "权限字符长度不能超过{max}个字符")
    private String roleKey;

    /**
     * 角色排序
     */
    @Schema(description = "角色排序", required = true)
    @ExcelProperty(value = "角色排序")
    @NotNull(message = "显示顺序不能为空")
    private Integer roleSort;

    /**
     * 数据范围（ALL=全部数据权限 CUSTOM=自定数据权限 DEPT=本部门数据权限 DEPT_CHILD=本部门及以下数据权限 SELF=仅本人数据权限）
     */
    @Schema(description = "数据范围（ALL=全部数据权限 CUSTOM=自定数据权限 DEPT=本部门数据权限 DEPT_CHILD=本部门及以下数据权限 SELF=仅本人数据权限）")
    @ExcelProperty(value = "数据范围", converter = ExcelEnumConvert.class)
    @ExcelEnumFormat(enumClass = DataScopeType.class)
    private DataScopeType dataScope;

    /**
     * 菜单树选择项是否关联显示（NO=父子不互相关联显示 YES=父子互相关联显示）
     */
    @Schema(description = "菜单树选择项是否关联显示（NO=父子不互相关联显示 YES=父子互相关联显示）")
    @ExcelProperty(value = "菜单树选择项是否关联显示", converter = ExcelEnumConvert.class)
    @ExcelEnumFormat(enumClass = CommonYesOrNoEnum.class)
    private CommonYesOrNoEnum menuCheckStrictly;

    /**
     * 部门树选择项是否关联显示（NO=父子不互相关联显示 YES=父子互相关联显示 ）
     */
    @Schema(description = "部门树选择项是否关联显示（NO=父子不互相关联显示 YES=父子互相关联显示 ）")
    private CommonYesOrNoEnum deptCheckStrictly;

    /**
     * 角色状态（NORMAL=正常 DISABLE=停用）
     */
    @Schema(description = "角色状态（NORMAL=正常 DISABLE=停用）", required = true)
    @ExcelProperty(value = "角色状态", converter = ExcelEnumConvert.class)
    @ExcelEnumFormat(enumClass = CommonNormalDisableEnum.class)
    @NotNull(message = "角色状态不能为空")
    private CommonNormalDisableEnum status;

    /**
     * 删除标志（EXIST=代表存在 DELETED=代表删除）
     */
    @Schema(description = "删除标志（EXIST=代表存在 DELETED=代表删除）")
    @TableLogic
    private DeleteStatusEnum delFlag;

    /**
     * 备注
     */
    @Schema(description = "备注")
    private String remark;

    /**
     * 用户是否存在此角色标识 默认不存在
     */
    @Schema(description = "用户是否存在此角色标识 默认不存在")
    @TableField(exist = false)
    private boolean flag = false;

    /**
     * 菜单组
     */
    @Schema(description = "菜单组", required = true)
    @TableField(exist = false)
    private Long[] menuIds;

    /**
     * 部门组（数据权限）
     */
    @Schema(description = "部门组（数据权限）")
    @TableField(exist = false)
    private Long[] deptIds;

    public SysRole(Long roleId) {
        this.roleId = roleId;
    }

    @Schema(description = "是否管理员")
    public boolean isAdmin() {
        return UserConstants.ADMIN_ID.equals(this.roleId);
    }
}
