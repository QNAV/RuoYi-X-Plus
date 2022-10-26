package com.ruoyi.system.domain;

import com.baomidou.mybatisplus.annotation.*;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import java.io.Serializable;
import java.util.Date;
import java.math.BigDecimal;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 业务用户登录记录对象 biz_logininfor
 *
 * @author weibocy
 * @date 2022-10-26
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("biz_logininfor")
@ApiModel(value = "BizLogininfor", description = "业务用户登录记录实体对象", parent = BaseEntity.class)
public class BizLogininfor extends BaseEntity {

    private static final long serialVersionUID=1L;

    /**
     * 访问ID
     */
    @TableId(value = "info_id")
    @ApiModelProperty(value = "访问ID")
    private Long infoId;

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


}
