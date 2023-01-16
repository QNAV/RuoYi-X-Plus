package com.ruoyi.common.core.domain.bo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

/**
 * 短信登录对象
 *
 * @author weibocy
 */

@Data
@Schema(description = "短信登录对象")
public class SmsLoginBo implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 用户名
     */
    @NotBlank(message = "{user.phoneNumber.not.blank}")
    @Schema(description = "用户手机号")
    private String phoneNumber;

    /**
     * 用户密码
     */
    @NotBlank(message = "{sms.code.not.blank}")
    @Schema(description = "短信验证码")
    private String smsCode;

}
