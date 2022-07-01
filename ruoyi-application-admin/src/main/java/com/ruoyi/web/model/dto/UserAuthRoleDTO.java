package com.ruoyi.web.model.dto;

import com.ruoyi.common.core.domain.entity.SysRole;
import com.ruoyi.common.core.domain.entity.SysUser;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * 用户授权角色返回对象
 * @author weibocy
 */
@Data
@ApiModel(value = "UserAuthRoleDTO", description = "用户授权角色返回对象")
public class UserAuthRoleDTO {

    @ApiModelProperty("用户信息业务对象")
    private SysUser user;

    @ApiModelProperty("授权角色列表")
    private List<SysRole> roles;
}
