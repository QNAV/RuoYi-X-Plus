package com.ruoyi.system.domain;

import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.ruoyi.common.annotation.ExcelEnumFormat;
import com.ruoyi.common.convert.ExcelEnumConvert;
import com.ruoyi.common.core.domain.BaseEntity;
import com.ruoyi.common.enums.CommonYesOrNoEnum;
import com.ruoyi.system.enums.ConfigValueTypeEnum;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

/**
 * 参数配置表 sys_config
 *
 * @author ruoyi
 * @author Lion Li
 */

@Data
@EqualsAndHashCode(callSuper = true)
@TableName("sys_config")
@ExcelIgnoreUnannotated
@Schema(description = "参数配置业务对象")
public class SysConfig extends BaseEntity {

    /**
     * 参数主键
     */
    @Schema(description = "参数主键")
    @ExcelProperty(value = "参数主键")
    @TableId(value = "config_id")
    private Long configId;

    /**
     * 参数名称
     */
    @Schema(description = "参数名称", required = true)
    @ExcelProperty(value = "参数名称")
    @NotBlank(message = "参数名称不能为空")
    @Size(min = 0, max = 100, message = "参数名称不能超过{max}个字符")
    private String configName;

    /**
     * 参数键名
     */
    @Schema(description = "参数键名", required = true)
    @ExcelProperty(value = "参数键名")
    @NotBlank(message = "参数键名长度不能为空")
    @Size(min = 0, max = 100, message = "参数键名长度不能超过{max}个字符")
    private String configKey;


    /**
     * 参数值类型
     */
    @Schema(description = "参数值类型", required = true)
    @NotBlank(message = "参数值类型不能为空")
    @ExcelProperty(value = "参数值类型", converter = ExcelEnumConvert.class)
    @ExcelEnumFormat(enumClass = ConfigValueTypeEnum.class)
    private ConfigValueTypeEnum valueType;

    /**
     * 参数键值
     */
    @Schema(description = "参数键值", required = true)
    @ExcelProperty(value = "参数键值")
    @NotBlank(message = "参数键值不能为空")
    @Size(min = 0, max = 500, message = "参数键值长度不能超过{max}个字符")
    private String configValue;

    /**
     * 系统内置（YES=是 NO=否）
     */
    @Schema(description = "系统内置（YES=是 NO=否）")
    @ExcelProperty(value = "系统内置", converter = ExcelEnumConvert.class)
    @ExcelEnumFormat(enumClass = CommonYesOrNoEnum.class)
    private CommonYesOrNoEnum configType;

    /**
     * 备注
     */
    @Schema(description = "备注")
    private String remark;

}
