package com.ruoyi.common.core.domain.bo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

/**
 * 修改密码表单对象
 * @author weibocy
 */
@ApiModel(value = "UpdatePwdBo", description = "修改密码表单对象")
@Data
public class UpdatePwdBo implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 原密码
     */
    @ApiModelProperty(value = "原密码", required = true)
    @NotBlank(message = "原密码不能为空")
    private String oldPassword;

    /**
     * 新密码
     */
    @ApiModelProperty(value = "新密码", required = true)
    @NotBlank(message = "新密码不能为空")
    private String newPassword;

}
