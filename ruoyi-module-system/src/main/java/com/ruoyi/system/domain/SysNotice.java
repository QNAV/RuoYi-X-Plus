package com.ruoyi.system.domain;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.ruoyi.common.core.domain.BaseEntity;
import com.ruoyi.common.xss.Xss;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;


/**
 * 通知公告表 sys_notice
 *
 * @author weibocy
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("sys_notice")
@ApiModel(value = "SysNotice", description = "通知公告实体对象", parent = BaseEntity.class)
public class SysNotice extends BaseEntity {

    /**
     * 公告ID
     */
    @ApiModelProperty(value = "公告ID")
    @TableId(value = "notice_id")
    private Long noticeId;

    /**
     * 公告标题
     */
    @Xss(message = "公告标题不能包含脚本字符")
    @ApiModelProperty(value = "公告标题", required = true)
    @NotBlank(message = "公告标题不能为空")
    @Size(min = 0, max = 50, message = "公告标题不能超过50个字符")
    private String noticeTitle;

    /**
     * 公告类型（1通知 2公告）
     */
    @ApiModelProperty(value = "公告类型（1通知 2公告）", required = true)
    @NotBlank(message = "公告类型不能为空")
    private String noticeType;

    /**
     * 公告内容
     */
    @ApiModelProperty(value = "公告内容", required = true)
    @NotBlank(message = "公告内容不能为空")
    private String noticeContent;

    /**
     * 公告状态（0正常 1关闭）
     */
    @ApiModelProperty(value = "公告状态（0正常 1关闭）", required = true)
    @NotBlank(message = "公告状态不能为空")
    private String status;

    /**
     * 备注
     */
    @ApiModelProperty(value = "备注")
    private String remark;

}
