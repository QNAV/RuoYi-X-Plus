package com.ruoyi.admin.web.model.vo;

import com.ruoyi.common.core.domain.vo.SysUserVo;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;

/**
 * 个人信息视图对象
 * @author weibocy
 */
@Data
@Schema(description = "个人信息视图对象")
public class ProfileVo implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 用户对象信息
     */
    @Schema(description = "用户对象信息", required = true)
    private SysUserVo user;

    /**
     * 用户所属角色组
     */
    @Schema(description = "用户所属角色组", required = true)
    private String roleGroup;

    /**
     * 用户所属岗位组
     */
    @Schema(description = "用户所属岗位组", required = true)
    private String postGroup;
}
