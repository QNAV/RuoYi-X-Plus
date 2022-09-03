package com.ruoyi.common.core.domain.vo;

import com.ruoyi.common.core.domain.BaseVo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;


/**
 * 字典类型视图对象
 *
 * @author weibocy
 */

@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "SysDictTypeVo", description = "字典类型视图对象", parent = BaseVo.class)
public class SysDictTypeVo extends BaseVo {

    /**
     * 字典主键
     */
    @ApiModelProperty(value = "字典主键", required = true)
    private Long dictId;

    /**
     * 字典名称
     */
    @ApiModelProperty(value = "字典名称", required = true)
    private String dictName;

    /**
     * 字典类型
     */
    @ApiModelProperty(value = "字典类型", required = true)
    private String dictType;

    /**
     * 状态（0正常 1停用）
     */
    @ApiModelProperty(value = "状态（0正常 1停用）", required = true)
    private String status;

    /**
     * 备注
     */
    @ApiModelProperty(value = "备注")
    private String remark;

}
