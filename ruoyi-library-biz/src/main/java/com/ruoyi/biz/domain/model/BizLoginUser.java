package com.ruoyi.biz.domain.model;

import com.ruoyi.biz.helper.BizLoginHelper;
import com.ruoyi.common.enums.UserSexEnum;
import com.ruoyi.common.enums.UserTypeEnum;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 业务用户身份权限
 *
 * @author weibocy
 */
@Data
@NoArgsConstructor
public class BizLoginUser implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 用户ID
     */
    private Long userId;

    /**
     * 用户名
     */
    private String username;


    /**
     * 手机号码
     */
    private String phoneNumber;

    /**
     * appid
     */
    private String appid;

    /**
     * unionid
     */
    private String unionid;

    /**
     * openid
     */
    private String openid;

    /**
     * 用户性别（UNKNOWN=未知 MAN=男 WOMAN=女）
     */
    private UserSexEnum sex;

    /**
     * 国家
     */
    private String country;

    /**
     * 省份
     */
    private String province;

    /**
     * 城市
     */
    private String city;


    /**
     * 用户唯一标识
     */
    private String token;

    /**
     * 用户类型（PC=电脑端 ANDROID=安卓app端 IOS=苹果app端 WXAPP=微信小程序端 WXMP=微信公众号端 ALIPAYAPP=支付宝小程序端）
     */
    private UserTypeEnum userType;

    /**
     * 登录时间
     */
    private Long loginTime;

    /**
     * 过期时间
     */
    private Long expireTime;

    /**
     * 登录IP地址
     */
    private String ipaddr;

    /**
     * 登录地点
     */
    private String loginLocation;

    /**
     * 浏览器类型
     */
    private String browser;

    /**
     * 操作系统
     */
    private String os;


    /**
     * 是否是关联账户登录
     */
    private Boolean unionAuthLogin = false;


    /**
     * 关联账户类型
     */
    private String unionAuthType;


    /**
     * 获取登录id
     */
    public String getLoginId() {
        if (userType == null) {
            throw new IllegalArgumentException("用户类型不能为空");
        }
        if (userId == null) {
            throw new IllegalArgumentException("用户ID不能为空");
        }
        return userType + ":" + userId;
    }


}
