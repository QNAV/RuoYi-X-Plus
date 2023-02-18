package com.ruoyi.system.domain;

import com.baomidou.mybatisplus.annotation.*;
import com.ruoyi.common.enums.CommonResult;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import java.util.Date;

/**
 * 业务用户登录记录对象 biz_logininfor
 *
 * @author weibocy
 * @date 2022-10-26
 */
@Data
@TableName("biz_logininfor")
@Schema(description = "业务用户登录记录实体对象")
public class BizLogininfor {

    private static final long serialVersionUID=1L;

    /**
     * 访问ID
     */
    @TableId(value = "info_id")
    @Schema(description = "访问ID")
    private Long infoId;

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


}
