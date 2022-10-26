package com.ruoyi.system.domain.vo;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;
import com.ruoyi.common.annotation.ExcelDictFormat;
import com.ruoyi.common.convert.ExcelDictConvert;
import com.ruoyi.common.core.domain.BaseVo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import java.util.Date;



/**
 * 业务用户登录记录视图对象 biz_logininfor
 *
 * @author weibocy
 * @date 2022-10-26
 */
@Data
@ApiModel(value = "BizLogininforVo", description = "业务用户登录记录视图对象", parent = BaseVo.class)
@ExcelIgnoreUnannotated
public class BizLogininforVo extends BaseVo {

    private static final long serialVersionUID = 1L;

    /**
     * 访问ID
     */
    @ExcelProperty(value = "访问ID")
    @ApiModelProperty(value = "访问ID", required = true)
    private Long infoId;

    /**
     * 用户名(也可能是手机号等)
     */
    @ExcelProperty(value = "用户名(也可能是手机号等)")
    @ApiModelProperty(value = "用户名(也可能是手机号等)", required = true)
    private String userName;

    /**
     * 登录IP地址
     */
    @ExcelProperty(value = "登录IP地址")
    @ApiModelProperty(value = "登录IP地址", required = true)
    private String ipaddr;

    /**
     * 登录地点
     */
    @ExcelProperty(value = "登录地点")
    @ApiModelProperty(value = "登录地点")
    private String loginLocation;

    /**
     * 浏览器类型
     */
    @ExcelProperty(value = "浏览器类型")
    @ApiModelProperty(value = "浏览器类型")
    private String browser;

    /**
     * 操作系统
     */
    @ExcelProperty(value = "操作系统")
    @ApiModelProperty(value = "操作系统")
    private String os;

    /**
     * 登录状态（0成功 1失败）
     */
    @ExcelProperty(value = "登录状态", converter = ExcelDictConvert.class)
    @ExcelDictFormat(readConverterExp = "0=成功,1=失败")
    @ApiModelProperty(value = "登录状态（0成功 1失败）", required = true)
    private String status;

    /**
     * 提示消息
     */
    @ExcelProperty(value = "提示消息")
    @ApiModelProperty(value = "提示消息")
    private String msg;

    /**
     * 访问时间
     */
    @ExcelProperty(value = "访问时间")
    @ApiModelProperty(value = "访问时间", required = true)
    private Date loginTime;


}
