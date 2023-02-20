package com.ruoyi.generator.enums;

import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.annotation.EnumValue;
import com.fasterxml.jackson.annotation.JsonValue;
import com.ruoyi.common.enums.BaseEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

/**
 * 查询方式枚举
 * @author weibocy
 */
@AllArgsConstructor
@Getter
public enum QueryTypeEnum implements BaseEnum {


    /**
     * 等于
     */
    EQ("EQ", "等于"),

    /**
     * 不等于
     */
    NE("NE", "不等于"),

    /**
     * 大于
     */
    GT("GT", "大于"),

    /**
     * 大于等于
     */
    GE("GE", "大于等于"),

    /**
     * 小于
     */
    LT("LT", "小于"),

    /**
     * 小于等于
     */
    LE("LE", "小于等于"),

    /**
     * 模糊
     */
    LIKE("LIKE", "模糊"),

    /**
     * 范围
     */
    BETWEEN("BETWEEN", "范围");


    /**
     * 枚举代码
     */
    // 标记响应mbp值
    @EnumValue
    // 标记响应json值
    @JsonValue
    private final String code;

    /**
     * 枚举信息
     */
    private final String info;

    // code对应的枚举map
    public static Map<String, QueryTypeEnum> CMAPS= new HashMap<>();
    // info对应的枚举map
    public static Map<String, QueryTypeEnum> IMAPS= new HashMap<>();


    static {
        // 静态初始化
        for (QueryTypeEnum e: values()){
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
    public static QueryTypeEnum getEnumByCode(String code) {
        return CMAPS.get(code);
    }


    /**
     * 根据枚举说明查询对应枚举
     * 应用场景：导入excel数据时说明信息转换成枚举
     *
     * @return
     */
    public static QueryTypeEnum getEnumByInfo(String info) {
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
        QueryTypeEnum e = (QueryTypeEnum) obj;
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
        QueryTypeEnum e = (QueryTypeEnum) obj;
        return e.getInfo();
    }

}
