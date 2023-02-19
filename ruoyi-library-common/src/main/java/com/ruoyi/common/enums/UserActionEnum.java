package com.ruoyi.common.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.fasterxml.jackson.annotation.JsonValue;

/**
 * 用户动作枚举
 *
 * @author ruoyi
 */
public enum UserActionEnum {
    LOGINOK("LOGINOK", "登录成功"),
    LOGINFAIL("LOGINFAIL", "登录失败"),
    LOGOUT("LOGOUT", "注销登录"),
    REGISTER("REGISTER", "注册"),

    ;

    // 标记响应mbp值
    @EnumValue
    // 标记响应json值
    @JsonValue
    private final String code;
    private final String info;

    UserActionEnum(String code, String info) {
        this.code = code;
        this.info = info;
    }

    public String getCode() {
        return code;
    }

    public String getInfo() {
        return info;
    }
}
