package com.ruoyi.common.core.domain.bo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

/**
 * 修改密码表单对象
 * @author weibocy
 */
@Schema(description = "修改密码表单对象")
@Data
public class UpdatePwdBo implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 原密码
     */
    @Schema(description = "原密码", required = true)
    @NotBlank(message = "原密码不能为空")
    private String oldPassword;

    /**
     * 新密码
     */
    @Schema(description = "新密码", required = true)
    @NotBlank(message = "新密码不能为空")
    private String newPassword;

}
