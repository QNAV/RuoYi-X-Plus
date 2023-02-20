package com.ruoyi.biz.helper;

import cn.dev33.satoken.context.SaHolder;
import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.core.util.ObjectUtil;
import com.ruoyi.biz.domain.model.BizLoginUser;
import com.ruoyi.common.enums.DeviceType;
import com.ruoyi.common.enums.UserTypeEnum;
import com.ruoyi.common.exception.UtilException;
import com.ruoyi.common.utils.StringUtils;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

/**
 * 业务登录鉴权助手
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class BizLoginHelper {

    public static final String JOIN_CODE = ":";
    public static final String LOGIN_USER_KEY = "loginBizUser";

    /**
     * 登录系统
     *
     * @param loginUser 登录用户信息
     */
    public static void login(BizLoginUser loginUser) {
        SaHolder.getStorage().set(LOGIN_USER_KEY, loginUser);
        StpUtil.login(loginUser.getLoginId());
        setLoginUser(loginUser);
    }

    /**
     * 登录系统 基于 设备类型
     * 针对相同用户体系不同设备
     *
     * @param loginUser 登录用户信息
     */
    public static void loginByDevice(BizLoginUser loginUser, DeviceType deviceType) {
        SaHolder.getStorage().set(LOGIN_USER_KEY, loginUser);
        StpUtil.login(loginUser.getLoginId(), deviceType.getDevice());
        setLoginUser(loginUser);
    }



    /**
     * 设置用户数据(多级缓存)
     */
    public static void setLoginUser(BizLoginUser loginUser) {
        StpUtil.getTokenSession().set(LOGIN_USER_KEY, loginUser);
    }

    /**
     * 获取用户(多级缓存)
     */
    public static BizLoginUser getLoginUser() {
        BizLoginUser loginUser = (BizLoginUser) SaHolder.getStorage().get(LOGIN_USER_KEY);
        if (loginUser != null) {
            return loginUser;
        }
        loginUser = (BizLoginUser) StpUtil.getTokenSession().get(LOGIN_USER_KEY);
        SaHolder.getStorage().set(LOGIN_USER_KEY, loginUser);
        return loginUser;
    }

    /**
     * 获取用户id
     */
    public static Long getUserId() {
        BizLoginUser loginUser = getLoginUser();
        if (ObjectUtil.isNull(loginUser)) {
            String loginId = StpUtil.getLoginIdAsString();
            String userId = null;
            for (UserTypeEnum value : UserTypeEnum.values()) {
                if (StringUtils.contains(loginId, value.getCode())) {
                    String[] strs = StringUtils.split(loginId, JOIN_CODE);
                    // 用户id在总是在最后
                    userId = strs[strs.length - 1];
                }
            }
            if (StringUtils.isBlank(userId)) {
                throw new UtilException("登录用户: LoginId异常 => " + loginId);
            }
            return Long.parseLong(userId);
        }
        return loginUser.getUserId();
    }


    /**
     * 获取用户账户
     */
    public static String getUsername() {
        return getLoginUser().getUsername();
    }

    /**
     * 获取用户手机号
     */
    public static String getPhoneNumber() {
        return getLoginUser().getPhoneNumber();
    }

    /**
     * 获取用户类型
     */
    public static UserTypeEnum getUserType() {
        String loginId = StpUtil.getLoginIdAsString();
        return UserTypeEnum.getUserType(loginId);
    }
}
