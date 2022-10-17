package com.ruoyi.system.domain.bo;

import com.ruoyi.common.core.domain.bo.PageQuery;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;


/**
 * 通知公告分页查询对象
 * @author weibocy
 */
@Data
@ApiModel(value = "SysNoticePageQueryBo", description = "通知公告分页查询对象", parent = PageQuery.class)
public class SysNoticePageQueryBo extends PageQuery {

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
