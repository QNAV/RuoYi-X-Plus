package com.ruoyi.system.domain.vo;

import com.ruoyi.common.core.domain.BaseVo;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.Date;

/**
 * OSS对象存储视图对象
 *
 * @author weibocy
 */
@Data
@Schema(description = "OSS对象存储视图对象")
public class SysOssVo extends BaseVo {

    private static final long serialVersionUID = 1L;

    /**
     * 对象存储主键
     */
    @Schema(description = "对象存储主键", required = true)
    private Long ossId;

    /**
     * 文件名
     */
    @Schema(description = "文件名", required = true)
    private String fileName;

    /**
     * 原名
     */
    @Schema(description = "原名", required = true)
    private String originalName;

    /**
     * 文件后缀名
     */
    @Schema(description = "文件后缀名", required = true)
    private String fileSuffix;

    /**
     * URL地址
     */
    @Schema(description = "URL地址", required = true)
    private String url;

    /**
     * 创建时间
     */
    @Schema(description = "创建时间", required = true)
    private Date createTime;

    /**
     * 上传人
     */
    @Schema(description = "上传人")
    private String createBy;

    /**
     * 服务商
     */
    @Schema(description = "服务商", required = true)
    private String service;


}
