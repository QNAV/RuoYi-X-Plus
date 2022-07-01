package com.ruoyi.system.domain.to;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * 授权用户角色表单对象
 * @author weibocy
 */
@ApiModel(value = "AuthRoleAllBody", description = "授权用户角色表单对象")
@Data
public class AuthRoleAllBody {

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
