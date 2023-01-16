package com.ruoyi.admin.web.model.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;

/**
 * 图片验证码返回对象
 * @author weibocy
 */
@Schema(description = "图片验证码返回对象")
@Data
public class CaptchaImageVo implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 验证码开关
     */
    @Schema(description = "验证码开关", required = true)
    private boolean captchaOnOff;

    /**
     * 验证码唯一标识符
     */
    @Schema(description = "验证码唯一标识符", required = true)
    private String uuid;

    /**
     * 图片base64
     */
    @Schema(description = "图片base64", required = true)
    private String img;

}
