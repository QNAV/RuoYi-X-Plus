package com.ruoyi.system.domain.bo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

/**
 * 参数配置修改业务对象
 *
 * @author weibocy
 */

@Data
@Schema(description = "参数配置修改业务对象")
public class SysConfigEditBo implements Serializable {

    private static final long serialVersionUID=1L;

    /**
     * 参数主键
     */
    @Schema(description = "参数主键", required = true)
    @NotNull(message = "参数主键不能为空")
    private Long configId;

    /**
     * 参数名称
     */
    @Schema(description = "参数名称")
    @Size(min = 1, max = 100, message = "参数名称不能超过100个字符")
    private String configName;

    /**
     * 参数键名
     */
    @Schema(description = "参数键名")
    @Size(min = 1, max = 100, message = "参数键名长度不能超过100个字符")
    private String configKey;

    /**
     * 参数键值
     */
    @Schema(description = "参数键值")
    @Size(min = 1, max = 500, message = "参数键值长度不能超过500个字符")
    private String configValue;

    /**
     * 系统内置（Y是 N否）
     */
    @Schema(description = "系统内置（Y是 N否）")
    private String configType;

    /**
     * 备注
     */
    @Schema(description = "备注")
    private String remark;

}
