package com.ruoyi.system.domain.bo;

import com.ruoyi.common.core.domain.bo.PageQuery;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.Date;

/**
 * 操作日志记录分页查询对象
 * @author weibocy
 */
@Schema(description = "操作日志记录分页查询对象")
@Data
public class SysOperLogPageQueryBo extends PageQuery {

    private static final long serialVersionUID=1L;

    /**
     * 操作模块
     */
    @Schema(description = "操作模块")
    private String title;

    /**
     * 业务类型（0其它 1新增 2修改 3删除）
     */
    @Schema(description = "业务类型（0其它 1新增 2修改 3删除）")
    private Integer businessType;

    /**
     * 业务类型数组
     */
    @Schema(description = "业务类型数组")
    private Integer[] businessTypes;

    /**
     * 操作人员
     */
    @Schema(description = "操作人员")
    private String operName;

    /**
     * 操作状态（0正常 1异常）
     */
    @Schema(description = "操作状态（0正常 1异常）")
    private Integer status;


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
