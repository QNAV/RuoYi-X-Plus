package com.ruoyi.system.domain.bo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * 操作日志记录查询对象
 * @author weibocy
 */
@ApiModel(value = "SysOperLogQueryBo", description = "操作日志记录查询对象")
@Data
public class SysOperLogQueryBo {

    /**
     * 操作模块
     */
    @ApiModelProperty(value = "操作模块")
    private String title;

    /**
     * 业务类型（0其它 1新增 2修改 3删除）
     */
    @ApiModelProperty(value = "业务类型（0其它 1新增 2修改 3删除）")
    private Integer businessType;

    /**
     * 业务类型数组
     */
    @ApiModelProperty(value = "业务类型数组")
    private Integer[] businessTypes;

    /**
     * 操作人员
     */
    @ApiModelProperty(value = "操作人员")
    private String operName;

    /**
     * 操作状态（0正常 1异常）
     */
    @ApiModelProperty(value = "操作状态（0正常 1异常）")
    private Integer status;


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
