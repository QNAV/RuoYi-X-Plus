package com.ruoyi.common.annotation;

import java.lang.annotation.*;

/**
 * 枚举格式化
 *
 * @author weibocy
 */
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
public @interface ExcelEnumFormat {

    String[] value() default {""};

    Class<? extends Enum> enumClass();
}
