package com.ruoyi.system.domain.bo;

import com.ruoyi.common.enums.CommonNormalDisableEnum;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;


/**
 * 字典类型查询对象
 * @author weibocy
 */
@Schema(description = "字典类型查询对象")
@Data
public class SysDictTypeQueryBo implements Serializable {

    private static final long serialVersionUID=1L;

    /**
     * 字典名称
     */
    @Schema(description = "字典名称")
    private String dictName;

    /**
     * 字典类型
     */
    @Schema(description = "字典类型")
    private String dictType;

    /**
     * 状态（NORMAL=正常 DISABLE=停用）
     */
    @Schema(description = "状态（NORMAL=正常 DISABLE=停用）")
    private CommonNormalDisableEnum status;


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
