package com.ruoyi.common.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.fasterxml.jackson.annotation.JsonValue;
import com.ruoyi.common.utils.StringUtils;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 数据权限类型
 * <p>
 * 语法支持 spel 模板表达式
 * <p>
 * 内置数据 user 当前用户 内容参考 AdminLoginUser
 * 如需扩展数据 可使用 {@link com.ruoyi.common.helper.DataPermissionHelper} 操作
 * 内置服务 sdss 系统数据权限服务 内容参考 SysDataScopeService
 * 如需扩展更多自定义服务 可以参考 sdss 自行编写
 *
 * @author Lion Li
 */
@Getter
@AllArgsConstructor
public enum DataScopeType {

    /**
     * 全部数据权限
     */
    ALL("ALL", "", ""),

    /**
     * 自定数据权限
     */
    CUSTOM("CUSTOM", " #{#deptName} IN ( #{@sdss.getRoleCustom( #user.roleId )} ) ", ""),

    /**
     * 部门数据权限
     */
    DEPT("DEPT", " #{#deptName} = #{#user.deptId} ", ""),

    /**
     * 部门及以下数据权限
     */
    DEPT_CHILD("DEPT_CHILD", " #{#deptName} IN ( #{@sdss.getDeptAndChild( #user.deptId )} )", ""),

    /**
     * 仅本人数据权限
     */
    SELF("SELF", " #{#userName} = #{#user.userId} ", " 1 = 0 ");

    // 标记响应mbp值
    @EnumValue
    // 标记响应json值
    @JsonValue
    private final String code;

    /**
     * 语法 采用 spel 模板表达式
     */
    private final String sqlTemplate;

    /**
     * 不满足 sqlTemplate 则填充
     */
    private final String elseSql;

    public static DataScopeType findCode(String code) {
        if (StringUtils.isBlank(code)) {
            return null;
        }
        for (DataScopeType type : values()) {
            if (type.getCode().equals(code)) {
                return type;
            }
        }
        return null;
    }
}
