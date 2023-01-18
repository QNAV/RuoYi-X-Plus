package com.ruoyi.system.domain.bo;

import com.ruoyi.common.core.domain.bo.PageQuery;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * 字典数据分页查询对象
 * @author weibocy
 */
@Schema(description = "字典数据分页查询对象")
@Data
public class SysDictDataPageQueryBo extends PageQuery {

    private static final long serialVersionUID=1L;

    /**
     * 字典标签
     */
    @Schema(description = "字典标签")
    private String dictLabel;


    /**
     * 字典类型
     */
    @Schema(description = "字典类型")
    private String dictType;


    /**
     * 状态（0正常 1停用）
     */
    @Schema(description = "状态（0正常 1停用）")
    private String status;

}
