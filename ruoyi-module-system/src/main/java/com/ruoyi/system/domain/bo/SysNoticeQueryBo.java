package com.ruoyi.system.domain.bo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;


/**
 * 通知公告查询对象
 * @author weibocy
 */
@Data
@Schema(description = "通知公告查询对象")
public class SysNoticeQueryBo implements Serializable {

    private static final long serialVersionUID=1L;

    /**
     * 公告标题
     */
    @Schema(description = "公告标题")
    private String noticeTitle;

    /**
     * 公告类型（1通知 2公告）
     */
    @Schema(description = "公告类型（1通知 2公告）")
    private String noticeType;

    /**
     * 创建者
     */
    @Schema(description = "创建者")
    private String createBy;

}
