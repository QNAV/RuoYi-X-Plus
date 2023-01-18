package com.ruoyi.system.domain.bo;

import com.ruoyi.common.core.domain.BaseEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * OSS对象存储分页查询对象 sys_oss
 *
 * @author Lion Li
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Schema(description = "OSS对象存储分页查询对象")
public class SysOssBo extends BaseEntity {

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

}
