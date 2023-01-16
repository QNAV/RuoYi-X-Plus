package com.ruoyi.admin.web.model.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;

/**
 * 用户头像上传返回对象
 * @author weibocy
 */
@Data
@Schema(description = "用户头像上传返回对象")
public class AvatarUploadVo implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 头像地址
     */
    @Schema(description = "头像地址")
    private String imgUrl;
}
