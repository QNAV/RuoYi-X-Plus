package com.ruoyi.system.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.fasterxml.jackson.annotation.JsonValue;

/**
 * 参数值类型枚举
 *
 * @author weibocy
 */
public enum ConfigValueTypeEnum {
    TEXT("TEXT", "文本"),
    BOOLEAN("BOOLEAN", "布尔"),
    DATETIME("DATETIME", "日期");

    // 标记响应mbp值
    @EnumValue
    // 标记响应json值
    @JsonValue
    private final String code;
    private final String info;

    ConfigValueTypeEnum(String code, String info) {
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
