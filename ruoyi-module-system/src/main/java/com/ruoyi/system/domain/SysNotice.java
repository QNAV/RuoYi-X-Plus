package com.ruoyi.system.domain;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.ruoyi.common.core.domain.BaseEntity;
import com.ruoyi.common.enums.CommonNormalDisableEnum;
import com.ruoyi.common.xss.Xss;
import com.ruoyi.system.enums.NoticeTypeEnum;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;


/**
 * 通知公告表 sys_notice
 *
 * @author ruoyi
 * @author Lion Li
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("sys_notice")
@Schema(description = "通知公告实体对象")
public class SysNotice extends BaseEntity {

    /**
     * 公告ID
     */
    @Schema(description = "公告ID")
    @TableId(value = "notice_id")
    private Long noticeId;

    /**
     * 公告标题
     */
    @Xss(message = "公告标题不能包含脚本字符")
    @Schema(description = "公告标题", required = true)
    @NotBlank(message = "公告标题不能为空")
    @Size(min = 0, max = 50, message = "公告标题不能超过{max}个字符")
    private String noticeTitle;

    /**
     * 公告类型（NOTICE=通知 BULLETIN=公告）
     */
    @Schema(description = "公告类型（NOTICE=通知 BULLETIN=公告）", required = true)
    @NotBlank(message = "公告类型不能为空")
    private NoticeTypeEnum noticeType;

    /**
     * 公告内容
     */
    @Schema(description = "公告内容", required = true)
    @NotBlank(message = "公告内容不能为空")
    private String noticeContent;

    /**
     * 公告状态（NORMAL=正常 DISABLE=关闭）
     */
    @Schema(description = "公告状态（NORMAL=正常 DISABLE=关闭）", required = true)
    @NotBlank(message = "公告状态不能为空")
    private CommonNormalDisableEnum status;

    /**
     * 备注
     */
    @Schema(description = "备注")
    private String remark;

}
