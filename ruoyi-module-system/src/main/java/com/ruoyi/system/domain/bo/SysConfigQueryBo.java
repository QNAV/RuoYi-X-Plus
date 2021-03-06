package com.ruoyi.system.domain.bo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * 参数配置查询对象
 * @author weibocy
 */
@ApiModel(value = "SysConfigQueryBo", description = "参数配置查询对象")
@Data
public class SysConfigQueryBo {

    /**
     * 参数名称
     */
    @ApiModelProperty(value = "参数名称")
    private String configName;

    /**
     * 参数键名
     */
    @ApiModelProperty(value = "参数键名")
    private String configKey;

    /**
     * 系统内置（Y是 N否）
     */
    @ApiModelProperty(value = "系统内置（Y是 N否）")
    private String configType;

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
