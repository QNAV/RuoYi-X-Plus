package com.ruoyi.admin.web.model.vo;

import com.ruoyi.common.core.domain.vo.SysUserVo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 个人信息视图对象
 * @author weibocy
 */
@Data
@ApiModel(value = "ProfileVo", description = "个人信息视图对象")
public class ProfileVo {

    /**
     * 用户对象信息
     */
    @ApiModelProperty(value = "用户对象信息", required = true)
    private SysUserVo user;

    /**
     * 用户所属角色组
     */
    @ApiModelProperty(value = "用户所属角色组", required = true)
    private String roleGroup;

    /**
     * 用户所属岗位组
     */
    @ApiModelProperty(value = "用户所属岗位组", required = true)
    private String postGroup;
}
