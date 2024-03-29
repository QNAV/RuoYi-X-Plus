package com.ruoyi.system.service;

import cn.dev33.satoken.secure.BCrypt;
import com.ruoyi.common.constant.CacheConstants;
import com.ruoyi.common.core.domain.bo.UserNameRegisterBo;
import com.ruoyi.common.core.domain.entity.SysUser;
import com.ruoyi.common.core.domain.event.AdminLogininforEvent;
import com.ruoyi.common.enums.CommonYesOrNoEnum;
import com.ruoyi.common.enums.LoginActionEnum;
import com.ruoyi.common.exception.user.CaptchaException;
import com.ruoyi.common.exception.user.CaptchaExpireException;
import com.ruoyi.common.exception.user.UserException;
import com.ruoyi.common.utils.MessageUtils;
import com.ruoyi.common.utils.ServletUtils;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.utils.redis.RedisUtils;
import com.ruoyi.common.utils.spring.SpringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * 注册校验方法
 *
 * @author Lion Li
 */
@Service
public class SysRegisterService {

    @Resource
    private ISysUserService userService;

    @Resource
    private ISysConfigService configService;

    /**
     * 注册
     */
    public void register(UserNameRegisterBo registerBody) {
        HttpServletRequest request = ServletUtils.getRequest();
        String username = registerBody.getUsername();
        String password = registerBody.getPassword();

        boolean captchaEnabled = configService.selectCaptchaEnabled();
        // 验证码开关
        if (captchaEnabled) {
            validateCaptcha(username, registerBody.getCode(), registerBody.getUuid(), request);
        }
        SysUser sysUser = new SysUser();
        sysUser.setUserName(username);
        sysUser.setNickName(username);
        sysUser.setPassword(BCrypt.hashpw(password));
        sysUser.setUserType(registerBody.getUserType());
        if (CommonYesOrNoEnum.NO.equals(userService.checkUserNameUnique(sysUser))) {
            throw new UserException("user.register.save.error", username);
        }
        boolean regFlag = userService.registerUser(sysUser);
        if (!regFlag) {
            throw new UserException("user.register.error");
        }
        recordLogininfor(username, LoginActionEnum.REGISTER, MessageUtils.message("user.register.success"));
    }

    /**
     * 校验验证码
     *
     * @param username 用户名
     * @param code     验证码
     * @param uuid     唯一标识
     * @return 结果
     */
    public void validateCaptcha(String username, String code, String uuid, HttpServletRequest request) {
        String verifyKey = CacheConstants.CAPTCHA_CODE_KEY + StringUtils.defaultString(uuid, "");
        String captcha = RedisUtils.getCacheObject(verifyKey);
        RedisUtils.deleteObject(verifyKey);
        if (captcha == null) {
            recordLogininfor(username, LoginActionEnum.REGISTER, MessageUtils.message("user.jcaptcha.expire"));
            throw new CaptchaExpireException();
        }
        if (!code.equalsIgnoreCase(captcha)) {
            recordLogininfor(username, LoginActionEnum.REGISTER, MessageUtils.message("user.jcaptcha.error"));
            throw new CaptchaException();
        }
    }

    /**
     * 记录登录信息
     *
     * @param username 用户名
     * @param status   状态
     * @param message  消息内容
     * @return
     */
    private void recordLogininfor(String username, LoginActionEnum status, String message) {
        AdminLogininforEvent logininforDTO = new AdminLogininforEvent();
        logininforDTO.setUsername(username);
        logininforDTO.setStatus(status);
        logininforDTO.setMessage(message);
        logininforDTO.setRequest(ServletUtils.getRequest());
        SpringUtils.context().publishEvent(logininforDTO);
    }
}
