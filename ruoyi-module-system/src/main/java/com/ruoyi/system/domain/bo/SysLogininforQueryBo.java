package com.ruoyi.system.domain.bo;

import com.ruoyi.common.enums.CommonResult;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 系统访问记录查询对象
 * @author weibocy
 */
@Schema(description = "系统访问记录查询对象")
@Data
public class SysLogininforQueryBo implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 用户账号
     */
    @Schema(description = "用户账号")
    private String userName;

    /**
     * 登录状态（SUCCESS=成功 FAIL=失败）
     */
    @Schema(description = "登录状态（SUCCESS=成功 FAIL=失败）")
    private CommonResult status;

    /**
     * 登录IP地址
     */
    @Schema(description = "登录IP地址")
    private String ipaddr;

    /**
     * 开始时间
     */
    @Schema(description = "开始时间")
    private Date beginTime;

    /**
     * 结束时间
     */
    @Schema(description = "结束时间")
    private Date endTime;

}
