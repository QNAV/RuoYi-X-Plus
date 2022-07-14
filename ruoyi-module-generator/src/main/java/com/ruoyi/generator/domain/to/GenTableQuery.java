package com.ruoyi.generator.domain.to;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.util.Date;

/**
 * 代码生成查询对象
 * @author weibocy
 */
@ApiModel(value = "GenTableQuery", description = "代码生成查询对象")
@Data
public class GenTableQuery {

    /**
     * 表名称
     */
    @ApiModelProperty("表名称")
    private String tableName;

    /**
     * 表描述
     */
    @ApiModelProperty("表描述")
    private String tableComment;


    /**
     * 开始创建时间
     */
    @ApiModelProperty(value = "开始创建时间")
    private Date beginCreateTime;

    /**
     * 结束创建时间
     */
    @ApiModelProperty(value = "结束创建时间")
    private Date endCreateTime;


}
