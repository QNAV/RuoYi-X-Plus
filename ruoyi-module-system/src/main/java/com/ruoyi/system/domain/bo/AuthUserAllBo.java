package com.ruoyi.system.domain.bo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * 批量授权用户表单对象
 * @author ruoyi
 */
@Schema(description = "批量授权用户表单对象")
@Data
public class AuthUserAllBo implements Serializable {

    private static final long serialVersionUID=1L;

    /**
     * 角色ID
     */
    @Schema(description = "角色ID", required = true)
    @NotNull
    private Long roleId;

    /**
     * 用户ID串
     */
    @Schema(description = "用户ID串", required = true)
    @NotNull
    private Long[] userIds;
}
