package com.ruoyi.system.domain.bo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * 字典数据查询对象
 * @author weibocy
 */
@ApiModel(value = "SysDictDataQueryBo", description = "字典数据查询对象")
@Data
public class SysDictDataQueryBo implements Serializable {

    private static final long serialVersionUID=1L;

    /**
     * 字典标签
     */
    @ApiModelProperty(value = "字典标签")
    private String dictLabel;


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

}
