package com.ruoyi.admin.web.model.vo;

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

    @ApiModelProperty(value = "身份令牌", required = true)
    private String token;
}
