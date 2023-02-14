package com.ruoyi.common.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.fasterxml.jackson.annotation.JsonValue;
import com.ruoyi.common.utils.StringUtils;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 设备类型
 * 针对多套 用户体系
 *
 * @author Lion Li
 */
@Getter
@AllArgsConstructor
public enum UserType {

    /**
     * 电脑端
     */
    PC("PC"),

    /**
     * 安卓app端
     */
    ANDROID("ANDROID"),

    /**
     * 苹果app端
     */
    IOS("IOS"),

    /**
     * 微信小程序端
     */
    WXAPP("WXAPP"),

    /**
     * 微信公众号端
     */
    WXMP("WXMP"),

    /**
     * 支付宝小程序端
     */
    ALIPAYAPP("ALIPAYAPP");

    // 标记响应mbp值
    @EnumValue
    // 标记响应json值
    @JsonValue
    private final String userType;

    public static UserType getUserType(String str) {
        for (UserType value : values()) {
            if (StringUtils.contains(str, value.getUserType())) {
                return value;
            }
        }
        throw new RuntimeException("'UserType' not found By " + str);
    }
}
