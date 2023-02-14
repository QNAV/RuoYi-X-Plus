package com.ruoyi.system.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.fasterxml.jackson.annotation.JsonValue;

/**
 * 通知公告类型枚举
 *
 * @author weibocy
 */
public enum NoticeTypeEnum {
    NOTICE("NOTICE", "通知"),
    BULLETIN("BULLETIN", "公告");

    // 标记响应mbp值
    @EnumValue
    // 标记响应json值
    @JsonValue
    private final String code;
    private final String info;

    NoticeTypeEnum(String code, String info) {
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
