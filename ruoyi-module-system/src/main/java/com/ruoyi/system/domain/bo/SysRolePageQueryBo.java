package com.ruoyi.system.domain.bo;

import com.ruoyi.common.core.domain.bo.PageQuery;
import com.ruoyi.common.enums.CommonNormalDisableEnum;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.Date;

/**
 * 角色查询对象
 * @author weibocy
 */
@Schema(description = "角色查询对象")
@Data
public class SysRolePageQueryBo extends PageQuery {

    private static final long serialVersionUID=1L;

    /**
     * 角色ID
     */
    @Schema(description = "角色ID")
    private Long roleId;

    /**
     * 角色名称
     */
    @Schema(description = "角色名称")
    private String roleName;

    /**
     * 角色权限
     */
    @Schema(description = "角色权限")
    private String roleKey;

    /**
     * 角色状态（NORMAL=正常 DISABLE=停用）
     */
    @Schema(description = "角色状态（NORMAL=正常 DISABLE=停用）")
    private CommonNormalDisableEnum status;


    /**
     * 开始创建时间
     */
    @Schema(description = "开始创建时间")
    private Date beginCreateTime;

    /**
     * 结束创建时间
     */
    @Schema(description = "结束创建时间")
    private Date endCreateTime;

}
