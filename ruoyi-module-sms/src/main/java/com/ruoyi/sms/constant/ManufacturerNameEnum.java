package com.ruoyi.sms.constant;

/**
 * @Author Administrator
 * @Date 2023/7/5 10:53
 */
public enum ManufacturerNameEnum {

    ALIYUN("aliyun","阿里云"),
    TENCENT("tencent","腾讯")
    ;

    private String code;

    private String name ;

    ManufacturerNameEnum(String code, String name) {
        this.code = code;
        this.name = name;
    }

    public static  ManufacturerNameEnum getInstance(String code){
        ManufacturerNameEnum[] values = ManufacturerNameEnum.values();
        for (ManufacturerNameEnum value : values) {
            if(value.code.equals(code)){
                return value;
            }
        }
        return null;
    }
}
