package com.ruoyi.web.model.vo;

import com.ruoyi.system.domain.vo.SysPostVo;
import com.ruoyi.common.core.domain.vo.SysRoleVo;
import com.ruoyi.common.core.domain.vo.SysUserVo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * 用户详情返回对象
 * @author weibocy
 */
@ApiModel(value = "UserDetailVo", description = "用户详情返回对象")
@Data
public class UserDetailVo {

    /**
     * 角色列表
     */
    @ApiModelProperty(value = "角色列表", required = true)
    private List<SysRoleVo> roles;

    /**
     * 岗位列表
     */
    @ApiModelProperty(value = "岗位列表", required = true)
    private List<SysPostVo> posts;

    /**
     * 用户信息业务对象
     */
    @ApiModelProperty("用户信息业务对象")
    private SysUserVo user;

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
