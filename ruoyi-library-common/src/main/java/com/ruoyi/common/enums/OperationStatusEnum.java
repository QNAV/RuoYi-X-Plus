package com.ruoyi.common.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.fasterxml.jackson.annotation.JsonValue;

/**
 * 操作状态枚举
 *
 * @author weibocy
 */
public enum OperationStatusEnum {
    NORMAL("NORMAL", "正常"),
    EXCEPTION("EXCEPTION", "异常"),
    ;

    // 标记响应mbp值
    @EnumValue
    // 标记响应json值
    @JsonValue
    private final String code;
    private final String info;

    OperationStatusEnum(String code, String info) {
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
