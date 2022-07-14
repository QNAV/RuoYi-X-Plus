package com.ruoyi.system.domain.bo;

import com.ruoyi.common.core.domain.bo.PageQuery;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * OSS对象存储分页查询对象
 * @author weibocy
 */
@ApiModel(value = "SysOssPageQueryBo", description = "OSS对象存储分页查询对象")
@Data
public class SysOssPageQueryBo extends PageQuery {

    /**
     * 文件名
     */
    @ApiModelProperty("文件名")
    private String fileName;

    /**
     * 原名
     */
    @ApiModelProperty("原名")
    private String originalName;

    /**
     * 文件后缀名
     */
    @ApiModelProperty("文件后缀名")
    private String fileSuffix;

    /**
     * URL地址
     */
    @ApiModelProperty("URL地址")
    private String url;

    /**
     * 服务商
     */
    @ApiModelProperty("服务商")
    private String service;


    /**
     * 开始创建时间
     */
    @ApiModelProperty(value = "开始创建时间")
    private Date beginCreateTime;

    /**
     * 结束创建时间
     */
    @ApiModelProperty(value = "结束创建时间")
    private Date endCreateTime;

    /**
     * 创建者
     */
    @ApiModelProperty(value = "创建者")
    private String createBy;
}
