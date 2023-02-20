package com.ruoyi.common.enums;

import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.annotation.EnumValue;
import com.fasterxml.jackson.annotation.JsonValue;
import com.ruoyi.common.utils.StringUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * 设备类型
 * 针对多套 用户体系
 *
 * @author weibocy
 */
public enum UserTypeEnum implements BaseEnum {

    /**
     * 电脑端
     */
    PC("PC", "电脑端"),

    /**
     * 安卓app端
     */
    ANDROID("ANDROID", "安卓app端"),

    /**
     * 苹果app端
     */
    IOS("IOS", "苹果app端"),

    /**
     * 微信小程序端
     */
    WXAPP("WXAPP", "微信小程序端"),

    /**
     * 微信公众号端
     */
    WXMP("WXMP", "微信公众号端"),

    /**
     * 支付宝小程序端
     */
    ALIPAYAPP("ALIPAYAPP", "支付宝小程序端");

    // 标记响应mbp值
    @EnumValue
    // 标记响应json值
    @JsonValue
    private final String code;
    private final String info;

    UserTypeEnum(String code, String info) {
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
    public static Map<String, UserTypeEnum> CMAPS= new HashMap<>();
    // info对应的枚举map
    public static Map<String, UserTypeEnum> IMAPS= new HashMap<>();


    static {
        // 静态初始化
        for (UserTypeEnum e: values()){
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
    public static UserTypeEnum getEnumByCode(String code) {
        return CMAPS.get(code);
    }


    /**
     * 根据枚举说明查询对应枚举
     * 应用场景：导入excel数据时说明信息转换成枚举
     *
     * @return
     */
    public static UserTypeEnum getEnumByInfo(String info) {
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
        UserTypeEnum e = (UserTypeEnum) obj;
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
        UserTypeEnum e = (UserTypeEnum) obj;
        return e.getInfo();
    }


    public static UserTypeEnum getUserType(String loginId) {
        for (UserTypeEnum value : values()) {
            if (StringUtils.contains(loginId, value.getCode())) {
                return value;
            }
        }
        throw new RuntimeException("'UserType' not found By " + loginId);
    }

}
