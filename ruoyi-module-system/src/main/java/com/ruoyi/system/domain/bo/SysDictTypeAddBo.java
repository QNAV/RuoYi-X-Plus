package com.ruoyi.system.domain.bo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;


/**
 * 字典类型新增业务对象
 *
 * @author weibocy
 */

@Data
@ApiModel(value = "SysDictTypeAddBo", description = "字典类型新增业务对象")
public class SysDictTypeAddBo {

    /**
     * 字典名称
     */
    @ApiModelProperty(value = "字典名称", required = true)
    @NotBlank(message = "字典名称不能为空")
    private String dictName;

    /**
     * 字典类型
     */
    @ApiModelProperty(value = "字典类型", required = true)
    @NotBlank(message = "字典类型不能为空")
    private String dictType;

    /**
     * 状态（0正常 1停用）
     */
    @ApiModelProperty(value = "状态（0正常 1停用）")
    private String status;

    /**
     * 备注
     */
    @ApiModelProperty(value = "备注")
    private String remark;

}
