package com.ruoyi.system.domain.bo;

import com.ruoyi.common.enums.CommonYesOrNoEnum;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 参数配置查询对象
 * @author weibocy
 */
@Schema(description = "参数配置查询对象")
@Data
public class SysConfigQueryBo implements Serializable {

    private static final long serialVersionUID=1L;

    /**
     * 参数名称
     */
    @Schema(description = "参数名称")
    private String configName;

    /**
     * 参数键名
     */
    @Schema(description = "参数键名")
    private String configKey;

    /**
     * 系统内置（YES=是 NO=否）
     */
    @Schema(description = "系统内置（YES=是 NO=否）")
    private CommonYesOrNoEnum configType;

    /**
     * 开始时间
     */
    @Schema(description = "开始时间")
    private Date beginTime;

    /**
     * 结束时间
     */
    @Schema(description = "结束时间")
    private Date endTime;


}
