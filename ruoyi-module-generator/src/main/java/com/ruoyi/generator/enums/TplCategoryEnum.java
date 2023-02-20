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
 * 模板类型枚举
 * @author weibocy
 */
@AllArgsConstructor
@Getter
public enum TplCategoryEnum implements BaseEnum {


    /**
     * 单表操作
     */
    CURD("CRUD", "单表操作"),

    /**
     * 树表操作
     */
    TREE("TREE", "树表操作"),

    /**
     * 主子表操作
     */
    SUB("SUB", "主子表操作");


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
    public static Map<String, TplCategoryEnum> CMAPS= new HashMap<>();
    // info对应的枚举map
    public static Map<String, TplCategoryEnum> IMAPS= new HashMap<>();


    static {
        // 静态初始化
        for (TplCategoryEnum e: values()){
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
    public static TplCategoryEnum getEnumByCode(String code) {
        return CMAPS.get(code);
    }


    /**
     * 根据枚举说明查询对应枚举
     * 应用场景：导入excel数据时说明信息转换成枚举
     *
     * @return
     */
    public static TplCategoryEnum getEnumByInfo(String info) {
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
        TplCategoryEnum e = (TplCategoryEnum) obj;
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
        TplCategoryEnum e = (TplCategoryEnum) obj;
        return e.getInfo();
    }



}
