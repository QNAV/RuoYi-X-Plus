package com.ruoyi.web.model.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 用户头像上传返回对象
 * @author weibocy
 */
@Data
@ApiModel(value = "AvatarUploadVo", description = "用户头像上传返回对象")
public class AvatarUploadVo {

    /**
     * 头像地址
     */
    @ApiModelProperty("头像地址")
    private String imgUrl;
}
