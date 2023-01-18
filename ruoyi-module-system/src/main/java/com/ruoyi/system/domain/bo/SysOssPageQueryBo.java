package com.ruoyi.system.domain.bo;

import com.ruoyi.common.core.domain.bo.PageQuery;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.Date;

/**
 * OSS对象存储分页查询对象
 * @author weibocy
 */
@Schema(description = "OSS对象存储分页查询对象")
@Data
public class SysOssPageQueryBo extends PageQuery {

    private static final long serialVersionUID=1L;

    /**
     * 文件名
     */
    @Schema(description = "文件名")
    private String fileName;

    /**
     * 原名
     */
    @Schema(description = "原名")
    private String originalName;

    /**
     * 文件后缀名
     */
    @Schema(description = "文件后缀名")
    private String fileSuffix;

    /**
     * URL地址
     */
    @Schema(description = "URL地址")
    private String url;

    /**
     * 服务商
     */
    @Schema(description = "服务商")
    private String service;


    /**
     * 开始创建时间
     */
    @Schema(description = "开始创建时间")
    private Date beginCreateTime;

    /**
     * 结束创建时间
     */
    @Schema(description = "结束创建时间")
    private Date endCreateTime;

    /**
     * 创建者
     */
    @Schema(description = "创建者")
    private String createBy;
}
