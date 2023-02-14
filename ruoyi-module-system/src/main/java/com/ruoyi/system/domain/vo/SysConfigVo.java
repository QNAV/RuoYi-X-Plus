package com.ruoyi.system.domain.vo;

import com.ruoyi.common.core.domain.BaseVo;
import com.ruoyi.common.enums.CommonYesOrNo;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 参数配置视图对象
 *
 * @author weibocy
 */

@Data
@EqualsAndHashCode(callSuper = true)
@Schema(description = "参数配置视图对象")
public class SysConfigVo extends BaseVo {

    private static final long serialVersionUID=1L;

    /**
     * 参数主键
     */
    @Schema(description = "参数主键", required = true)
    private Long configId;

    /**
     * 参数名称
     */
    @Schema(description = "参数名称", required = true)
    private String configName;

    /**
     * 参数键名
     */
    @Schema(description = "参数键名", required = true)
    private String configKey;

    /**
     * 参数键值
     */
    @Schema(description = "参数键值", required = true)
    private String configValue;

    /**
     * 系统内置（YES=是 NO=否）
     */
    @Schema(description = "系统内置（YES=是 NO=否）", required = true)
    private CommonYesOrNo configType;

    /**
     * 备注
     */
    @Schema(description = "备注")
    private String remark;

}
