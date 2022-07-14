package com.ruoyi.common.core.domain.bo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * 短信登录对象
 *
 * @author weibocy
 */

@Data
@ApiModel(value = "SmsLoginBo", description = "短信登录对象")
public class SmsLoginBo {

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

}
