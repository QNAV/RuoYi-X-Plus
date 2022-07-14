package com.ruoyi.web.model.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 用户登录返回对象
 * @author weibocy
 */
@Data
@ApiModel(value = "LoginVo", description = "用户登录返回对象")
public class LoginVo {

    @ApiModelProperty("身份令牌")
    private String token;
}
