package com.ruoyi.common.core.domain.entity;

import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.ruoyi.common.annotation.ExcelDictFormat;
import com.ruoyi.common.annotation.ExcelEnumFormat;
import com.ruoyi.common.constant.UserConstants;
import com.ruoyi.common.convert.ExcelDictConvert;
import com.ruoyi.common.convert.ExcelEnumConvert;
import com.ruoyi.common.core.domain.BaseEntity;
import com.ruoyi.common.enums.CommonNormalDisableEnum;
import com.ruoyi.common.enums.CommonYesOrNoEnum;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

/**
 * 字典数据表 sys_dict_data
 *
 * @author ruoyi
 * @author Lion Li
 */

@Data
@EqualsAndHashCode(callSuper = true)
@TableName("sys_dict_data")
@ExcelIgnoreUnannotated
@Schema(description = "字典数据业务对象")
public class SysDictData extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 字典编码
     */
    @Schema(description = "字典编码")
    @ExcelProperty(value = "字典编码")
    @TableId(value = "dict_code")
    private Long dictCode;

    /**
     * 字典排序
     */
    @Schema(description = "字典排序")
    @ExcelProperty(value = "字典排序")
    private Integer dictSort;

    /**
     * 字典标签
     */
    @Schema(description = "字典标签", required = true)
    @ExcelProperty(value = "字典标签")
    @NotBlank(message = "字典标签不能为空")
    @Size(min = 0, max = 100, message = "字典标签长度不能超过{max}个字符")
    private String dictLabel;

    /**
     * 字典键值
     */
    @Schema(description = "字典键值", required = true)
    @ExcelProperty(value = "字典键值")
    @NotBlank(message = "字典键值不能为空")
    @Size(min = 0, max = 100, message = "字典键值长度不能超过{max}个字符")
    private String dictValue;

    /**
     * 字典类型
     */
    @Schema(description = "字典类型", required = true)
    @ExcelProperty(value = "字典类型")
    @NotBlank(message = "字典类型不能为空")
    @Size(min = 0, max = 100, message = "字典类型长度不能超过{max}个字符")
    private String dictType;

    /**
     * 样式属性（其他样式扩展）
     */
    @Schema(description = "样式属性（其他样式扩展）")
    @Size(min = 0, max = 100, message = "样式属性长度不能超过{max}个字符")
    private String cssClass;

    /**
     * 表格字典样式
     */
    @Schema(description = "表格字典样式")
    private String listClass;

    /**
     * 是否默认（YES=是 NO=否）
     */
    @Schema(description = "是否默认（YES=是 NO=否）")
    @ExcelProperty(value = "是否默认", converter = ExcelEnumConvert.class)
    @ExcelEnumFormat(enumClass = CommonYesOrNoEnum.class)
    private CommonYesOrNoEnum isDefault;

    /**
     * 状态（NORMAL=正常 DISABLE=停用）
     */
    @Schema(description = "状态（NORMAL=正常 DISABLE=停用）")
    @ExcelProperty(value = "状态", converter = ExcelEnumConvert.class)
    @ExcelEnumFormat(enumClass = CommonNormalDisableEnum.class)
    private CommonNormalDisableEnum status;

    /**
     * 备注
     */
    @Schema(description = "备注")
    private String remark;

    public boolean getDefault() {
        return UserConstants.YES.equals(this.isDefault);
    }

}
