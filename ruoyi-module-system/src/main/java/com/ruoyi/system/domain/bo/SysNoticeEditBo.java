package com.ruoyi.system.domain.bo;

import com.ruoyi.common.xss.Xss;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;


/**
 * 通知公告编辑业务对象
 *
 * @author weibocy
 */
@Data
@Schema(description = "通知公告编辑业务对象")
public class SysNoticeEditBo implements Serializable {

    private static final long serialVersionUID=1L;

    /**
     * 公告ID
     */
    @Schema(description = "公告ID", required = true)
    @NotNull(message = "公告ID不能为空")
    private Long noticeId;

    /**
     * 公告标题
     */
    @Xss(message = "公告标题不能包含脚本字符")
    @Schema(description = "公告标题")
    @Size(min = 0, max = 50, message = "公告标题不能超过{max}个字符")
    private String noticeTitle;

    /**
     * 公告类型（1通知 2公告）
     */
    @Schema(description = "公告类型（1通知 2公告）")
    private String noticeType;

    /**
     * 公告内容
     */
    @Schema(description = "公告内容")
    private String noticeContent;

    /**
     * 公告状态（0正常 1关闭）
     */
    @Schema(description = "公告状态（0正常 1关闭）")
    private String status;

    /**
     * 备注
     */
    @Schema(description = "备注")
    private String remark;

}
