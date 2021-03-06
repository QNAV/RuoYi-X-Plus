package com.ruoyi.web.model.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * OSS上传结果返回对象
 * @author weibocy
 */
@Data
@ApiModel(value = "OssUploadVo", description = "OSS上传结果返回对象")
public class OssUploadVo {

    /**
     * URL地址
     */
    @ApiModelProperty("URL地址")
    private String url;

    /**
     * 原名
     */
    @ApiModelProperty("原名")
    private String fileName;

    /**
     * 对象存储主键
     */
    @ApiModelProperty("对象存储主键")
    private String ossId;
}
