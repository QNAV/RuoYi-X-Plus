package com.ruoyi.common.constant;

/**
 * 缓存的key 常量
 *
 * @author ruoyi
 */
public interface CacheConstants
{
    /**
     * 后台登录用户 redis key
     */
    String ADMIN_LOGIN_TOKEN_KEY = "admin_login_tokens:";


    /**
     * 业务登录用户 redis key
     */
    String BIZ_LOGIN_TOKEN_KEY = "biz_login_tokens:";

    /**
     * 登陆错误 redis key
     */
    String LOGIN_ERROR = "login_error:";

    /**
     * 验证码 redis key
     */
    String CAPTCHA_CODE_KEY = "captcha_codes:";

    /**
     * 短信验证码 redis key
     */
    String SMS_CAPTCHA_CODE_KEY = "sms_captcha_codes:";

    /**
     * 验证码有效期（分钟）
     */
    Integer CAPTCHA_EXPIRATION = 2;

    /**
     * 参数管理 cache key
     */
    String SYS_CONFIG_KEY = "sys_config:";

    /**
     * 字典管理 cache key
     */
    String SYS_DICT_KEY = "sys_dict:";

    /**
     * 防重提交 redis key
     */
    String REPEAT_SUBMIT_KEY = "repeat_submit:";

    /**
     * 限流 redis key
     */
    String RATE_LIMIT_KEY = "rate_limit:";

    /**
     * 在线业务用户 redis key
     */
    String ONLINE_BIZ_TOKEN_KEY = "online_biz_tokens:";

    /**
     * 在线后台用户 redis key
     */
    String ONLINE_ADMIN_TOKEN_KEY = "online_admin_tokens:";

}
