package com.ruoyi.system.domain.bo;

import com.ruoyi.common.annotation.Sensitive;
import com.ruoyi.common.enums.SensitiveStrategy;
import com.ruoyi.common.xss.Xss;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

/**
 * 用户信息新增业务对象
 *
 * @author weibocy
 */

@Data
@NoArgsConstructor
@Schema(description = "用户信息新增业务对象")
public class SysUserAddBo implements Serializable {

    private static final long serialVersionUID=1L;

    /**
     * 部门ID
     */
    @Schema(description = "部门ID")
    private Long deptId;

    /**
     * 用户账号
     */
    @Schema(description = "用户账号", required = true)
    @Xss(message = "用户账号不能包含脚本字符")
    @NotBlank(message = "用户账号不能为空")
    @Size(min = 0, max = 30, message = "用户账号长度不能超过{max}个字符")
    private String userName;

    /**
     * 用户昵称
     */
    @Schema(description = "用户昵称", required = true)
    @Xss(message = "用户昵称不能包含脚本字符")
    @Size(min = 0, max = 30, message = "用户昵称长度不能超过{max}个字符")
    @NotBlank(message = "用户昵称不能为空")
    private String nickName;

    /**
     * 用户类型（sys_user系统用户）
     */
    @Schema(description = "用户类型")
    private String userType;

    /**
     * 用户邮箱
     */
    @Sensitive(strategy = SensitiveStrategy.EMAIL)
    @Schema(description = "用户邮箱")
    @Email(message = "邮箱格式不正确")
    @Size(min = 0, max = 50, message = "邮箱长度不能超过{max}个字符")
    private String email;

    /**
     * 手机号码
     */
    @Sensitive(strategy = SensitiveStrategy.PHONE)
    @Schema(description = "手机号码")
    private String phoneNumber;

    /**
     * 用户性别
     */
    @Schema(description = "用户性别")
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
    @NotBlank(message = "密码不能为空")
    private String password;

    /**
     * 帐号状态（0正常 1停用）
     */
    @Schema(description = "帐号状态（0正常 1停用）")
    private String status;

    /**
     * 备注
     */
    @Schema(description = "备注")
    private String remark;


    /**
     * 角色组
     */
    @Schema(description = "角色组", required = true)
    @NotNull(message = "角色组不能为空")
    private Long[] roleIds;

    /**
     * 岗位组
     */
    @Schema(description = "岗位组", required = true)
    @NotNull(message = "岗位组不能为空")
    private Long[] postIds;

}
