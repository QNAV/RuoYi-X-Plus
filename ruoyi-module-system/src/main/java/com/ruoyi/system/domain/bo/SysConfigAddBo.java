package com.ruoyi.system.domain.bo;

import com.ruoyi.common.enums.CommonYesOrNo;
import com.ruoyi.system.enums.ConfigValueTypeEnum;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

/**
 * 参数配置新增业务对象
 *
 * @author weibocy
 */

@Data
@Schema(description = "参数配置新增业务对象")
public class SysConfigAddBo implements Serializable {

    private static final long serialVersionUID=1L;


    /**
     * 参数名称
     */
    @Schema(description = "参数名称", required = true)
    @NotBlank(message = "参数名称不能为空")
    @Size(min = 1, max = 100, message = "参数名称不能超过{max}个字符")
    private String configName;

    /**
     * 参数键名
     */
    @Schema(description = "参数键名", required = true)
    @NotBlank(message = "参数键名长度不能为空")
    @Size(min = 1, max = 100, message = "参数键名长度不能超过{max}个字符")
    private String configKey;

    /**
     * 参数值类型
     */
    @Schema(description = "参数值类型", required = true)
    @NotNull(message = "参数值类型不能为空")
    private ConfigValueTypeEnum valueType;

    /**
     * 参数键值
     */
    @Schema(description = "参数键值", required = true)
    @NotBlank(message = "参数键值不能为空")
    @Size(min = 1, max = 500, message = "参数键值长度不能超过{max}个字符")
    private String configValue;

    /**
     * 系统内置（YES=是 NO=否）
     */
    @Schema(description = "系统内置（YES=是 NO=否）")
    private CommonYesOrNo configType;

    /**
     * 备注
     */
    @Schema(description = "备注")
    private String remark;

}
