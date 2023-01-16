package com.ruoyi.admin.web.model.vo;

import com.ruoyi.common.core.domain.vo.SysUserVo;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;
import java.util.Set;

/**
 * 用户信息返回对象
 * @author weibocy
 */
@Data
@Schema(description = "用户信息返回对象")
public class UserInfoVo implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 用户信息业务对象
     */
    @Schema(description = "用户信息业务对象", required = true)
    private SysUserVo user;

    /**
     * 角色集合
     */
    @Schema(description = "角色集合", required = true)
    private Set<String> roles;

    /**
     * 权限集合
     */
    @Schema(description = "权限集合", required = true)
    private Set<String> permissions;

}
