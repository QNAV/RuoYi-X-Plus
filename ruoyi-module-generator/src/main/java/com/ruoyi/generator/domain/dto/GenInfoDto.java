package com.ruoyi.generator.domain.dto;

import com.ruoyi.generator.domain.GenTable;
import com.ruoyi.generator.domain.GenTableColumn;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * 代码生成业务信息对象
 * @author weibocy
 */
@ApiModel(value = "GenInfoDto", description = "代码生成业务信息对象")
@Data
public class GenInfoDto {

    /**
     * 查询到的生成业务信息
     */
    @ApiModelProperty(value = "查询到的生成业务信息", required = true)
    private GenTable info;

    /**
     * 查询到的生成业务字段列表
     */
    @ApiModelProperty(value = "查询到的生成业务字段列表", required = true)
    private List<GenTableColumn> rows;

    /**
     * 所有生成业务信息列表
     */
    @ApiModelProperty(value = "所有生成业务信息列表", required = true)
    private List<GenTable> tables;
}
