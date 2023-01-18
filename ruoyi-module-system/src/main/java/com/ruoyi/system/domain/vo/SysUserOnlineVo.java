package com.ruoyi.system.domain.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;

/**
 * 当前在线会话视图对象
 *
 * @author weibocy
 */

@Data
@Schema(description = "当前在线会话视图对象")
public class SysUserOnlineVo implements Serializable {

    private static final long serialVersionUID=1L;

    /**
     * 会话编号
     */
    @Schema(description = "会话编号", required = true)
    private String tokenId;

    /**
     * 部门名称
     */
    @Schema(description = "部门名称")
    private String deptName;

    /**
     * 用户名称
     */
    @Schema(description = "用户名称", required = true)
    private String userName;

    /**
     * 登录IP地址
     */
    @Schema(description = "登录IP地址", required = true)
    private String ipaddr;

    /**
     * 登录地址
     */
    @Schema(description = "登录地址")
    private String loginLocation;

    /**
     * 浏览器类型
     */
    @Schema(description = "浏览器类型", required = true)
    private String browser;

    /**
     * 操作系统
     */
    @Schema(description = "操作系统", required = true)
    private String os;

    /**
     * 登录时间
     */
    @Schema(description = "登录时间", required = true)
    private Long loginTime;

}
