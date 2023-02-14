package com.ruoyi.common.core.domain.bo;

import com.ruoyi.common.enums.DataScopeType;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 角色
 *
 * @author weibocy
 */

@Data
@NoArgsConstructor
public class RoleBo implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 角色ID
     */
    private Long roleId;

    /**
     * 角色名称
     */
    private String roleName;

    /**
     * 角色权限
     */
    private String roleKey;

    /**
     * 数据范围（ALL=全部数据权限 CUSTOM=自定数据权限 DEPT=本部门数据权限 DEPT_CHILD=本部门及以下数据权限 SELF=仅本人数据权限）
     */
    private DataScopeType dataScope;

}
