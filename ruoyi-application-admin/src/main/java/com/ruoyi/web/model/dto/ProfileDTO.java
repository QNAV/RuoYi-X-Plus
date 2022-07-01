package com.ruoyi.web.model.dto;

import com.ruoyi.common.core.domain.entity.SysUser;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 个人信息返回对象
 * @author weibocy
 */
@Data
@ApiModel(value = "ProfileDTO", description = "个人信息返回对象")
public class ProfileDTO {

    /**
     * 用户对象信息
     */
    @ApiModelProperty("用户对象信息")
    private SysUser user;

    /**
     * 用户所属角色组
     */
    @ApiModelProperty("用户所属角色组")
    private String roleGroup;

    /**
     * 用户所属岗位组
     */
    @ApiModelProperty("用户所属岗位组")
    private String postGroup;
}
