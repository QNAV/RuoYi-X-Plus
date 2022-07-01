package com.ruoyi.common.core.domain.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 用户注册对象
 *
 * @author weibocy
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "RegisterBodyUserName", description = "用户注册对象")
public class RegisterBodyUserName extends UserNameLoginBody {

    /**
     * 用户类型
     */
    @ApiModelProperty(value = "用户类型")
    private String userType;

}
