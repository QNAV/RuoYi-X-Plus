package com.ruoyi.common.annotation;

import com.ruoyi.common.enums.BusinessTypeEnum;
import com.ruoyi.common.enums.UserTypeEnum;

import java.lang.annotation.*;

/**
 * 自定义后台操作日志记录注解
 *
 * @author weibocy
 */
@Target({ElementType.PARAMETER, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface BizLog {
    /**
     * 模块
     */
    String title() default "";

    /**
     * 功能
     */
    BusinessTypeEnum businessType() default BusinessTypeEnum.OTHER;

    /**
     * 操作人类别
     */
    UserTypeEnum operatorType() default UserTypeEnum.PC;

    /**
     * 是否保存请求的参数
     */
    boolean isSaveRequestData() default true;

    /**
     * 是否保存响应的参数
     */
    boolean isSaveResponseData() default true;
}
