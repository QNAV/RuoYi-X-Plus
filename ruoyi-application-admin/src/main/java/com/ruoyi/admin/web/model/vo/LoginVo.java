package com.ruoyi.admin.web.model.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;

/**
 * 用户登录返回对象
 * @author weibocy
 */
@Data
@Schema(description = "用户登录返回对象")
public class LoginVo implements Serializable {

    private static final long serialVersionUID = 1L;

    @Schema(description = "身份令牌", required = true)
    private String token;
}
