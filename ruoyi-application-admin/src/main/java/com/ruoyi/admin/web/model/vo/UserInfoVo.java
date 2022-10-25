package com.ruoyi.admin.web.model.vo;

import com.ruoyi.common.core.domain.entity.SysUser;
import com.ruoyi.common.core.domain.vo.SysUserVo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Set;

/**
 * 用户信息返回对象
 * @author weibocy
 */
@Data
@ApiModel(value = "UserInfoVo", description = "用户信息返回对象")
public class UserInfoVo {

    /**
     * 用户信息业务对象
     */
    @ApiModelProperty(value = "用户信息业务对象", required = true)
    private SysUserVo user;

    /**
     * 角色集合
     */
    @ApiModelProperty(value = "角色集合", required = true)
    private Set<String> roles;

    /**
     * 权限集合
     */
    @ApiModelProperty(value = "权限集合", required = true)
    private Set<String> permissions;

}
