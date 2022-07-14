package com.ruoyi.system.domain.vo;

import com.ruoyi.common.core.domain.BaseVo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 参数配置视图对象
 *
 * @author weibocy
 */

@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "SysConfigVo", description = "参数配置视图对象")
public class SysConfigVo extends BaseVo {

    /**
     * 参数主键
     */
    @ApiModelProperty(value = "参数主键", required = true)
    private Long configId;

    /**
     * 参数名称
     */
    @ApiModelProperty(value = "参数名称", required = true)
    private String configName;

    /**
     * 参数键名
     */
    @ApiModelProperty(value = "参数键名", required = true)
    private String configKey;

    /**
     * 参数键值
     */
    @ApiModelProperty(value = "参数键值", required = true)
    private String configValue;

    /**
     * 系统内置（Y是 N否）
     */
    @ApiModelProperty(value = "系统内置（Y是 N否）", required = true)
    private String configType;

    /**
     * 备注
     */
    @ApiModelProperty(value = "备注")
    private String remark;

}
