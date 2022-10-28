package com.ruoyi.system.domain.bo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * 批量授权用户表单对象
 * @author weibocy
 */
@ApiModel(value = "AuthUserAllBo", description = "批量授权用户表单对象")
@Data
public class AuthUserAllBo implements Serializable {

    private static final long serialVersionUID=1L;

    /**
     * 角色ID
     */
    @ApiModelProperty(value = "角色ID", required = true)
    @NotNull
    private Long roleId;

    /**
     * 用户ID串
     */
    @ApiModelProperty(value = "用户ID串", required = true)
    @NotNull
    private Long[] userIds;
}
