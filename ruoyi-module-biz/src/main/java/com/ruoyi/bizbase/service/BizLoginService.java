package com.ruoyi.bizbase.service;

import cn.binarywang.wx.miniapp.bean.WxMaJscode2SessionResult;
import cn.dev33.satoken.secure.BCrypt;
import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.core.util.ObjectUtil;
import com.ruoyi.biz.domain.model.BizLoginUser;
import com.ruoyi.biz.helper.BizLoginHelper;
import com.ruoyi.bizbase.model.bo.SmsLoginBindWeixinBo;
import com.ruoyi.common.constant.CacheConstants;
import com.ruoyi.common.constant.Constants;
import com.ruoyi.common.core.domain.entity.BizUser;
import com.ruoyi.common.core.service.LogininforService;
import com.ruoyi.common.enums.DeviceType;
import com.ruoyi.common.enums.LoginType;
import com.ruoyi.common.enums.UnionAuthType;
import com.ruoyi.common.enums.UserStatus;
import com.ruoyi.common.exception.ServiceException;
import com.ruoyi.common.exception.user.CaptchaException;
import com.ruoyi.common.exception.user.CaptchaExpireException;
import com.ruoyi.common.exception.user.UserException;
import com.ruoyi.common.utils.*;
import com.ruoyi.common.utils.redis.RedisUtils;
import com.ruoyi.system.domain.bo.BizUserAddBo;
import com.ruoyi.system.domain.bo.BizUserEditBo;
import com.ruoyi.system.service.IBizUserService;
import com.ruoyi.system.service.ISysConfigService;
import com.ruoyi.weixin.miniapp.WeixinMiniappService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.time.Duration;
import java.util.function.Supplier;

/**
 * 业务用户登录校验方法
 *
 * @author weibocy
 */
@Slf4j
@Service
public class BizLoginService {

    @Resource
    private IBizUserService userService;
    @Resource
    private ISysConfigService configService;

    /**
     * 业务用户登录日志服务
     * 前后台用户分开，暂时用Qualifier区分方案，此包暂时无法脱离system模块
     */
    @Resource
    @Qualifier("bizLogininforServiceImpl")
    private LogininforService asyncService;


    /**
     * 微信模块
     */
    @Resource
    private WeixinMiniappService weixinMiniappService;


    /**
     * 登录验证
     *
     * @param username 用户名
     * @param password 密码
     * @param code     验证码
     * @param uuid     唯一标识
     * @return 结果
     */
    public String login(String username, String password, String code, String uuid) {
        HttpServletRequest request = ServletUtils.getRequest();
        boolean captchaEnabled = configService.selectCaptchaEnabled();
        // 验证码开关
        if (captchaEnabled) {
            validateCaptcha(username, code, uuid, request);
        }
        BizUser user = loadUserByUsername(username);
        checkLogin(LoginType.PASSWORD, username, () -> !BCrypt.checkpw(password, user.getPassword()));
        // 此处可根据登录用户的数据不同 自行创建 loginUser
        BizLoginUser loginUser = buildLoginUser(user);
        // 生成token
        BizLoginHelper.loginByDevice(loginUser, DeviceType.XCX);

        asyncService.recordLogininfor(username, Constants.LOGIN_SUCCESS, MessageUtils.message("user.login.success"), request);
        recordLoginInfo(user.getUserId(), username);
        return StpUtil.getTokenValue();
    }

    /**
     * 短信验证码登录
     * @param phoneNumber 手机号
     * @param smsCode 验证码
     * @return
     */
    public String smsLogin(String phoneNumber, String smsCode) {

        // 先做验证码验证，因为下一步会自动注册不存在的账号
        HttpServletRequest request = ServletUtils.getRequest();
        checkLogin(LoginType.SMS, phoneNumber, () -> !validateSmsCode(phoneNumber, smsCode, request));

        // 通过手机号查找用户(不存在会自动注册)
        BizUser user = loadUserByPhoneNumber(phoneNumber);
        // 登录成功后，删除验证码数据，确保只有一次成功可用
        deleteSmsCode(phoneNumber);
        // 此处可根据登录用户的数据不同 自行创建 loginUser
        BizLoginUser loginUser = buildLoginUser(user);
        // 生成token
        BizLoginHelper.loginByDevice(loginUser, DeviceType.XCX);

        asyncService.recordLogininfor(user.getUserName(), Constants.LOGIN_SUCCESS, MessageUtils.message("user.login.success"), request);
        recordLoginInfo(user.getUserId(), user.getUserName());
        return StpUtil.getTokenValue();
    }


    /**
     * 短信验证码登录并绑定小程序微信
     * @param smsLoginBody 短信登录并绑定微信对象
     * @return
     */
    public String smsLoginBindWeixin(SmsLoginBindWeixinBo smsLoginBody) {

        // 先做验证码验证，因为下一步会自动注册不存在的账号
        HttpServletRequest request = ServletUtils.getRequest();
        checkLogin(LoginType.SMS, smsLoginBody.getPhoneNumber(), () -> !validateSmsCode(smsLoginBody.getPhoneNumber(), smsLoginBody.getSmsCode(), request));

        // 验证小程序code
        WxMaJscode2SessionResult result = weixinMiniappService.getWxSession(smsLoginBody.getAppid(), smsLoginBody.getXcxCode());
        if (result == null){
            throw  new ServiceException("小程序code无效");
        }
        String openid = result.getOpenid();
        String unionid = result.getUnionid();

        // 通过手机号和appid查找用户(不存在会自动注册)
        BizUser user = loadUserByPhoneNumberAndAppid(smsLoginBody.getPhoneNumber(), smsLoginBody.getAppid());
        // 登录成功后，删除验证码数据，确保只有一次成功可用
        deleteSmsCode(smsLoginBody.getPhoneNumber());
        // 微信绑定
        userService.bindWeixin(smsLoginBody.getAppid(), user.getUserId(), openid, unionid);
        // 绑定微信后重新读取
        user = loadUserByPhoneNumberAndAppid(smsLoginBody.getPhoneNumber(), smsLoginBody.getAppid());
        // 此处可根据登录用户的数据不同 自行创建 loginUser
        BizLoginUser loginUser = buildLoginUser(user);
        // 设置关联登录账号信息
        loginUser.setUnionAuthLogin(true);
        loginUser.setUnionAuthType(UnionAuthType.WEIXIN_MINIAPP.getUnionAuthType());
        // 生成token
        BizLoginHelper.loginByDevice(loginUser, DeviceType.XCX);

        asyncService.recordLogininfor(user.getUserName(), Constants.LOGIN_SUCCESS, MessageUtils.message("user.login.success"), request);
        recordLoginInfo(user.getUserId(), user.getUserName());
        return StpUtil.getTokenValue();
    }

    /**
     * 小程序登录
     * @param appid
     * @param xcxCode
     * @return
     */
    public String xcxLogin(String appid, String xcxCode) {
        HttpServletRequest request = ServletUtils.getRequest();
        WxMaJscode2SessionResult result = weixinMiniappService.getWxSession(appid, xcxCode);
        if (result == null){
            throw  new ServiceException("code无效");
        }
        String openid = result.getOpenid();
        BizUser user = loadUserByOpenid(openid);

        // 此处可根据登录用户的数据不同 自行创建 loginUser
        BizLoginUser loginUser = buildLoginUser(user);
        // 设置关联登录账号信息
        loginUser.setUnionAuthLogin(true);
        loginUser.setUnionAuthType(UnionAuthType.WEIXIN_MINIAPP.getUnionAuthType());
        // 生成token
        BizLoginHelper.loginByDevice(loginUser, DeviceType.XCX);

        asyncService.recordLogininfor(user.getUserName(), Constants.LOGIN_SUCCESS, MessageUtils.message("user.login.success"), request);
        recordLoginInfo(user.getUserId(), user.getUserName());
        return StpUtil.getTokenValue();
    }



    public void logout(String loginName) {
        asyncService.recordLogininfor(loginName, Constants.LOGOUT, MessageUtils.message("user.logout.success"), ServletUtils.getRequest());
    }

    /**
     * 校验短信验证码
     */
    public boolean validateSmsCode(String phoneNumber, String smsCode, HttpServletRequest request) {
        String code = RedisUtils.getCacheObject(CacheConstants.CAPTCHA_CODE_KEY + phoneNumber);
        if (StringUtils.isBlank(code)) {
            asyncService.recordLogininfor(phoneNumber, Constants.LOGIN_FAIL, MessageUtils.message("user.jcaptcha.expire"), request);
            throw new CaptchaExpireException();
        }
        return code.equals(smsCode);
    }


    /**
     * 删除短信验证码
     * 用于登录成功后删除，防止二次利用
     */
    public boolean deleteSmsCode(String phoneNumber) {

        return RedisUtils.deleteObject(CacheConstants.CAPTCHA_CODE_KEY + phoneNumber);
    }

    /**
     * 校验验证码
     *
     * @param username 用户名
     * @param code     验证码
     * @param uuid     唯一标识
     */
    public void validateCaptcha(String username, String code, String uuid, HttpServletRequest request) {
        String verifyKey = CacheConstants.CAPTCHA_CODE_KEY + StringUtils.defaultString(uuid, "");
        String captcha = RedisUtils.getCacheObject(verifyKey);
        RedisUtils.deleteObject(verifyKey);
        if (captcha == null) {
            asyncService.recordLogininfor(username, Constants.LOGIN_FAIL, MessageUtils.message("user.jcaptcha.expire"), request);
            throw new CaptchaExpireException();
        }
        if (!code.equalsIgnoreCase(captcha)) {
            asyncService.recordLogininfor(username, Constants.LOGIN_FAIL, MessageUtils.message("user.jcaptcha.error"), request);
            throw new CaptchaException();
        }
    }

    /**
     * 根据用户名获取用户信息
     * @param username 用户名
     * @return
     */
    public BizUser loadUserByUsername(String username) {
        BizUser user = userService.selectUserByUserName(username);
        if (ObjectUtil.isNull(user)) {
            log.info("登录用户：{} 不存在.", username);
            throw new UserException("user.not.exists", username);
        } else if (UserStatus.DELETED.getCode().equals(user.getDelFlag())) {
            log.info("登录用户：{} 已被删除.", username);
            throw new UserException("user.password.delete", username);
        } else if (UserStatus.DISABLE.getCode().equals(user.getStatus())) {
            log.info("登录用户：{} 已被停用.", username);
            throw new UserException("user.blocked", username);
        }
        return user;
    }

    /**
     * 根据手机号获取用户信息
     * @param phoneNumber 手机号码
     * @return
     */
    public BizUser loadUserByPhoneNumber(String phoneNumber) {
        BizUser user = userService.selectUserByPhoneNumber(phoneNumber);
        if (ObjectUtil.isNull(user)) {
            // 手机号登录，第一次登录则写入新用户
            BizUserAddBo addBo = new BizUserAddBo();
            addBo.setUserName(phoneNumber);
            addBo.setPhoneNumber(phoneNumber);
            userService.insertByBo(addBo);
            log.info("自动注册手机号用户：{} .", phoneNumber);
            // 注册账户后再读取一次
            user = userService.selectUserByPhoneNumber(phoneNumber);
        } else if (UserStatus.DELETED.getCode().equals(user.getDelFlag())) {
            log.info("登录用户：{} 已被删除.", phoneNumber);
            throw new UserException("user.password.delete", phoneNumber);
        } else if (UserStatus.DISABLE.getCode().equals(user.getStatus())) {
            log.info("登录用户：{} 已被停用.", phoneNumber);
            throw new UserException("user.blocked", phoneNumber);
        }
        return user;
    }

    /**
     * 根据手机号获取用户信息
     * @param phoneNumber 手机号码
     * @param appid appid
     * @return
     */
    public BizUser loadUserByPhoneNumberAndAppid(String phoneNumber, String appid) {
        BizUser user = userService.selectUserByPhoneNumberAndAppid(phoneNumber, appid);
        if (ObjectUtil.isNull(user)) {
            // 手机号登录，第一次登录则写入新用户
            BizUserAddBo addBo = new BizUserAddBo();
            addBo.setAppid(appid);
            addBo.setUserName(phoneNumber);
            addBo.setPhoneNumber(phoneNumber);
            userService.insertByBo(addBo);
            log.info("自动注册手机号用户：{} .", phoneNumber);
            // 注册账户后再读取一次
            user = userService.selectUserByPhoneNumberAndAppid(phoneNumber, appid);
        } else if (UserStatus.DELETED.getCode().equals(user.getDelFlag())) {
            log.info("登录用户：{} 已被删除.", phoneNumber);
            throw new UserException("user.password.delete", phoneNumber);
        } else if (UserStatus.DISABLE.getCode().equals(user.getStatus())) {
            log.info("登录用户：{} 已被停用.", phoneNumber);
            throw new UserException("user.blocked", phoneNumber);
        }
        return user;
    }

    /**
     * 根据openid获取用户信息
     * @param openid openid
     * @return
     */
    public BizUser loadUserByOpenid(String openid) {
        // 使用 openid 查询绑定用户 如未绑定用户 则根据业务自行处理 例如 创建默认用户
        BizUser user = userService.selectUserByOpenid(openid);
        if (ObjectUtil.isNull(user)) {
            log.info("登录用户：{} 不存在.", openid);
            throw new UserException("user.not.exists", openid);
        } else if (UserStatus.DELETED.getCode().equals(user.getDelFlag())) {
            log.info("登录用户：{} 已被删除.", openid);
            throw new UserException("user.password.delete", openid);
        } else if (UserStatus.DISABLE.getCode().equals(user.getStatus())) {
            log.info("登录用户：{} 已被停用.", openid);
            throw new UserException("user.blocked", openid);
        }
        return user;
    }

    /**
     * 构建登录用户
     */
    public BizLoginUser buildLoginUser(BizUser user) {
        BizLoginUser loginUser = BeanCopyUtils.copy(user, BizLoginUser.class);
        return loginUser;
    }

    /**
     * 记录登录信息
     *
     * @param userId 用户ID
     */
    public void recordLoginInfo(Long userId, String username) {
        BizUserEditBo user = new BizUserEditBo();
        user.setUserId(userId);
        user.setLoginIp(ServletUtils.getClientIP());
        user.setLoginDate(DateUtils.getNowDate());
        user.setUpdateBy(username);
        userService.updateByBo(user);
    }

    /**
     * 登录校验
     */
    public void checkLogin(LoginType loginType, String username, Supplier<Boolean> supplier) {
        HttpServletRequest request = ServletUtils.getRequest();
        String errorKey = CacheConstants.LOGIN_ERROR + username;
        Integer errorLimitTime = Constants.LOGIN_ERROR_LIMIT_TIME;
        Integer setErrorNumber = Constants.LOGIN_ERROR_NUMBER;
        String loginFail = Constants.LOGIN_FAIL;

        // 获取用户登录错误次数(可自定义限制策略 例如: key + username + ip)
        Integer errorNumber = RedisUtils.getCacheObject(errorKey);
        // 锁定时间内登录 则踢出
        if (ObjectUtil.isNotNull(errorNumber) && errorNumber.equals(setErrorNumber)) {
            asyncService.recordLogininfor(username, loginFail, MessageUtils.message(loginType.getRetryLimitExceed(), errorLimitTime), request);
            throw new UserException(loginType.getRetryLimitExceed(), errorLimitTime);
        }

        if (supplier.get()) {
            // 是否第一次
            errorNumber = ObjectUtil.isNull(errorNumber) ? 1 : errorNumber + 1;
            // 达到规定错误次数 则锁定登录
            if (errorNumber.equals(setErrorNumber)) {
                RedisUtils.setCacheObject(errorKey, errorNumber, Duration.ofMinutes(errorLimitTime));
                asyncService.recordLogininfor(username, loginFail, MessageUtils.message(loginType.getRetryLimitExceed(), errorLimitTime), request);
                throw new UserException(loginType.getRetryLimitExceed(), errorLimitTime);
            } else {
                // 未达到规定错误次数 则递增
                RedisUtils.setCacheObject(errorKey, errorNumber);
                asyncService.recordLogininfor(username, loginFail, MessageUtils.message(loginType.getRetryLimitCount(), errorNumber), request);
                throw new UserException(loginType.getRetryLimitCount(), errorNumber);
            }
        }

        // 登录成功 清空错误次数
        RedisUtils.deleteObject(errorKey);
    }
}
