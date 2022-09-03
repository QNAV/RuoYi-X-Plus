package com.ruoyi.common.core.domain.vo;


import com.ruoyi.common.constant.UserConstants;

import com.ruoyi.common.core.domain.BaseVo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

/**
 * 用户信息视图对象
 *
 * @author weibocy
 */

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "SysUserVo", description = "用户信息视图对象", parent = BaseVo.class)
public class SysUserVo extends BaseVo {

    /**
     * 用户ID
     */
    @ApiModelProperty(value = "用户ID", required = true)
    private Long userId;

    /**
     * 部门ID
     */
    @ApiModelProperty(value = "部门ID", required = true)
    private Long deptId;

    /**
     * 用户账号
     */
    @ApiModelProperty(value = "用户账号", required = true)
    private String userName;

    /**
     * 用户昵称
     */
    @ApiModelProperty(value = "用户昵称", required = true)
    private String nickName;

    /**
     * 用户类型（sys_user系统用户）
     */
    @ApiModelProperty(value = "用户类型", required = true)
    private String userType;

    /**
     * 用户邮箱
     */
    @ApiModelProperty(value = "用户邮箱")
    private String email;

    /**
     * 手机号码
     */
    @ApiModelProperty(value = "手机号码")
    private String phoneNumber;

    /**
     * 用户性别
     */
    @ApiModelProperty(value = "用户性别", required = true)
    private String sex;

    /**
     * 用户头像
     */
    @ApiModelProperty(value = "用户头像")
    private String avatar;

    /**
     * 密码
     */
    @ApiModelProperty(value = "密码", required = true)
    private String password;

    /**
     * 帐号状态（0正常 1停用）
     */
    @ApiModelProperty(value = "帐号状态（0正常 1停用）", required = true)
    private String status;

    /**
     * 删除标志（0代表存在 2代表删除）
     */
    @ApiModelProperty(value = "删除标志（0代表存在 2代表删除）", required = true)
    private String delFlag;

    /**
     * 最后登录IP
     */
    @ApiModelProperty(value = "最后登录IP")
    private String loginIp;

    /**
     * 最后登录时间
     */
    @ApiModelProperty(value = "最后登录时间")
    private Date loginDate;

    /**
     * 备注
     */
    @ApiModelProperty(value = "备注")
    private String remark;

    /**
     * 部门对象
     */
    @ApiModelProperty(value = "部门对象", required = true)
    private SysDeptVo dept;

    /**
     * 角色对象
     */
    @ApiModelProperty(value = "角色对象", required = true)
    private List<SysRoleVo> roles;

    /**
     * 角色组
     */
    @ApiModelProperty(value = "角色组", required = true)
    private Long[] roleIds;

    /**
     * 岗位组
     */
    @ApiModelProperty(value = "岗位组", required = true)
    private Long[] postIds;

    /**
     * 数据权限 当前角色ID
     */
    @ApiModelProperty(value = "角色ID", required = true)
    private Long roleId;


    @ApiModelProperty(value = "是否管理员", required = true)
    public boolean isAdmin() {
        return UserConstants.ADMIN_ID.equals(this.userId);
    }

}
