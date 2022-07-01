package com.ruoyi.web.model.dto;

import com.ruoyi.common.core.domain.entity.SysUser;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Set;

/**
 * 用户信息返回对象
 * @author weibocy
 */
@Data
@ApiModel(value = "UserInfoDTO", description = "用户信息返回对象")
public class UserInfoDTO {

    /**
     * 用户信息业务对象
     */
    @ApiModelProperty("用户信息业务对象")
    private SysUser user;

    /**
     * 角色集合
     */
    @ApiModelProperty("角色集合")
    private Set<String> roles;

    /**
     * 权限集合
     */
    @ApiModelProperty("权限集合")
    private Set<String> permissions;

}
