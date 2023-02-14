package com.ruoyi.system.domain.bo;

import com.ruoyi.common.core.domain.bo.PageQuery;
import com.ruoyi.common.enums.CommonYesOrNo;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.Date;

/**
 * 参数配置分页查询对象
 * @author weibocy
 */
@Schema(description = "参数配置分页查询对象")
@Data
public class SysConfigPageQueryBo extends PageQuery {

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
    private CommonYesOrNo configType;

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
