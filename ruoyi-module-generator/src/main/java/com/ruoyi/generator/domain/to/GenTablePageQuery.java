package com.ruoyi.generator.domain.to;

import com.ruoyi.common.core.domain.bo.PageQuery;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.Date;

/**
 * 代码生成分页查询对象
 * @author weibocy
 */
@Schema(description = "代码生成分页查询对象")
@Data
public class GenTablePageQuery extends PageQuery {

    /**
     * 表名称
     */
    @Schema(description = "表名称")
    private String tableName;

    /**
     * 表描述
     */
    @Schema(description = "表描述")
    private String tableComment;


    /**
     * 开始创建时间
     */
    @Schema(description = "开始创建时间")
    private Date beginCreateTime;

    /**
     * 结束创建时间
     */
    @Schema(description = "结束创建时间")
    private Date endCreateTime;


}
