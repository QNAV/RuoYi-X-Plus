package com.ruoyi.generator.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.fasterxml.jackson.annotation.JsonValue;
import com.ruoyi.common.utils.StringUtils;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.apache.commons.compress.utils.Lists;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 显示类型枚举
 * @author weibocy
 */
@AllArgsConstructor
@Getter
public enum HtmlTypeEnum {


    /**
     * 文本框
     */
    INPUT("INPUT", "文本框"),

    /**
     * 文本域
     */
    TEXTAREA("TEXTAREA", "文本域"),

    /**
     * 下拉框
     */
    SELECT("SELECT", "下拉框"),

    /**
     * 复选框
     */
    CHECKBOX("CHECKBOX", "复选框"),

    /**
     * 单选框
     */
    RADIO("RADIO", "单选框"),

    /**
     * 日期控件
     */
    DATETIME("DATETIME", "日期控件"),

    /**
     * 图片上传控件
     */
    IMAGE("IMAGE", "图片上传控件"),

    /**
     * 文件上传控件
     */
    UPLOAD("UPLOAD", "文件上传控件"),

    /**
     * 富文本控件
     */
    EDITOR("EDITOR", "富文本控件");


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


    /**
     * 将枚举转换成list格式
     * 这样前台遍历的时候比较容易，列如 下拉框 后台调用toList方法
     *
     * @return
     */
    public static List getList() {
        List list = Lists.newArrayList();
        for (HtmlTypeEnum statusEnum : HtmlTypeEnum.values()) {
            Map<String, Object> map = new HashMap<>();
            map.put("code", statusEnum.getCode());
            map.put("info", statusEnum.getInfo());
            list.add(map);
        }
        return list;
    }

    /**
     * 根据枚举代码转换信息说明
     * @param code
     * @return
     */
    public static String getInfoByCode(String code){
        String info = "";
        for (HtmlTypeEnum statusEnum : HtmlTypeEnum.values()) {
            if (statusEnum.getCode().equals(code)){
                info = statusEnum.getInfo();
                break;
            }
        }
        if (StringUtils.isNotBlank(info)){
            return info;
        }else { // 找不到则返回 code
            return String.valueOf(code);
        }
    }



}
