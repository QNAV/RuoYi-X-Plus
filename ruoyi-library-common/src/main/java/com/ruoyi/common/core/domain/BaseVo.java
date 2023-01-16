package com.ruoyi.common.core.domain;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * Vo基类
 *
 * @author weibocy
 */

@Data
@Schema(description = "Vo基类")
public class BaseVo implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 创建者
     */
    @Schema(description = "创建者", required = true)
    private String createBy;

    /**
     * 创建时间
     */
    @Schema(description = "创建时间", required = true)
    private Date createTime;

    /**
     * 更新者
     */
    @Schema(description = "更新者")
    private String updateBy;

    /**
     * 更新时间
     */
    @Schema(description = "更新时间")
    private Date updateTime;
}
