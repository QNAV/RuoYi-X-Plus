package com.ruoyi.system.domain.to;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 字典数据查询对象
 * @author weibocy
 */
@ApiModel("字典数据查询对象")
@Data
public class SysDictDataQuery {

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
