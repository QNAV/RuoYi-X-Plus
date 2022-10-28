package com.ruoyi.system.domain.bo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.Date;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * 业务用户信息业务新增对象 biz_user
 *
 * @author weibocy
 * @date 2022-10-25
 */

@Data
@ApiModel(value = "BizUserAddBo", description = "业务用户信息业务新增对象")
public class BizUserAddBo implements Serializable {

    private static final long serialVersionUID=1L;

    /**
     * 用户ID
     */
    @ApiModelProperty(hidden = true)
    private Long userId;

    /**
     * appid
     */
    @ApiModelProperty(value = "appid")
    private String appid;

    /**
     * unionid
     */
    @ApiModelProperty(value = "unionid")
    private String unionid;

    /**
     * openid
     */
    @ApiModelProperty(value = "openid")
    private String openid;

    /**
     * 用户账号
     */
    @ApiModelProperty(value = "用户账号", required = true)
    @NotBlank(message = "用户账号不能为空")
    private String userName;

    /**
     * 用户昵称
     */
    @ApiModelProperty(value = "用户昵称")
    private String nickName;

    /**
     * 用户类型（app_userAPP用户，wxapp_user微信小程序）
     */
    @ApiModelProperty(value = "用户类型（app_userAPP用户，wxapp_user微信小程序）")
    private String userType;

    /**
     * 用户邮箱
     */
    @ApiModelProperty(value = "用户邮箱")
    private String email;

    /**
     * 手机号码
     */
    @ApiModelProperty(value = "手机号码", required = true)
    @NotBlank(message = "手机号码不能为空")
    private String phoneNumber;

    /**
     * 用户性别（1男 2女 0未知）
     */
    @ApiModelProperty(value = "用户性别（1男 2女 0未知）")
    private String sex;

    /**
     * 头像地址
     */
    @ApiModelProperty(value = "头像地址")
    private String avatar;

    /**
     * 密码
     */
    @ApiModelProperty(value = "密码")
    private String password;

    /**
     * 帐号状态（0正常 1停用）
     */
    @ApiModelProperty(value = "帐号状态（0正常 1停用）")
    private String status;

    /**
     * 国家
     */
    @ApiModelProperty(value = "国家")
    private String country;

    /**
     * 省份
     */
    @ApiModelProperty(value = "省份")
    private String province;

    /**
     * 城市
     */
    @ApiModelProperty(value = "城市")
    private String city;

    /**
     * 删除标志（0代表存在 2代表删除）
     */
    @ApiModelProperty(value = "删除标志（0代表存在 2代表删除）")
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


}
