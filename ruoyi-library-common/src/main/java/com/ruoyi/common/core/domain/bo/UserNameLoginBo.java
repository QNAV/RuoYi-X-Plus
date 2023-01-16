package com.ruoyi.common.core.domain.bo;

import com.ruoyi.common.constant.UserConstants;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

/**
 * 用户密码登录表单对象
 *
 * @author weibocy
 */

@Data
@Schema(description = "用户密码登录表单对象")
public class UserNameLoginBo implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 用户名
     */
    @NotBlank(message = "{user.username.not.blank}")
    @Length(min = UserConstants.USERNAME_MIN_LENGTH, max = UserConstants.USERNAME_MAX_LENGTH, message = "{user.username.length.valid}")
    @Schema(description = "用户名", required = true)
    private String username;

    /**
     * 用户密码
     */
    @NotBlank(message = "{user.password.not.blank}")
    @Length(min = UserConstants.PASSWORD_MIN_LENGTH, max = UserConstants.PASSWORD_MAX_LENGTH, message = "{user.password.length.valid}")
    @Schema(description = "用户密码", required = true)
    private String password;

    /**
     * 验证码
     */
    @NotBlank(message = "验证码不能为空")
    @Schema(description = "验证码", required = true)
    private String code;

    /**
     * 唯一标识
     */
    @NotBlank(message = "唯一标识不能为空")
    @Schema(description = "唯一标识", required = true)
    private String uuid;

}
