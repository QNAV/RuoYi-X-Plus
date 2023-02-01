package com.ruoyi.system.domain.bo;

import com.ruoyi.common.xss.Xss;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.Serializable;


/**
 * 通知公告新增业务对象
 *
 * @author weibocy
 */
@Data
@Schema(description = "通知公告新增业务对象")
public class SysNoticeAddBo implements Serializable {

    private static final long serialVersionUID=1L;

    /**
     * 公告标题
     */
    @Xss(message = "公告标题不能包含脚本字符")
    @Schema(description = "公告标题", required = true)
    @NotBlank(message = "公告标题不能为空")
    @Size(min = 0, max = 50, message = "公告标题不能超过{max}个字符")
    private String noticeTitle;

    /**
     * 公告类型（1通知 2公告）
     */
    @Schema(description = "公告类型（1通知 2公告）", required = true)
    @NotBlank(message = "公告类型不能为空")
    private String noticeType;

    /**
     * 公告内容
     */
    @Schema(description = "公告内容", required = true)
    @NotBlank(message = "公告内容不能为空")
    private String noticeContent;

    /**
     * 公告状态（0正常 1关闭）
     */
    @Schema(description = "公告状态（0正常 1关闭）", required = true)
    @NotBlank(message = "公告状态不能为空")
    private String status;

    /**
     * 备注
     */
    @Schema(description = "备注")
    private String remark;

}
