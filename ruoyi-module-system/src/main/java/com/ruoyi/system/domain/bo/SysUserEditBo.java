package com.ruoyi.system.domain.bo;

import com.ruoyi.common.annotation.Sensitive;
import com.ruoyi.common.constant.UserConstants;
import com.ruoyi.common.enums.CommonNormalDisableEnum;
import com.ruoyi.common.enums.SensitiveStrategy;
import com.ruoyi.common.enums.UserSexEnum;
import com.ruoyi.common.enums.UserTypeEnum;
import com.ruoyi.common.xss.Xss;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

/**
 * 用户信息编辑业务对象
 *
 * @author weibocy
 */

@Data
@NoArgsConstructor
@Schema(description = "用户信息编辑业务对象")
public class SysUserEditBo implements Serializable {

    private static final long serialVersionUID=1L;

    /**
     * 用户ID
     */
    @Schema(description = "用户ID", required = true)
    private Long userId;

    /**
     * 部门ID
     */
    @Schema(description = "部门ID")
    private Long deptId;

    /**
     * 用户账号
     */
    @Schema(description = "用户账号")
    @Xss(message = "用户账号不能包含脚本字符")
    @Size(min = 0, max = 30, message = "用户账号长度不能超过{max}个字符")
    private String userName;

    /**
     * 用户昵称
     */
    @Schema(description = "用户昵称")
    @Xss(message = "用户昵称不能包含脚本字符")
    @Size(min = 0, max = 30, message = "用户昵称长度不能超过{max}个字符")
    private String nickName;

    /**
     * 用户类型（PC=电脑端 ANDROID=安卓app端 IOS=苹果app端 WXAPP=微信小程序端 WXMP=微信公众号端 ALIPAYAPP=支付宝小程序端）
     */
    @Schema(description = "用户类型（PC=电脑端 ANDROID=安卓app端 IOS=苹果app端 WXAPP=微信小程序端 WXMP=微信公众号端 ALIPAYAPP=支付宝小程序端）")
    private UserTypeEnum userType;

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
     * 用户性别（UNKNOWN=未知 MAN=男 WOMAN=女）
     */
    @Schema(description = "用户性别（UNKNOWN=未知 MAN=男 WOMAN=女）")
    private UserSexEnum sex;

    /**
     * 用户头像
     */
    @Schema(description = "用户头像")
    private String avatar;

    /**
     * 密码
     */
    @Schema(description = "密码")
    private String password;

    /**
     * 帐号状态（NORMAL=正常 DISABLE=停用）
     */
    @Schema(description = "帐号状态（NORMAL=正常 DISABLE=停用）")
    private CommonNormalDisableEnum status;

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

    public boolean isAdmin() {
        return UserConstants.ADMIN_ID.equals(this.userId);
    }

}
