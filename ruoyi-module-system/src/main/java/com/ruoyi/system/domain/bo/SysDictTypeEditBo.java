package com.ruoyi.system.domain.bo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;


/**
 * 字典类型编辑业务对象
 *
 * @author weibocy
 */

@Data
@Schema(description = "字典类型编辑业务对象")
public class SysDictTypeEditBo implements Serializable {

    private static final long serialVersionUID=1L;

    /**
     * 字典主键
     */
    @Schema(description = "字典主键", required = true)
    @NotNull(message = "字典主键不能为空")
    private Long dictId;

    /**
     * 字典名称
     */
    @Schema(description = "字典名称")
    private String dictName;

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

    /**
     * 备注
     */
    @Schema(description = "备注")
    private String remark;

}
