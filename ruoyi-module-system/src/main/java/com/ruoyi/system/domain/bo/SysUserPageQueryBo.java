package com.ruoyi.system.domain.bo;

import com.ruoyi.common.core.domain.bo.PageQuery;
import com.ruoyi.common.enums.CommonNormalDisableEnum;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.Date;

/**
 * 用户信息分页查询对象
 * @author weibocy
 */
@Data
@Schema(description = "用户信息分页查询对象")
public class SysUserPageQueryBo extends PageQuery {

    private static final long serialVersionUID=1L;

    /**
     * 部门ID
     */
    @Schema(description = "部门ID")
    private Long deptId;

    /**
     * 数据权限 当前角色ID
     */
    @Schema(description = "角色ID")
    private Long roleId;


    /**
     * 用户ID
     */
    @Schema(description = "用户ID")
    private Long userId;

    /**
     * 用户账号
     */
    @Schema(description = "用户账号")
    private String userName;

    /**
     * 手机号码
     */
    @Schema(description = "手机号码")
    private String phoneNumber;

    /**
     * 帐号状态（NORMAL=正常 DISABLE=停用）
     */
    @Schema(description = "帐号状态（NORMAL=正常 DISABLE=停用）")
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
