package com.ruoyi.system.domain.vo;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 系统访问记录视图返回对象
 *
 * @author weibocy
 */

@Data
@ApiModel(value = "SysLogininforVo", description = "系统访问记录视图返回对象")
public class SysLogininforVo implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * ID
     */
    @ApiModelProperty(value = "访问ID", required = true)
    private Long infoId;

    /**
     * 用户账号
     */
    @ApiModelProperty(value = "用户账号", required = true)
    private String userName;

    /**
     * 登录状态 0成功 1失败
     */
    @ApiModelProperty(value = "登录状态 0成功 1失败", required = true)
    private String status;

    /**
     * 登录IP地址
     */
    @ApiModelProperty(value = "登录IP地址", required = true)
    private String ipaddr;

    /**
     * 登录地点
     */
    @ApiModelProperty(value = "登录地点", required = true)
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
     * 提示消息
     */
    @ApiModelProperty(value = "提示消息", required = true)
    private String msg;

    /**
     * 访问时间
     */
    @ApiModelProperty(value = "访问时间", required = true)
    private Date loginTime;

}
