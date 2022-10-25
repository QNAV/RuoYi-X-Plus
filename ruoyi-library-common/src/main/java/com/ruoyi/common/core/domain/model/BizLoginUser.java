package com.ruoyi.common.core.domain.model;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 业务用户身份权限
 *
 * @author weibocy
 */
@Data
@NoArgsConstructor
public class BizLoginUser extends LoginUser {

    private static final long serialVersionUID = 1L;

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
     * 用户性别（1男 2女 0未知）
     */
    private String sex;

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


}
