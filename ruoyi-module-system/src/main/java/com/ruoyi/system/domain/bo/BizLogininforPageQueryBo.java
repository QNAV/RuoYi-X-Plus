package com.ruoyi.system.domain.bo;

import com.ruoyi.common.core.domain.bo.PageQuery;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import javax.validation.constraints.*;

import java.util.Date;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * 业务用户登录记录业务分页查询对象 biz_logininfor
 *
 * @author weibocy
 * @date 2022-10-26
 */

@Data
@ApiModel(value = "BizLogininforPageQueryBo", description = "业务用户登录记录业务分页查询对象", parent = PageQuery.class)
public class BizLogininforPageQueryBo extends PageQuery {

    /**
     * 用户名(也可能是手机号等)
     */
    @ApiModelProperty(value = "用户名(也可能是手机号等)")
    private String userName;

    /**
     * 登录IP地址
     */
    @ApiModelProperty(value = "登录IP地址")
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
    @ApiModelProperty(value = "登录状态（0成功 1失败）")
    private String status;

    /**
     * 提示消息
     */
    @ApiModelProperty(value = "提示消息")
    private String msg;

    /**
     * 访问时间
     */
    @ApiModelProperty(value = "访问时间")
    private Date loginTime;

    /**
     * 创建开始时间
     */
    @ApiModelProperty(value = "创建开始时间")
    private Date createBeginTime;

    /**
     * 创建结束时间
     */
    @ApiModelProperty(value = "创建结束时间")
    private Date createEndTime;
}
