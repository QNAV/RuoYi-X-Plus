package com.ruoyi.system.domain.vo;

import com.ruoyi.common.core.domain.BaseVo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * OSS对象存储视图对象
 *
 * @author weibocy
 */
@Data
@ApiModel(value = "SysOssVo", description = "OSS对象存储视图对象")
public class SysOssVo extends BaseVo {

    private static final long serialVersionUID = 1L;

    /**
     * 对象存储主键
     */
    @ApiModelProperty(value = "对象存储主键", required = true)
    private Long ossId;

    /**
     * 文件名
     */
    @ApiModelProperty(value = "文件名", required = true)
    private String fileName;

    /**
     * 原名
     */
    @ApiModelProperty(value = "原名", required = true)
    private String originalName;

    /**
     * 文件后缀名
     */
    @ApiModelProperty(value = "文件后缀名", required = true)
    private String fileSuffix;

    /**
     * URL地址
     */
    @ApiModelProperty(value = "URL地址", required = true)
    private String url;

    /**
     * 创建时间
     */
    @ApiModelProperty(value = "创建时间", required = true)
    private Date createTime;

    /**
     * 上传人
     */
    @ApiModelProperty("上传人")
    private String createBy;

    /**
     * 服务商
     */
    @ApiModelProperty(value = "服务商", required = true)
    private String service;


}
