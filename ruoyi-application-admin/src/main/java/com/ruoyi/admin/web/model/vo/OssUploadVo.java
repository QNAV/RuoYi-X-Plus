package com.ruoyi.admin.web.model.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;

/**
 * OSS上传结果返回对象
 * @author weibocy
 */
@Data
@Schema(description = "OSS上传结果返回对象")
public class OssUploadVo implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * URL地址
     */
    @Schema(description = "URL地址")
    private String url;

    /**
     * 原名
     */
    @Schema(description = "原名")
    private String fileName;

    /**
     * 对象存储主键
     */
    @Schema(description = "对象存储主键")
    private String ossId;
}
