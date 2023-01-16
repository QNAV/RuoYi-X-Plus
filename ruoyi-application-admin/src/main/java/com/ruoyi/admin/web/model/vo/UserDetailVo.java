package com.ruoyi.admin.web.model.vo;

import com.ruoyi.system.domain.vo.SysPostVo;
import com.ruoyi.common.core.domain.vo.SysRoleVo;
import com.ruoyi.common.core.domain.vo.SysUserVo;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 用户详情返回对象
 * @author weibocy
 */
@Schema(description = "用户详情返回对象")
@Data
public class UserDetailVo implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 角色列表
     */
    @Schema(description = "角色列表", required = true)
    private List<SysRoleVo> roles;

    /**
     * 岗位列表
     */
    @Schema(description = "岗位列表", required = true)
    private List<SysPostVo> posts;

    /**
     * 用户信息业务对象
     */
    @Schema(description = "用户信息业务对象")
    private SysUserVo user;

    /**
     * 选中岗位ID列表
     */
    @Schema(description = "岗位ID列表")
    private List<Long> postIds;

    /**
     * 角色ID列表
     */
    @Schema(description = "角色ID列表")
    private List<Long> roleIds;
}
