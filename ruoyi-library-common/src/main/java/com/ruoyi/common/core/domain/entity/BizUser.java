package com.ruoyi.common.core.domain.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.ruoyi.common.annotation.Sensitive;
import com.ruoyi.common.core.domain.BaseEntity;
import com.ruoyi.common.enums.*;
import com.ruoyi.common.xss.Xss;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Date;

/**
 * 业务用户实体对象 biz_user
 *
 * @author weibocy
 */

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@TableName("biz_user")
@Schema(description = "业务用户实体对象")
public class BizUser extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 用户ID
     */
    @Schema(description = "用户ID")
    @TableId(value = "user_id")
    private Long userId;

    /**
     * appid
     */
    @Schema(description = "appid")
    private String appid;

    /**
     * unionid
     */
    @Schema(description = "unionid")
    private String unionid;

    /**
     * openid
     */
    @Schema(description = "openid")
    private String openid;

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

    /** 用户性别（UNKNOWN=未知 MAN=男 WOMAN=女） */
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

    /** 国家 */
    @Schema(description = "国家")
    private String country;

    /** 省份 */
    @Schema(description = "省份")
    private String province;

    /** 城市 */
    @Schema(description = "城市")
    private String city;

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
     * 删除标志（EXIST=代表存在 DELETED=代表删除）
     */
    @Schema(description = "删除标志（EXIST=代表存在 DELETED=代表删除）")
    @TableLogic
    private DeleteStatusEnum delFlag;


    public BizUser(Long userId) {
        this.userId = userId;
    }


}
