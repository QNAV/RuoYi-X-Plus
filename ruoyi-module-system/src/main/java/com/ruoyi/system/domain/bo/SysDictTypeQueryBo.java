package com.ruoyi.system.domain.bo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;


/**
 * 字典类型查询对象
 * @author weibocy
 */
@ApiModel(value = "SysDictTypeQueryBo", description = "字典类型查询对象")
@Data
public class SysDictTypeQueryBo implements Serializable {

    private static final long serialVersionUID=1L;

    /**
     * 字典名称
     */
    @ApiModelProperty(value = "字典名称")
    private String dictName;

    /**
     * 字典类型
     */
    @ApiModelProperty(value = "字典类型")
    private String dictType;

    /**
     * 状态（0正常 1停用）
     */
    @ApiModelProperty(value = "状态（0正常 1停用）")
    private String status;


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
