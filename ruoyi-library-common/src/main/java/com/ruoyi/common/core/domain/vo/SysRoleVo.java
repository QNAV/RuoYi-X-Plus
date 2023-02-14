package com.ruoyi.common.core.domain.vo;


import com.ruoyi.common.constant.UserConstants;
import com.ruoyi.common.core.domain.BaseVo;
import com.ruoyi.common.enums.CommonNormalDisable;
import com.ruoyi.common.enums.CommonYesOrNo;
import com.ruoyi.common.enums.DataScopeType;
import com.ruoyi.common.enums.DeleteStatus;
import io.swagger.v3.oas.annotations.media.Schema;
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
@Schema(description = "角色视图对象")
public class SysRoleVo extends BaseVo {

    private static final long serialVersionUID = 1L;

    /**
     * 角色ID
     */
    @Schema(description = "角色ID", required = true)
    private Long roleId;

    /**
     * 角色名称
     */
    @Schema(description = "角色名称", required = true)
    private String roleName;

    /**
     * 角色权限
     */
    @Schema(description = "角色权限", required = true)
    private String roleKey;

    /**
     * 角色排序
     */
    @Schema(description = "角色排序", required = true)
    private Integer roleSort;

    /**
     * 数据范围（ALL=全部数据权限 CUSTOM=自定数据权限 DEPT=本部门数据权限 DEPT_CHILD=本部门及以下数据权限 SELF=仅本人数据权限）
     */
    @Schema(description = "数据范围（ALL=全部数据权限 CUSTOM=自定数据权限 DEPT=本部门数据权限 DEPT_CHILD=本部门及以下数据权限 SELF=仅本人数据权限）", required = true)
    private DataScopeType dataScope;

    /**
     * 菜单树选择项是否关联显示（NO=父子不互相关联显示 YES=父子互相关联显示）
     */
    @Schema(description = "菜单树选择项是否关联显示（NO=父子不互相关联显示 YES=父子互相关联显示）", required = true)
    private CommonYesOrNo menuCheckStrictly;

    /**
     * 部门树选择项是否关联显示（NO=父子不互相关联显示 YES=父子互相关联显示 ）
     */
    @Schema(description = "部门树选择项是否关联显示（NO=父子不互相关联显示 YES=父子互相关联显示 ）", required = true)
    private CommonYesOrNo deptCheckStrictly;

    /**
     * 角色状态（NORMAL=正常 DISABLE=停用）
     */
    @Schema(description = "角色状态（NORMAL=正常 DISABLE=停用）", required = true)
    private CommonNormalDisable status;

    /**
     * 删除标志（EXIST=代表存在 DELETED=代表删除）
     */
    @Schema(description = "删除标志（EXIST=代表存在 DELETED=代表删除）", required = true)
    private DeleteStatus delFlag;

    /**
     * 备注
     */
    @Schema(description = "备注")
    private String remark;

    /**
     * 用户是否存在此角色标识 默认不存在
     */
    @Schema(description = "用户是否存在此角色标识 默认不存在", required = true)
    private boolean flag = false;

    /**
     * 菜单组
     */
    @Schema(description = "菜单组", required = true)
    private Long[] menuIds;

    /**
     * 部门组（数据权限）
     */
    @Schema(description = "部门组（数据权限）", required = true)
    private Long[] deptIds;


    @Schema(description = "是否管理员", required = true)
    public boolean isAdmin() {
        return UserConstants.ADMIN_ID.equals(this.roleId);
    }
}
