package com.ruoyi.web.model.dto;

import com.ruoyi.common.core.domain.entity.SysRole;
import com.ruoyi.common.core.domain.entity.SysUser;
import com.ruoyi.system.domain.SysPost;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * 用户详情返回对象
 * @author weibocy
 */
@ApiModel(value = "UserDetailDTO", description = "用户详情返回对象")
@Data
public class UserDetailDTO {

    /**
     * 角色列表
     */
    @ApiModelProperty("角色列表")
    private List<SysRole> roles;

    /**
     * 岗位列表
     */
    @ApiModelProperty("岗位列表")
    private List<SysPost> posts;

    /**
     * 用户信息业务对象
     */
    @ApiModelProperty("用户信息业务对象")
    private SysUser user;

    /**
     * 选中岗位ID列表
     */
    @ApiModelProperty("岗位ID列表")
    private List<Long> postIds;

    /**
     * 角色ID列表
     */
    @ApiModelProperty("角色ID列表")
    private List<Long> roleIds;
}
