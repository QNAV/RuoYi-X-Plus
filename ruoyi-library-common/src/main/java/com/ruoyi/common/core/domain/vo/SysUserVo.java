package com.ruoyi.common.core.domain.vo;


import com.ruoyi.common.constant.UserConstants;

import com.ruoyi.common.core.domain.BaseVo;
import com.ruoyi.common.enums.CommonNormalDisable;
import com.ruoyi.common.enums.DeleteStatus;
import com.ruoyi.common.enums.UserSexEnum;
import com.ruoyi.common.enums.UserType;
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
     * 用户类型（PC=电脑端 ANDROID=安卓app端 IOS=苹果app端 WXAPP=微信小程序端 WXMP=微信公众号端 ALIPAYAPP=支付宝小程序端）
     */
    @Schema(description = "用户类型（PC=电脑端 ANDROID=安卓app端 IOS=苹果app端 WXAPP=微信小程序端 WXMP=微信公众号端 ALIPAYAPP=支付宝小程序端）", required = true)
    private UserType userType;

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
     * 用户性别（UNKNOWN=未知 MAN=男 WOMAN=女）
     */
    @Schema(description = "用户性别（UNKNOWN=未知 MAN=男 WOMAN=女）", required = true)
    private UserSexEnum sex;

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
     * 帐号状态（NORMAL=正常 DISABLE=停用）
     */
    @Schema(description = "帐号状态（NORMAL=正常 DISABLE=停用）", required = true)
    private CommonNormalDisable status;

    /**
     * 删除标志（EXIST=代表存在 DELETED=代表删除）
     */
    @Schema(description = "删除标志（EXIST=代表存在 DELETED=代表删除）", required = true)
    private DeleteStatus delFlag;

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
