package com.ruoyi.system.domain.vo;


import com.alibaba.excel.annotation.ExcelProperty;
import com.ruoyi.common.annotation.ExcelEnumFormat;
import com.ruoyi.common.convert.ExcelEnumConvert;
import com.ruoyi.common.enums.LoginActionEnum;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 系统访问记录视图返回对象
 *
 * @author weibocy
 */

@Data
@Schema(description = "系统访问记录视图返回对象")
public class SysLogininforVo implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * ID
     */
    @Schema(description = "访问ID", required = true)
    private Long infoId;

    /**
     * 用户账号
     */
    @Schema(description = "用户账号", required = true)
    private String userName;

    /**
     * 登录状态（LOGINOK=登录成功 LOGINFAIL=登录失败 LOGOUT=注销登录 REGISTER=注册）
     */
    @Schema(description = "登录状态（LOGINOK=登录成功 LOGINFAIL=登录失败 LOGOUT=注销登录 REGISTER=注册）", required = true)
    @ExcelProperty(value = "登录状态", converter = ExcelEnumConvert.class)
    @ExcelEnumFormat(enumClass = LoginActionEnum.class)
    private LoginActionEnum status;

    /**
     * 登录IP地址
     */
    @Schema(description = "登录IP地址", required = true)
    private String ipaddr;

    /**
     * 登录地点
     */
    @Schema(description = "登录地点", required = true)
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
     * 提示消息
     */
    @Schema(description = "提示消息", required = true)
    private String msg;

    /**
     * 访问时间
     */
    @Schema(description = "访问时间", required = true)
    private Date loginTime;

}
