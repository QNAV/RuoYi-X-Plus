package com.ruoyi.system.domain.bo;

import com.ruoyi.common.core.domain.bo.PageQuery;
import com.ruoyi.common.enums.CommonResult;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.Date;


/**
 * 业务用户登录记录业务分页查询对象 biz_logininfor
 *
 * @author weibocy
 * @date 2022-10-26
 */

@Data
@Schema(description = "业务用户登录记录业务分页查询对象")
public class BizLogininforPageQueryBo extends PageQuery {

    private static final long serialVersionUID=1L;

    /**
     * 用户名(也可能是手机号等)
     */
    @Schema(description = "用户名(也可能是手机号等)")
    private String userName;

    /**
     * 登录IP地址
     */
    @Schema(description = "登录IP地址")
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
     * 登录状态（SUCCESS=成功 FAIL=失败）
     */
    @Schema(description = "登录状态（SUCCESS=成功 FAIL=失败）")
    private CommonResult status;

    /**
     * 提示消息
     */
    @Schema(description = "提示消息")
    private String msg;

    /**
     * 访问时间
     */
    @Schema(description = "访问时间")
    private Date loginTime;

    /**
     * 创建开始时间
     */
    @Schema(description = "创建开始时间")
    private Date createBeginTime;

    /**
     * 创建结束时间
     */
    @Schema(description = "创建结束时间")
    private Date createEndTime;
}
