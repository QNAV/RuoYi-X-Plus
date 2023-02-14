package com.ruoyi.system.domain.bo;

import com.ruoyi.common.enums.CommonNormalDisable;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;


/**
 * 字典类型新增业务对象
 *
 * @author weibocy
 */

@Data
@Schema(description = "字典类型新增业务对象")
public class SysDictTypeAddBo implements Serializable {

    private static final long serialVersionUID=1L;

    /**
     * 字典名称
     */
    @Schema(description = "字典名称", required = true)
    @NotBlank(message = "字典名称不能为空")
    private String dictName;

    /**
     * 字典类型
     */
    @Schema(description = "字典类型", required = true)
    @NotBlank(message = "字典类型不能为空")
    private String dictType;

    /**
     * 状态（NORMAL=正常 DISABLE=停用）
     */
    @Schema(description = "状态（NORMAL=正常 DISABLE=停用）")
    private CommonNormalDisable status;

    /**
     * 备注
     */
    @Schema(description = "备注")
    private String remark;

}
