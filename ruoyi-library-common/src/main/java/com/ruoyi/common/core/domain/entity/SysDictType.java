package com.ruoyi.common.core.domain.entity;

import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.ruoyi.common.annotation.ExcelDictFormat;
import com.ruoyi.common.annotation.ExcelEnumFormat;
import com.ruoyi.common.convert.ExcelDictConvert;
import com.ruoyi.common.convert.ExcelEnumConvert;
import com.ruoyi.common.core.domain.BaseEntity;
import com.ruoyi.common.enums.CommonNormalDisableEnum;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

/**
 * 字典类型表 sys_dict_type
 *
 * @author ruoyi
 * @author Lion Li
 */

@Data
@EqualsAndHashCode(callSuper = true)
@TableName("sys_dict_type")
@ExcelIgnoreUnannotated
@Schema(description = "字典类型业务对象")
public class SysDictType extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 字典主键
     */
    @Schema(description = "字典主键")
    @ExcelProperty(value = "字典主键")
    @TableId(value = "dict_id")
    private Long dictId;

    /**
     * 字典名称
     */
    @Schema(description = "字典名称", required = true)
    @ExcelProperty(value = "字典名称")
    @NotBlank(message = "字典名称不能为空")
    @Size(min = 0, max = 100, message = "字典类型名称长度不能超过{max}个字符")
    private String dictName;

    /**
     * 字典类型
     */
    @Schema(description = "字典类型", required = true)
    @ExcelProperty(value = "字典类型")
    @NotBlank(message = "字典类型不能为空")
    @Size(min = 0, max = 100, message = "字典类型类型长度不能超过{max}个字符")
    @Pattern(regexp = "^[a-z][a-z0-9_]*$", message = "字典类型必须以字母开头，且只能为（小写字母，数字，下滑线）")
    private String dictType;

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

}
