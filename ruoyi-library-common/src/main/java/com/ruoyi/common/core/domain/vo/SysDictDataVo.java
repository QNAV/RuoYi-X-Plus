package com.ruoyi.common.core.domain.vo;

import com.ruoyi.common.core.domain.BaseVo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 字典数据视图对象
 *
 * @author weibocy
 */

@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "SysDictDataVo", description = "字典数据视图对象", parent = BaseVo.class)
public class SysDictDataVo extends BaseVo {

    private static final long serialVersionUID = 1L;

    /**
     * 字典编码
     */
    @ApiModelProperty(value = "字典编码", required = true)
    private Long dictCode;

    /**
     * 字典排序
     */
    @ApiModelProperty(value = "字典排序", required = true)
    private Integer dictSort;

    /**
     * 字典标签
     */
    @ApiModelProperty(value = "字典标签", required = true)
    private String dictLabel;

    /**
     * 字典键值
     */
    @ApiModelProperty(value = "字典键值", required = true)
    private String dictValue;

    /**
     * 字典类型
     */
    @ApiModelProperty(value = "字典类型", required = true)
    private String dictType;

    /**
     * 样式属性（其他样式扩展）
     */
    @ApiModelProperty(value = "样式属性（其他样式扩展）")
    private String cssClass;

    /**
     * 表格字典样式
     */
    @ApiModelProperty(value = "表格字典样式")
    private String listClass;

    /**
     * 是否默认（Y是 N否）
     */
    @ApiModelProperty(value = "是否默认（Y是 N否）", required = true)
    private String isDefault;

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
