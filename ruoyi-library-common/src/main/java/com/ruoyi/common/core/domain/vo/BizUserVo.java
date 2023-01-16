package com.ruoyi.common.core.domain.vo;


import com.ruoyi.common.core.domain.BaseVo;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * 业务用户信息视图对象
 *
 * @author weibocy
 */

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Schema(description = "业务用户信息视图对象")
public class BizUserVo extends BaseVo {

    private static final long serialVersionUID = 1L;

    /**
     * 用户ID
     */
    @Schema(description = "用户ID", required = true)
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
    private String userName;

    /**
     * 用户昵称
     */
    @Schema(description = "用户昵称", required = true)
    private String nickName;

    /**
     * 用户类型（app_userAPP用户，wxapp_user微信小程序）
     */
    @Schema(description = "用户类型（app_userAPP用户，wxapp_user微信小程序）", required = true)
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
    @Schema(description = "密码")
    private String password;

    /**
     * 帐号状态（0正常 1停用）
     */
    @Schema(description = "帐号状态（0正常 1停用）", required = true)
    private String status;

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
     * 删除标志（0代表存在 2代表删除）
     */
    @Schema(description = "删除标志（0代表存在 2代表删除）", required = true)
    private String delFlag;

    /**
     * 最后登录IP
     */
    @Schema(description = "最后登录IP", required = true)
    private String loginIp;

    /**
     * 最后登录时间
     */
    @Schema(description = "最后登录时间", required = true)
    private Date loginDate;

    /**
     * 备注
     */
    @Schema(description = "备注")
    private String remark;


}
