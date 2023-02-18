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
 * 查询方式枚举
 * @author weibocy
 */
@AllArgsConstructor
@Getter
public enum QueryTypeEnum {


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


    /**
     * 将枚举转换成list格式
     * 这样前台遍历的时候比较容易，列如 下拉框 后台调用toList方法
     *
     * @return
     */
    public static List getList() {
        List list = Lists.newArrayList();
        for (QueryTypeEnum statusEnum : QueryTypeEnum.values()) {
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
        for (QueryTypeEnum statusEnum : QueryTypeEnum.values()) {
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
