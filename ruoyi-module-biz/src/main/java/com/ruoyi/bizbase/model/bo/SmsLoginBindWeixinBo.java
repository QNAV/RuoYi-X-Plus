package com.ruoyi.bizbase.model.bo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * 短信登录并绑定微信对象
 *
 * @author weibocy
 */

@Data
@ApiModel(value = "SmsLoginBindWeixinBo", description = "短信登录并绑定微信对象")
public class SmsLoginBindWeixinBo {
    /**
     * 用户名
     */
    @NotBlank(message = "{user.phoneNumber.not.blank}")
    @ApiModelProperty(value = "用户手机号")
    private String phoneNumber;

    /**
     * 用户密码
     */
    @NotBlank(message = "{sms.code.not.blank}")
    @ApiModelProperty(value = "短信验证码")
    private String smsCode;


    /**
     * 小程序应用id
     */
    @NotBlank(message = "小程序应用id不能为空")
    @ApiModelProperty(value = "小程序应用id")
    private String appid;


    /**
     * 小程序code
     */
    @NotBlank(message = "小程序code不能为空")
    @ApiModelProperty(value = "小程序code")
    private String xcxCode;
}
