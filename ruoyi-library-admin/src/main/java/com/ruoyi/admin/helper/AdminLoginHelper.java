package com.ruoyi.admin.helper;

import cn.dev33.satoken.context.SaHolder;
import cn.dev33.satoken.stp.SaLoginModel;
import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.core.util.ObjectUtil;
import com.ruoyi.admin.domain.model.AdminLoginUser;
import com.ruoyi.common.constant.UserConstants;
import com.ruoyi.common.enums.DeviceType;
import com.ruoyi.common.enums.UserTypeEnum;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.Set;

/**
 * 后台登录鉴权助手
 * <p>
 * user_type 为 用户类型 同一个用户表 可以有多种用户类型 例如 pc,app
 * deivce 为 设备类型 同一个用户类型 可以有 多种设备类型 例如 web,ios
 * 可以组成 用户类型与设备类型多对多的 权限灵活控制
 * <p>
 * 多用户体系 针对 多种用户类型 但权限控制不一致
 * 可以组成 多用户类型表与多设备类型 分别控制权限
 *
 * @author Lion Li
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class AdminLoginHelper {

    public static final String LOGIN_USER_KEY = "loginAdminUser";
    public static final String MENU_PERMISSION = "menuPermission";

    /**
     * 登录系统
     *
     * @param loginUser 登录用户信息
     */
    public static void login(AdminLoginUser loginUser) {
        loginByDevice(loginUser, null);
    }

    /**
     * 登录系统 基于 设备类型
     * 针对相同用户体系不同设备
     *
     * @param loginUser 登录用户信息
     */
    public static void loginByDevice(AdminLoginUser loginUser, DeviceType deviceType) {
        SaHolder.getStorage().set(LOGIN_USER_KEY, loginUser);
        Set<String> menuPermission = loginUser.getMenuPermission();
        loginUser.setMenuPermission(null);
        SaLoginModel model = new SaLoginModel();
        if (ObjectUtil.isNotNull(deviceType)) {
            model.setDevice(deviceType.getDevice());
        }
        StpUtil.login(loginUser.getLoginId(), model.setExtra(LOGIN_USER_KEY, loginUser));
        // 解决菜单权限过度 token 臃肿过长问题
        StpUtil.getTokenSession().set(MENU_PERMISSION, menuPermission);
    }

    /**
     * 设置用户数据(多级缓存)
     */
    public static void setLoginUser(AdminLoginUser loginUser) {
        StpUtil.getTokenSession().set(LOGIN_USER_KEY, loginUser);
    }

    /**
     * 获取用户(多级缓存)
     */
    @SuppressWarnings("unchecked cast")
    public static AdminLoginUser getLoginUser() {
        AdminLoginUser loginUser = (AdminLoginUser) SaHolder.getStorage().get(LOGIN_USER_KEY);
        if (loginUser != null) {
            return loginUser;
        }
        loginUser = (AdminLoginUser) StpUtil.getTokenSession().get(LOGIN_USER_KEY);
        // 解决菜单权限过度 token 臃肿过长问题
        Set<String> menuPermission = (Set<String>) StpUtil.getTokenSession().get(MENU_PERMISSION);
        loginUser.setMenuPermission(menuPermission);
        SaHolder.getStorage().set(LOGIN_USER_KEY, loginUser);
        return loginUser;
    }

    /**
     * 获取用户基于token
     */
    public static AdminLoginUser getLoginUser(String token) {
        AdminLoginUser loginUser = (AdminLoginUser) StpUtil.getExtra(token, LOGIN_USER_KEY);
        // 解决菜单权限过多 token 臃肿过长问题
        Set<String> menuPermission = (Set<String>) StpUtil.getTokenSessionByToken(token).get(MENU_PERMISSION);
        loginUser.setMenuPermission(menuPermission);
        return loginUser;
    }

    /**
     * 获取用户id
     */
    public static Long getUserId() {
        AdminLoginUser loginUser;
        try {
            loginUser = getLoginUser();
        } catch (Exception e) {
            return null;
        }
        return loginUser.getUserId();
    }

    /**
     * 获取部门ID
     */
    public static Long getDeptId() {
        return getLoginUser().getDeptId();
    }

    /**
     * 获取用户账户
     */
    public static String getUsername() {
        return getLoginUser().getUsername();
    }

    /**
     * 获取用户类型
     */
    public static UserTypeEnum getUserType() {
        String loginId = StpUtil.getLoginIdAsString();
        return UserTypeEnum.getUserType(loginId);
    }

    /**
     * 是否为管理员
     *
     * @param userId 用户ID
     * @return 结果
     */
    public static boolean isAdmin(Long userId) {
        return UserConstants.ADMIN_ID.equals(userId);
    }

    public static boolean isAdmin() {
        return isAdmin(getUserId());
    }

}
