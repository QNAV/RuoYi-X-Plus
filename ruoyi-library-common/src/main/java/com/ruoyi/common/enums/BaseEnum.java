package com.ruoyi.common.enums;

import cn.hutool.core.util.ObjectUtil;

/**
 * 通用枚举接口约束
 * 用于框架内统一枚举数据格式
 */
public interface BaseEnum {

    /**
     * 约束枚举数据格式 code
     * @return
     */
    String getCode();

    /**
     * 约束枚举数据格式 info
     * @return
     */
    String getInfo();

}
