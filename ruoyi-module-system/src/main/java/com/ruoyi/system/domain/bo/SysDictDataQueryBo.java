package com.ruoyi.system.domain.bo;

import com.ruoyi.common.enums.CommonNormalDisableEnum;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;

/**
 * 字典数据查询对象
 * @author weibocy
 */
@Schema(description = "字典数据查询对象")
@Data
public class SysDictDataQueryBo implements Serializable {

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
     * 状态（NORMAL=正常 DISABLE=停用）
     */
    @Schema(description = "状态（NORMAL=正常 DISABLE=停用）")
    private CommonNormalDisableEnum status;

}
