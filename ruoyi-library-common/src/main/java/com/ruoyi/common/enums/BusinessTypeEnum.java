package com.ruoyi.common.enums;

import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.annotation.EnumValue;
import com.fasterxml.jackson.annotation.JsonValue;

import java.util.HashMap;
import java.util.Map;

/**
 * 业务类型枚举
 *
 * @author weibocy
 */
public enum BusinessTypeEnum implements BaseEnum {
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

    // code对应的枚举map
    public static Map<String, BusinessTypeEnum> CMAPS= new HashMap<>();
    // info对应的枚举map
    public static Map<String, BusinessTypeEnum> IMAPS= new HashMap<>();


    static {
        // 静态初始化
        for (BusinessTypeEnum e: values()){
            CMAPS.put(e.getCode(), e);
            IMAPS.put(e.getInfo(), e);
        }
    }


    /**
     * 根据枚举编码查询对应枚举
     * 应用场景：数据库存储的枚举编码（手工，常规已经有自动转换机制了）查询对应枚举
     *
     * @return
     */
    public static BusinessTypeEnum getEnumByCode(String code) {
        return CMAPS.get(code);
    }


    /**
     * 根据枚举说明查询对应枚举
     * 应用场景：导入excel数据时说明信息转换成枚举
     *
     * @return
     */
    public static BusinessTypeEnum getEnumByInfo(String info) {
        return IMAPS.get(info);
    }

    /**
     * 根据枚举查询枚举代码
     * 应用场景：反射转换值时使用
     *
     * @return
     */
    public static String getCode(Object obj) {
        if (ObjectUtil.isNull(obj)){
            return "";
        }
        BusinessTypeEnum e = (BusinessTypeEnum) obj;
        return e.getCode();
    }

    /**
     * 根据枚举查询枚举说明
     * 应用场景：反射转换值时使用
     *
     * @return
     */
    public static String getInfo(Object obj) {
        if (ObjectUtil.isNull(obj)){
            return "";
        }
        BusinessTypeEnum e = (BusinessTypeEnum) obj;
        return e.getInfo();
    }
}
