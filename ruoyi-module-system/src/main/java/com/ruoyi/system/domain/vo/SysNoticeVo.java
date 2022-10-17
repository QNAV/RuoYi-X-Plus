package com.ruoyi.system.domain.vo;

import com.ruoyi.common.core.domain.BaseVo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;


/**
 * 通知公告视图对象
 *
 * @author weibocy
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "SysNoticeVo", description = "通知公告视图对象", parent = BaseVo.class)
public class SysNoticeVo extends BaseVo {

    /**
     * 公告ID
     */
    @ApiModelProperty(value = "公告ID", required = true)
    private Long noticeId;

    /**
     * 公告标题
     */
    @ApiModelProperty(value = "公告标题", required = true)
    private String noticeTitle;

    /**
     * 公告类型（1通知 2公告）
     */
    @ApiModelProperty(value = "公告类型（1通知 2公告）", required = true)
    private String noticeType;

    /**
     * 公告内容
     */
    @ApiModelProperty(value = "公告内容", required = true)
    private String noticeContent;

    /**
     * 公告状态（0正常 1关闭）
     */
    @ApiModelProperty(value = "公告状态（0正常 1关闭）", required = true)
    private String status;

    /**
     * 备注
     */
    @ApiModelProperty(value = "备注")
    private String remark;

}
