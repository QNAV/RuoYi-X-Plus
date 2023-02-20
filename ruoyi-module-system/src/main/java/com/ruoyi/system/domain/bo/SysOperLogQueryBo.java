package com.ruoyi.system.domain.bo;

import com.ruoyi.common.enums.BusinessTypeEnum;
import com.ruoyi.common.enums.OperationStatusEnum;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 操作日志记录查询对象
 * @author weibocy
 */
@Schema(description = "操作日志记录查询对象")
@Data
public class SysOperLogQueryBo implements Serializable {

    private static final long serialVersionUID=1L;

    /**
     * 操作模块
     */
    @Schema(description = "操作模块")
    private String title;

    /**
     * 业务类型（ADD=新增 MODIFY=修改 DELETE=删除 GRANT=授权 EXPORT=导出 IMPORT=导入 FORCED=强退 GENCODE=生成代码 CLEAR=清空数据 OTHER=其他）
     */
    @Schema(description = "业务类型（ADD=新增 MODIFY=修改 DELETE=删除 GRANT=授权 EXPORT=导出 IMPORT=导入 FORCED=强退 GENCODE=生成代码 CLEAR=清空数据 OTHER=其他）")
    private BusinessTypeEnum businessType;

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
     * 操作状态（NORMAL=正常 EXCEPTION=异常）
     */
    @Schema(description = "操作状态（NORMAL=正常 EXCEPTION=异常）")
    private OperationStatusEnum status;


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
