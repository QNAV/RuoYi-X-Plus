package com.ruoyi.system.domain;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * 当前在线会话
 *
 * @author ruoyi
 * @author Lion Li
 */

@Data
@Schema(description = "当前在线会话业务对象")
public class SysUserOnline {

    /**
     * 会话编号
     */
    @Schema(description = "会话编号")
    private String tokenId;

    /**
     * 部门名称
     */
    @Schema(description = "部门名称")
    private String deptName;

    /**
     * 用户名称
     */
    @Schema(description = "用户名称")
    private String userName;

    /**
     * 登录IP地址
     */
    @Schema(description = "登录IP地址")
    private String ipaddr;

    /**
     * 登录地址
     */
    @Schema(description = "登录地址")
    private String loginLocation;

    /**
     * 浏览器类型
     */
    @Schema(description = "浏览器类型")
    private String browser;

    /**
     * 操作系统
     */
    @Schema(description = "操作系统")
    private String os;

    /**
     * 登录时间
     */
    @Schema(description = "登录时间")
    private Long loginTime;

}
