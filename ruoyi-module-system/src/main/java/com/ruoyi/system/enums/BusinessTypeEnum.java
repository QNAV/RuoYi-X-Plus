package com.ruoyi.system.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.fasterxml.jackson.annotation.JsonValue;

/**
 * 业务类型枚举
 *
 * @author weibocy
 */
public enum BusinessTypeEnum {
    ADD("ADD", "新增"),
    MODIFY("MODIFY", "修改"),
    DELETE("DELETE", "删除"),
    GRANT("GRANT", "授权"),
    EXPORT("EXPORT", "导出"),
    IMPORT("IMPORT", "导入"),
    FORCED("FORCED", "强退"),
    GENCODE("GENCODE", "生成代码"),
    CLEAR("CLEAR", "清空数据"),
    OTHER("OTHER", "其他"),
    ;

    // 标记响应mbp值
    @EnumValue
    // 标记响应json值
    @JsonValue
    private final String code;
    private final String info;

    BusinessTypeEnum(String code, String info) {
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
