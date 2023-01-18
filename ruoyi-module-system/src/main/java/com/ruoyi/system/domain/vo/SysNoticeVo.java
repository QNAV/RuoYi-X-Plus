package com.ruoyi.system.domain.vo;

import com.ruoyi.common.core.domain.BaseVo;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;


/**
 * 通知公告视图对象
 *
 * @author weibocy
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Schema(description = "通知公告视图对象")
public class SysNoticeVo extends BaseVo {

    private static final long serialVersionUID=1L;

    /**
     * 公告ID
     */
    @Schema(description = "公告ID", required = true)
    private Long noticeId;

    /**
     * 公告标题
     */
    @Schema(description = "公告标题", required = true)
    private String noticeTitle;

    /**
     * 公告类型（1通知 2公告）
     */
    @Schema(description = "公告类型（1通知 2公告）", required = true)
    private String noticeType;

    /**
     * 公告内容
     */
    @Schema(description = "公告内容", required = true)
    private String noticeContent;

    /**
     * 公告状态（0正常 1关闭）
     */
    @Schema(description = "公告状态（0正常 1关闭）", required = true)
    private String status;

    /**
     * 备注
     */
    @Schema(description = "备注")
    private String remark;

}
