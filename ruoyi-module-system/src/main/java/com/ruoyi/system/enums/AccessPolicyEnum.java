package com.ruoyi.system.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.fasterxml.jackson.annotation.JsonValue;

/**
 * 权限类型枚举
 *
 * @author weibocy
 */
public enum AccessPolicyEnum {
    PUBLIC("PUBLIC", "公开"),
    PRIVATE("PRIVATE", "私有"),
    CUSTOM("CUSTOM", "自定义"),
    ;

    // 标记响应mbp值
    @EnumValue
    // 标记响应json值
    @JsonValue
    private final String code;
    private final String info;

    AccessPolicyEnum(String code, String info) {
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
