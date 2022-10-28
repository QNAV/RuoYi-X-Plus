package com.ruoyi.common.core.domain.vo;


import com.ruoyi.common.constant.UserConstants;
import com.ruoyi.common.core.domain.BaseVo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * 角色视图对象
 *
 * @author weibocy
 */

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "SysRoleVo", description = "角色视图对象", parent = BaseVo.class)
public class SysRoleVo extends BaseVo {

    private static final long serialVersionUID = 1L;

    /**
     * 角色ID
     */
    @ApiModelProperty(value = "角色ID", required = true)
    private Long roleId;

    /**
     * 角色名称
     */
    @ApiModelProperty(value = "角色名称", required = true)
    private String roleName;

    /**
     * 角色权限
     */
    @ApiModelProperty(value = "角色权限", required = true)
    private String roleKey;

    /**
     * 角色排序
     */
    @ApiModelProperty(value = "角色排序", required = true)
    private Integer roleSort;

    /**
     * 数据范围（1：所有数据权限；2：自定义数据权限；3：本部门数据权限；4：本部门及以下数据权限；5：仅本人数据权限）
     */
    @ApiModelProperty(value = "数据范围（1：所有数据权限；2：自定义数据权限；3：本部门数据权限；4：本部门及以下数据权限；5：仅本人数据权限）", required = true)
    private String dataScope;

    /**
     * 菜单树选择项是否关联显示（ 0：父子不互相关联显示 1：父子互相关联显示）
     */
    @ApiModelProperty(value = "菜单树选择项是否关联显示（ 0：父子不互相关联显示 1：父子互相关联显示）", required = true)
    private Boolean menuCheckStrictly;

    /**
     * 部门树选择项是否关联显示（0：父子不互相关联显示 1：父子互相关联显示 ）
     */
    @ApiModelProperty(value = "部门树选择项是否关联显示（0：父子不互相关联显示 1：父子互相关联显示 ）", required = true)
    private Boolean deptCheckStrictly;

    /**
     * 角色状态（0正常 1停用）
     */
    @ApiModelProperty(value = "角色状态（0正常 1停用）", required = true)
    private String status;

    /**
     * 删除标志（0代表存在 2代表删除）
     */
    @ApiModelProperty(value = "删除标志（0代表存在 2代表删除）", required = true)
    private String delFlag;

    /**
     * 备注
     */
    @ApiModelProperty(value = "备注")
    private String remark;

    /**
     * 用户是否存在此角色标识 默认不存在
     */
    @ApiModelProperty(value = "用户是否存在此角色标识 默认不存在", required = true)
    private boolean flag = false;

    /**
     * 菜单组
     */
    @ApiModelProperty(value = "菜单组", required = true)
    private Long[] menuIds;

    /**
     * 部门组（数据权限）
     */
    @ApiModelProperty(value = "部门组（数据权限）", required = true)
    private Long[] deptIds;


    @ApiModelProperty(value = "是否管理员", required = true)
    public boolean isAdmin() {
        return UserConstants.ADMIN_ID.equals(this.roleId);
    }
}
