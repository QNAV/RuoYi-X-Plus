package com.ruoyi.biz.listener;

import cn.dev33.satoken.config.SaTokenConfig;
import cn.dev33.satoken.listener.SaTokenListener;
import cn.dev33.satoken.stp.SaLoginModel;
import cn.hutool.http.useragent.UserAgent;
import cn.hutool.http.useragent.UserAgentUtil;
import com.ruoyi.biz.domain.bo.BizUserOnlineBo;
import com.ruoyi.biz.domain.model.BizLoginUser;
import com.ruoyi.biz.helper.BizLoginHelper;
import com.ruoyi.common.constant.CacheConstants;
import com.ruoyi.common.constant.CacheNames;
import com.ruoyi.common.enums.UserType;
import com.ruoyi.common.utils.ServletUtils;
import com.ruoyi.common.utils.ip.AddressUtils;
import com.ruoyi.common.utils.redis.CacheUtils;
import com.ruoyi.common.utils.redis.RedisUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

import java.time.Duration;

/**
 * 业务用户行为 侦听器的实现
 *
 * @author weibocy
 */
@RequiredArgsConstructor
@Component
@Slf4j
@ConditionalOnProperty(prefix = "ruoyi", name = "appType", havingValue = "biz")
public class BizUserActionListener implements SaTokenListener {

    private final SaTokenConfig tokenConfig;

    /**
     * 每次登录时触发
     */
    @Override
    public void doLogin(String loginType, Object loginId, String tokenValue, SaLoginModel loginModel) {
        UserType userType = UserType.getUserType(loginId.toString());

        UserAgent userAgent = UserAgentUtil.parse(ServletUtils.getRequest().getHeader("User-Agent"));
        String ip = ServletUtils.getClientIP();
        BizLoginUser user = BizLoginHelper.getLoginUser();
        BizUserOnlineBo dto = new BizUserOnlineBo();
        dto.setIpaddr(ip);
        dto.setLoginLocation(AddressUtils.getRealAddressByIP(ip));
        dto.setBrowser(userAgent.getBrowser().getName());
        dto.setOs(userAgent.getOs().getName());
        dto.setLoginTime(System.currentTimeMillis());
        dto.setTokenId(tokenValue);
        dto.setUserName(user.getUsername());
        if(tokenConfig.getTimeout() == -1) {
            RedisUtils.setCacheObject(CacheConstants.ONLINE_BIZ_TOKEN_KEY + tokenValue, dto);
        } else {
            RedisUtils.setCacheObject(CacheConstants.ONLINE_BIZ_TOKEN_KEY + tokenValue, dto, Duration.ofSeconds(tokenConfig.getTimeout()));
        }
        log.info("user doLogin, userId:{}, token:{}", loginId, tokenValue);
    }

    /**
     * 每次注销时触发
     */
    @Override
    public void doLogout(String loginType, Object loginId, String tokenValue) {
        CacheUtils.evict(CacheNames.ONLINE_BIZ_TOKEN, tokenValue);
        log.info("user doLogout, userId:{}, token:{}", loginId, tokenValue);
    }

    /**
     * 每次被踢下线时触发
     */
    @Override
    public void doKickout(String loginType, Object loginId, String tokenValue) {
        CacheUtils.evict(CacheNames.ONLINE_BIZ_TOKEN, tokenValue);
        log.info("user doLogoutByLoginId, userId:{}, token:{}", loginId, tokenValue);
    }

    /**
     * 每次被顶下线时触发
     */
    @Override
    public void doReplaced(String loginType, Object loginId, String tokenValue) {
        CacheUtils.evict(CacheNames.ONLINE_BIZ_TOKEN, tokenValue);
        log.info("user doReplaced, userId:{}, token:{}", loginId, tokenValue);
    }

    /**
     * 每次被封禁时触发
     */
    @Override
    public void doDisable(String loginType, Object loginId, String service, int level, long disableTime) {
    }

    /**
     * 每次被解封时触发
     */
    @Override
    public void doUntieDisable(String loginType, Object loginId, String service) {
    }

    /**
     * 每次创建Session时触发
     */
    @Override
    public void doCreateSession(String id) {
    }

    /**
     * 每次注销Session时触发
     */
    @Override
    public void doLogoutSession(String id) {
    }


    /**
     * 每次Token续期时触发
     */
    @Override
    public void doRenewTimeout(String tokenValue, Object loginId, long timeout) {
    }


}
