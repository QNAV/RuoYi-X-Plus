package com.ruoyi.system.domain.bo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.Date;

/**
 * 业务用户登录记录业务新增对象 biz_logininfor
 *
 * @author weibocy
 * @date 2022-10-26
 */

@Data
@Schema(description = "业务用户登录记录业务新增对象")
public class BizLogininforAddBo implements Serializable {

    private static final long serialVersionUID=1L;

    /**
     * 用户名(也可能是手机号等)
     */
    @Schema(description = "用户名(也可能是手机号等)", required = true)
    @NotBlank(message = "用户名(也可能是手机号等)不能为空")
    private String userName;

    /**
     * 登录IP地址
     */
    @Schema(description = "登录IP地址", required = true)
    @NotBlank(message = "登录IP地址不能为空")
    private String ipaddr;

    /**
     * 登录地点
     */
    @Schema(description = "登录地点")
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
     * 登录状态（0成功 1失败）
     */
    @Schema(description = "登录状态（0成功 1失败）", required = true)
    @NotBlank(message = "登录状态（0成功 1失败）不能为空")
    private String status;

    /**
     * 提示消息
     */
    @Schema(description = "提示消息")
    private String msg;

    /**
     * 访问时间
     */
    @Schema(description = "访问时间", required = true)
    @NotNull(message = "访问时间不能为空")
    private Date loginTime;


}
