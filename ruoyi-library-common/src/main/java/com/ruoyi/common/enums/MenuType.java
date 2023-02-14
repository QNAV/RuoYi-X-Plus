package com.ruoyi.common.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.fasterxml.jackson.annotation.JsonValue;

/**
 * 菜单类型枚举
 *
 * @author weibocy
 */
public enum MenuType {
    DIRECTORY("DIRECTORY", "目录"),
    MENU("MENU", "菜单"),
    BUTTON("BUTTON", "按钮");

    // 标记响应mbp值
    @EnumValue
    // 标记响应json值
    @JsonValue
    private final String code;
    private final String info;

    MenuType(String code, String info) {
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
