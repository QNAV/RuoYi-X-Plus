package com.ruoyi.system.domain.vo;

import java.util.Date;

import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;
import com.ruoyi.common.annotation.ExcelEnumFormat;
import com.ruoyi.common.convert.ExcelEnumConvert;
import com.ruoyi.common.enums.LoginActionEnum;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;


/**
 * 业务用户登录记录视图对象 biz_logininfor
 *
 * @author weibocy
 * @date 2022-10-26
 */
@Data
@Schema(description = "业务用户登录记录视图对象")
@ExcelIgnoreUnannotated
public class BizLogininforVo {

    private static final long serialVersionUID = 1L;

    /**
     * 访问ID
     */
    @ExcelProperty(value = "访问ID")
    @Schema(description = "访问ID", required = true)
    private Long infoId;

    /**
     * 用户名(也可能是手机号等)
     */
    @ExcelProperty(value = "用户名(也可能是手机号等)")
    @Schema(description = "用户名(也可能是手机号等)", required = true)
    private String userName;

    /**
     * 登录IP地址
     */
    @ExcelProperty(value = "登录IP地址")
    @Schema(description = "登录IP地址", required = true)
    private String ipaddr;

    /**
     * 登录地点
     */
    @ExcelProperty(value = "登录地点")
    @Schema(description = "登录地点")
    private String loginLocation;

    /**
     * 浏览器类型
     */
    @ExcelProperty(value = "浏览器类型")
    @Schema(description = "浏览器类型")
    private String browser;

    /**
     * 操作系统
     */
    @ExcelProperty(value = "操作系统")
    @Schema(description = "操作系统")
    private String os;

    /**
     * 登录状态（LOGINOK=登录成功 LOGINFAIL=登录失败 LOGOUT=注销登录 REGISTER=注册）
     */
    @ExcelProperty(value = "登录状态", converter = ExcelEnumConvert.class)
    @ExcelEnumFormat(enumClass = LoginActionEnum.class)
    @Schema(description = "登录状态（LOGINOK=登录成功 LOGINFAIL=登录失败 LOGOUT=注销登录 REGISTER=注册）", required = true)
    private LoginActionEnum status;

    /**
     * 提示消息
     */
    @ExcelProperty(value = "提示消息")
    @Schema(description = "提示消息")
    private String msg;

    /**
     * 访问时间
     */
    @ExcelProperty(value = "访问时间")
    @Schema(description = "访问时间", required = true)
    private Date loginTime;


}
