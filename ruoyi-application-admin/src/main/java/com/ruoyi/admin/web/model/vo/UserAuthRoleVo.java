package com.ruoyi.admin.web.model.vo;

import com.ruoyi.common.core.domain.entity.SysRole;
import com.ruoyi.common.core.domain.entity.SysUser;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 用户授权角色返回对象
 * @author weibocy
 */
@Data
@Schema(description = "用户授权角色返回对象")
public class UserAuthRoleVo implements Serializable {

    private static final long serialVersionUID = 1L;

    @Schema(description = "用户信息业务对象")
    private SysUser user;

    @Schema(description = "授权角色列表")
    private List<SysRole> roles;
}
