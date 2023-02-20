package com.ruoyi.admin.service;

import cn.dev33.satoken.stp.StpInterface;
import com.ruoyi.admin.domain.model.AdminLoginUser;
import com.ruoyi.admin.helper.AdminLoginHelper;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * sa-token 权限管理实现类
 *
 * @author weibocy
 */
@Component
@ConditionalOnProperty(prefix = "ruoyi", name = "appType", havingValue = "admin")
public class AdminSaPermissionImpl implements StpInterface {

    /**
     * 获取菜单权限列表
     */
    @Override
    public List<String> getPermissionList(Object loginId, String loginType) {
        AdminLoginUser loginUser = AdminLoginHelper.getLoginUser();
        return new ArrayList<>(loginUser.getMenuPermission());

    }

    /**
     * 获取角色权限列表
     */
    @Override
    public List<String> getRoleList(Object loginId, String loginType) {
        AdminLoginUser loginUser = AdminLoginHelper.getLoginUser();
        return new ArrayList<>(loginUser.getRolePermission());
    }
}
