package com.ruoyi.system.domain.bo;

import com.ruoyi.common.core.domain.bo.PageQuery;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * 系统访问记录分页查询对象
 * @author weibocy
 */
@ApiModel(value = "SysLogininforPageQueryBo", description = "系统访问记录分页查询对象", parent = PageQuery.class)
@Data
public class SysLogininforPageQueryBo extends PageQuery {

    private static final long serialVersionUID = 1L;

    /**
     * 用户账号
     */
    @ApiModelProperty(value = "用户账号")
    private String userName;

    /**
     * 登录状态 0成功 1失败
     */
    @ApiModelProperty(value = "登录状态 0成功 1失败")
    private String status;

    /**
     * 登录IP地址
     */
    @ApiModelProperty(value = "登录IP地址")
    private String ipaddr;

    /**
     * 开始时间
     */
    @ApiModelProperty(value = "开始时间")
    private Date beginTime;

    /**
     * 结束时间
     */
    @ApiModelProperty(value = "结束时间")
    private Date endTime;

}
