package com.ruoyi.common.core.domain.vo;

import com.ruoyi.common.core.domain.BaseVo;
import com.ruoyi.common.enums.CommonNormalDisable;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;


/**
 * 字典类型视图对象
 *
 * @author weibocy
 */

@Data
@EqualsAndHashCode(callSuper = true)
@Schema(description = "字典类型视图对象")
public class SysDictTypeVo extends BaseVo {

    private static final long serialVersionUID = 1L;

    /**
     * 字典主键
     */
    @Schema(description = "字典主键", required = true)
    private Long dictId;

    /**
     * 字典名称
     */
    @Schema(description = "字典名称", required = true)
    private String dictName;

    /**
     * 字典类型
     */
    @Schema(description = "字典类型", required = true)
    private String dictType;

    /**
     * 状态（NORMAL=正常 DISABLE=停用）
     */
    @Schema(description = "状态（NORMAL=正常 DISABLE=停用）", required = true)
    private CommonNormalDisable status;

    /**
     * 备注
     */
    @Schema(description = "备注")
    private String remark;

}
