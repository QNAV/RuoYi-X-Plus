package com.ruoyi.system.domain.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 当前在线会话视图对象
 *
 * @author weibocy
 */

@Data
@ApiModel(value = "SysUserOnlineVo", description = "当前在线会话视图对象")
public class SysUserOnlineVo {

    /**
     * 会话编号
     */
    @ApiModelProperty(value = "会话编号", required = true)
    private String tokenId;

    /**
     * 部门名称
     */
    @ApiModelProperty(value = "部门名称")
    private String deptName;

    /**
     * 用户名称
     */
    @ApiModelProperty(value = "用户名称", required = true)
    private String userName;

    /**
     * 登录IP地址
     */
    @ApiModelProperty(value = "登录IP地址", required = true)
    private String ipaddr;

    /**
     * 登录地址
     */
    @ApiModelProperty(value = "登录地址")
    private String loginLocation;

    /**
     * 浏览器类型
     */
    @ApiModelProperty(value = "浏览器类型", required = true)
    private String browser;

    /**
     * 操作系统
     */
    @ApiModelProperty(value = "操作系统", required = true)
    private String os;

    /**
     * 登录时间
     */
    @ApiModelProperty(value = "登录时间", required = true)
    private Long loginTime;

}
