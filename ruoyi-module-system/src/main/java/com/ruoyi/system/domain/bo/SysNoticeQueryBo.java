package com.ruoyi.system.domain.bo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;


/**
 * 通知公告查询对象
 * @author weibocy
 */
@Data
@ApiModel(value = "SysNoticeQueryBo", description = "通知公告查询对象")
public class SysNoticeQueryBo implements Serializable {

    private static final long serialVersionUID=1L;

    /**
     * 公告标题
     */
    @ApiModelProperty(value = "公告标题")
    private String noticeTitle;

    /**
     * 公告类型（1通知 2公告）
     */
    @ApiModelProperty(value = "公告类型（1通知 2公告）")
    private String noticeType;

    /**
     * 创建者
     */
    @ApiModelProperty(value = "创建者")
    private String createBy;

}
