package com.ruoyi.common.core.domain.vo;


import com.ruoyi.common.constant.UserConstants;

import com.ruoyi.common.core.domain.BaseVo;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

/**
 * 后台用户信息视图对象
 *
 * @author weibocy
 */

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Schema(description = "后台用户信息视图对象")
public class SysUserVo extends BaseVo {

    private static final long serialVersionUID = 1L;

    /**
     * 用户ID
     */
    @Schema(description = "用户ID", required = true)
    private Long userId;

    /**
     * 部门ID
     */
    @Schema(description = "部门ID", required = true)
    private Long deptId;

    /**
     * 用户账号
     */
    @Schema(description = "用户账号", required = true)
    private String userName;

    /**
     * 用户昵称
     */
    @Schema(description = "用户昵称", required = true)
    private String nickName;

    /**
     * 用户类型（sys_user系统用户）
     */
    @Schema(description = "用户类型", required = true)
    private String userType;

    /**
     * 用户邮箱
     */
    @Schema(description = "用户邮箱")
    private String email;

    /**
     * 手机号码
     */
    @Schema(description = "手机号码")
    private String phoneNumber;

    /**
     * 用户性别
     */
    @Schema(description = "用户性别", required = true)
    private String sex;

    /**
     * 用户头像
     */
    @Schema(description = "用户头像")
    private String avatar;

    /**
     * 密码
     */
    @Schema(description = "密码", required = true)
    private String password;

    /**
     * 帐号状态（0正常 1停用）
     */
    @Schema(description = "帐号状态（0正常 1停用）", required = true)
    private String status;

    /**
     * 删除标志（0代表存在 2代表删除）
     */
    @Schema(description = "删除标志（0代表存在 2代表删除）", required = true)
    private String delFlag;

    /**
     * 最后登录IP
     */
    @Schema(description = "最后登录IP")
    private String loginIp;

    /**
     * 最后登录时间
     */
    @Schema(description = "最后登录时间")
    private Date loginDate;

    /**
     * 备注
     */
    @Schema(description = "备注")
    private String remark;

    /**
     * 部门对象
     */
    @Schema(description = "部门对象", required = true)
    private SysDeptVo dept;

    /**
     * 角色对象
     */
    @Schema(description = "角色对象", required = true)
    private List<SysRoleVo> roles;

    /**
     * 角色组
     */
    @Schema(description = "角色组", required = true)
    private Long[] roleIds;

    /**
     * 岗位组
     */
    @Schema(description = "岗位组", required = true)
    private Long[] postIds;

    /**
     * 数据权限 当前角色ID
     */
    @Schema(description = "角色ID", required = true)
    private Long roleId;


    @Schema(description = "是否管理员", required = true)
    public boolean isAdmin() {
        return UserConstants.ADMIN_ID.equals(this.userId);
    }

}
