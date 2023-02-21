package com.ruoyi.system.domain;

import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.ruoyi.common.annotation.ExcelEnumFormat;
import com.ruoyi.common.convert.ExcelEnumConvert;
import com.ruoyi.common.enums.LoginActionEnum;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 系统访问记录表 sys_logininfor
 *
 * @author ruoyi
 * @author Lion Li
 */

@Data
@TableName("sys_logininfor")
@ExcelIgnoreUnannotated
@Schema(description = "系统访问记录业务对象")
public class SysLogininfor implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * ID
     */
    @Schema(description = "访问ID")
    @ExcelProperty(value = "序号")
    @TableId(value = "info_id")
    private Long infoId;

    /**
     * 用户账号
     */
    @Schema(description = "用户账号")
    @ExcelProperty(value = "用户账号")
    private String userName;

    /**
     * 登录状态（LOGINOK=登录成功 LOGINFAIL=登录失败 LOGOUT=注销登录 REGISTER=注册）
     */
    @Schema(description = "登录状态登录状态（LOGINOK=登录成功 LOGINFAIL=登录失败 LOGOUT=注销登录 REGISTER=注册）")
    @ExcelProperty(value = "登录状态", converter = ExcelEnumConvert.class)
    @ExcelEnumFormat(enumClass = LoginActionEnum.class)
    private LoginActionEnum status;

    /**
     * 登录IP地址
     */
    @Schema(description = "登录IP地址")
    @ExcelProperty(value = "登录地址")
    private String ipaddr;

    /**
     * 登录地点
     */
    @Schema(description = "登录地点")
    @ExcelProperty(value = "登录地点")
    private String loginLocation;

    /**
     * 浏览器类型
     */
    @Schema(description = "浏览器类型")
    @ExcelProperty(value = "浏览器")
    private String browser;

    /**
     * 操作系统
     */
    @Schema(description = "操作系统")
    @ExcelProperty(value = "操作系统")
    private String os;

    /**
     * 提示消息
     */
    @Schema(description = "提示消息")
    @ExcelProperty(value = "提示消息")
    private String msg;

    /**
     * 访问时间
     */
    @Schema(description = "访问时间")
    @ExcelProperty(value = "访问时间")
    private Date loginTime;

}
