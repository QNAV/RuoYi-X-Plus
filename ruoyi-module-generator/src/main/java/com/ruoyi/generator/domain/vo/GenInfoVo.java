package com.ruoyi.generator.domain.vo;

import com.ruoyi.generator.domain.GenTable;
import com.ruoyi.generator.domain.GenTableColumn;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;

/**
 * 代码生成业务信息对象
 * @author weibocy
 */
@Schema(description = "代码生成业务信息对象")
@Data
public class GenInfoVo {

    /**
     * 查询到的生成业务信息
     */
    @Schema(description = "查询到的生成业务信息", required = true)
    private GenTable info;

    /**
     * 查询到的生成业务字段列表
     */
    @Schema(description = "查询到的生成业务字段列表", required = true)
    private List<GenTableColumn> rows;

    /**
     * 所有生成业务信息列表
     */
    @Schema(description = "所有生成业务信息列表", required = true)
    private List<GenTable> tables;
}
