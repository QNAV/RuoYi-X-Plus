package com.ruoyi.system.domain.bo;

import com.ruoyi.common.enums.CommonNormalDisable;
import com.ruoyi.common.enums.UserSexEnum;
import com.ruoyi.common.enums.UserType;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 业务用户信息业务查询对象 biz_user
 *
 * @author weibocy
 * @date 2022-10-25
 */

@Data
@Schema(description = "业务用户信息业务查询对象")
public class BizUserQueryBo implements Serializable {

    private static final long serialVersionUID=1L;

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
    @Schema(description = "用户账号")
    private String userName;

    /**
     * 用户昵称
     */
    @Schema(description = "用户昵称")
    private String nickName;

    /**
     * 用户类型（PC=电脑端 ANDROID=安卓app端 IOS=苹果app端 WXAPP=微信小程序端 WXMP=微信公众号端 ALIPAYAPP=支付宝小程序端）
     */
    @Schema(description = "用户类型（PC=电脑端 ANDROID=安卓app端 IOS=苹果app端 WXAPP=微信小程序端 WXMP=微信公众号端 ALIPAYAPP=支付宝小程序端）")
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
    @Schema(description = "用户性别（UNKNOWN=未知 MAN=男 WOMAN=女）")
    private UserSexEnum sex;

    /**
     * 头像地址
     */
    @Schema(description = "头像地址")
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
    private CommonNormalDisable status;

    /**
     * 国家
     */
    @Schema(description = "国家")
    private String country;

    /**
     * 省份
     */
    @Schema(description = "省份")
    private String province;

    /**
     * 城市
     */
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
     * 创建开始时间
     */
    @Schema(description = "创建开始时间")
    private Date createBeginTime;

    /**
     * 创建结束时间
     */
    @Schema(description = "创建结束时间")
    private Date createEndTime;
}
