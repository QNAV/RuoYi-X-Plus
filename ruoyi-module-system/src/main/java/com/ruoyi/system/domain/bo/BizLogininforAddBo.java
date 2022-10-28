package com.ruoyi.system.domain.bo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.Date;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * 业务用户登录记录业务新增对象 biz_logininfor
 *
 * @author weibocy
 * @date 2022-10-26
 */

@Data
@ApiModel(value = "BizLogininforAddBo", description = "业务用户登录记录业务新增对象")
public class BizLogininforAddBo implements Serializable {

    private static final long serialVersionUID=1L;

    /**
     * 访问ID
     */
    @ApiModelProperty(hidden = true)
    private Long infoId;

    /**
     * 用户名(也可能是手机号等)
     */
    @ApiModelProperty(value = "用户名(也可能是手机号等)", required = true)
    @NotBlank(message = "用户名(也可能是手机号等)不能为空")
    private String userName;

    /**
     * 登录IP地址
     */
    @ApiModelProperty(value = "登录IP地址", required = true)
    @NotBlank(message = "登录IP地址不能为空")
    private String ipaddr;

    /**
     * 登录地点
     */
    @ApiModelProperty(value = "登录地点")
    private String loginLocation;

    /**
     * 浏览器类型
     */
    @ApiModelProperty(value = "浏览器类型")
    private String browser;

    /**
     * 操作系统
     */
    @ApiModelProperty(value = "操作系统")
    private String os;

    /**
     * 登录状态（0成功 1失败）
     */
    @ApiModelProperty(value = "登录状态（0成功 1失败）", required = true)
    @NotBlank(message = "登录状态（0成功 1失败）不能为空")
    private String status;

    /**
     * 提示消息
     */
    @ApiModelProperty(value = "提示消息")
    private String msg;

    /**
     * 访问时间
     */
    @ApiModelProperty(value = "访问时间", required = true)
    @NotNull(message = "访问时间不能为空")
    private Date loginTime;


}
