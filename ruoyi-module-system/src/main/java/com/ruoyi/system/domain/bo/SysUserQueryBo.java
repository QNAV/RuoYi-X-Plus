package com.ruoyi.system.domain.bo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 用户信息查询对象
 * @author weibocy
 */
@Data
@Schema(description = "用户信息查询对象")
public class SysUserQueryBo implements Serializable {

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
     * 帐号状态（0正常 1停用）
     */
    @Schema(description = "帐号状态（0正常 1停用）")
    private String status;

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
