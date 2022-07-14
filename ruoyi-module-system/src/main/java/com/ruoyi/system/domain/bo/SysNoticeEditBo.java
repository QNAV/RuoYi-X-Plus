package com.ruoyi.system.domain.bo;

import com.ruoyi.common.xss.Xss;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


/**
 * 通知公告编辑业务对象
 *
 * @author weibocy
 */
@Data
@ApiModel(value = "SysNoticeEditBo", description = "通知公告编辑业务对象")
public class SysNoticeEditBo {

    /**
     * 公告ID
     */
    @ApiModelProperty(value = "公告ID", required = true)
    @NotNull(message = "公告ID不能为空")
    private Long noticeId;

    /**
     * 公告标题
     */
    @Xss(message = "公告标题不能包含脚本字符")
    @ApiModelProperty(value = "公告标题")
    @Size(min = 0, max = 50, message = "公告标题不能超过50个字符")
    private String noticeTitle;

    /**
     * 公告类型（1通知 2公告）
     */
    @ApiModelProperty(value = "公告类型（1通知 2公告）")
    private String noticeType;

    /**
     * 公告内容
     */
    @ApiModelProperty(value = "公告内容")
    private String noticeContent;

    /**
     * 公告状态（0正常 1关闭）
     */
    @ApiModelProperty(value = "公告状态（0正常 1关闭）")
    private String status;

    /**
     * 备注
     */
    @ApiModelProperty(value = "备注")
    private String remark;

}
