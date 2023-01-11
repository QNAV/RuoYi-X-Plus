package com.ruoyi.system.domain.bo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * 授权用户角色表单对象
 * @author ruoyi
 */
@ApiModel(value = "AuthRoleAllBo", description = "授权用户角色表单对象")
@Data
public class AuthRoleAllBo implements Serializable {

    private static final long serialVersionUID=1L;

    /**
     * 用户Id
     */
    @ApiModelProperty(value = "用户Id", required = true)
    @NotNull
    private Long userId;

    /**
     * 角色ID串
     */
    @ApiModelProperty(value = "角色ID串", required = true)
    @NotNull
    private Long[] roleIds;
}
