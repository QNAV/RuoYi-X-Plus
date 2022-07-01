package com.ruoyi.web.model.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 图片验证码返回对象
 * @author weibocy
 */
@ApiModel(value = "CaptchaImageDto", description = "图片验证码返回对象")
@Data
public class CaptchaImageDto {

    /**
     * 验证码开关
     */
    @ApiModelProperty("验证码开关")
    private boolean captchaOnOff;

    /**
     * 验证码唯一标识符
     */
    @ApiModelProperty("验证码唯一标识符")
    private String uuid;

    /**
     * 图片base64
     */
    @ApiModelProperty("图片base64")
    private String img;

}
