package com.ruoyi.web.model.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;

/**
 * 用户登录返回对象
 * @author weibocy
 */
@Data
@ApiModel(value = "LoginDTO", description = "用户登录返回对象")
public class LoginDTO {

    @ApiModelProperty("身份令牌")
    private String token;
}
