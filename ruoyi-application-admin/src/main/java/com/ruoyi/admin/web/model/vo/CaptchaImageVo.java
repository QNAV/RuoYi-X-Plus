package com.ruoyi.admin.web.model.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * 图片验证码返回对象
 * @author weibocy
 */
@ApiModel(value = "CaptchaImageVo", description = "图片验证码返回对象")
@Data
public class CaptchaImageVo implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 验证码开关
     */
    @ApiModelProperty(value = "验证码开关", required = true)
    private boolean captchaOnOff;

    /**
     * 验证码唯一标识符
     */
    @ApiModelProperty(value = "验证码唯一标识符", required = true)
    private String uuid;

    /**
     * 图片base64
     */
    @ApiModelProperty(value = "图片base64", required = true)
    private String img;

}
