package com.ruoyi.common.core.domain.bo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 用户名账户注册对象
 *
 * @author weibocy
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "UserNameRegisterBo", description = "用户名账户注册对象")
public class UserNameRegisterBo extends UserNameLoginBo {



    /**
     * 用户类型
     */
    @ApiModelProperty(value = "用户类型")
    private String userType;

}
