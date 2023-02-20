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
 * 生成代码方式枚举
 * @author weibocy
 */
@AllArgsConstructor
@Getter
public enum GenTypeEnum  implements BaseEnum {


    /**
     * zip压缩包
     */
    ZIP("ZIP", "zip压缩包"),

    /**
     * 自定义路径
     */
    CUSTOM("CUSTOM", "自定义路径");


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
    public static Map<String, GenTypeEnum> CMAPS= new HashMap<>();
    // info对应的枚举map
    public static Map<String, GenTypeEnum> IMAPS= new HashMap<>();


    static {
        // 静态初始化
        for (GenTypeEnum e: values()){
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
    public static GenTypeEnum getEnumByCode(String code) {
        return CMAPS.get(code);
    }


    /**
     * 根据枚举说明查询对应枚举
     * 应用场景：导入excel数据时说明信息转换成枚举
     *
     * @return
     */
    public static GenTypeEnum getEnumByInfo(String info) {
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
        GenTypeEnum e = (GenTypeEnum) obj;
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
        GenTypeEnum e = (GenTypeEnum) obj;
        return e.getInfo();
    }

}
