package com.ruoyi.system.domain.bo;

import com.ruoyi.common.enums.CommonNormalDisableEnum;
import com.ruoyi.common.enums.CommonYesOrNoEnum;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.Serializable;

/**
 * 字典数据新增业务对象
 *
 * @author weibocy
 */

@Data
@Schema(description = "字典数据新增业务对象")
public class SysDictDataAddBo implements Serializable {

    private static final long serialVersionUID=1L;

    /**
     * 字典排序
     */
    @Schema(description = "字典排序")
    private Integer dictSort;

    /**
     * 字典标签
     */
    @Schema(description = "字典标签", required = true)
    @NotBlank(message = "字典标签不能为空")
    @Size(min = 0, max = 100, message = "字典标签长度不能超过{max}个字符")
    private String dictLabel;

    /**
     * 字典键值
     */
    @Schema(description = "字典键值", required = true)
    @NotBlank(message = "字典键值不能为空")
    @Size(min = 0, max = 100, message = "字典键值长度不能超过{max}个字符")
    private String dictValue;

    /**
     * 字典类型
     */
    @Schema(description = "字典类型", required = true)
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
    private CommonYesOrNoEnum isDefault;

    /**
     * 状态（NORMAL=正常 DISABLE=停用）
     */
    @Schema(description = "状态（NORMAL=正常 DISABLE=停用）")
    private CommonNormalDisableEnum status;

    /**
     * 备注
     */
    @Schema(description = "备注")
    private String remark;

}
