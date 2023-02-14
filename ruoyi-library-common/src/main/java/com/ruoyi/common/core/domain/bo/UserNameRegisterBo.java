package com.ruoyi.common.core.domain.bo;

import com.ruoyi.common.enums.UserType;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 用户名账户注册对象
 *
 * @author weibocy
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Schema(description = "用户名账户注册对象")
public class UserNameRegisterBo extends UserNameLoginBo {

    private static final long serialVersionUID = 1L;

    /**
     * 用户类型
     */
    @Schema(description = "用户类型")
    private UserType userType;

}
