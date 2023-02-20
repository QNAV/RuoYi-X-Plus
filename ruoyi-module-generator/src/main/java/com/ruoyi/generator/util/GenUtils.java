package com.ruoyi.generator.util;

import com.ruoyi.common.constant.GenConstants;
import com.ruoyi.common.enums.CommonYesOrNoEnum;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.generator.config.GenConfig;
import com.ruoyi.generator.domain.GenTable;
import com.ruoyi.generator.domain.GenTableColumn;
import com.ruoyi.generator.enums.HtmlTypeEnum;
import com.ruoyi.generator.enums.JavaTypeEnum;
import com.ruoyi.generator.enums.QueryTypeEnum;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.RegExUtils;

import java.util.Arrays;

/**
 * 代码生成器 工具类
 *
 * @author ruoyi
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class GenUtils {

    /**
     * 初始化表信息
     */
    public static void initTable(GenTable genTable) {
        genTable.setClassName(convertClassName(genTable.getTableName()));
        genTable.setPackageName(GenConfig.getPackageName());
        genTable.setModuleName(getModuleName(GenConfig.getPackageName()));
        genTable.setBusinessName(getBusinessName(genTable.getTableName()));
        genTable.setFunctionName(replaceText(genTable.getTableComment()));
        genTable.setFunctionAuthor(GenConfig.getAuthor());
    }

    /**
     * 初始化列属性字段
     */
    public static void initColumnField(GenTableColumn column, GenTable table) {
        String dataType = getDbType(column.getColumnType());
        String columnName = column.getColumnName();
        column.setTableId(table.getTableId());
        column.setCreateBy(table.getCreateBy());
        // 设置java字段名
        column.setJavaField(StringUtils.toCamelCase(columnName));
        // 设置默认类型
        column.setJavaType(JavaTypeEnum.STRING);
        // 设置默认用等于
        column.setQueryType(QueryTypeEnum.EQ);

        if (arraysContains(GenConstants.COLUMNTYPE_STR, dataType) || arraysContains(GenConstants.COLUMNTYPE_TEXT, dataType)) {
            // 字符串长度超过500设置为文本域
            Integer columnLength = getColumnLength(column.getColumnType());
            HtmlTypeEnum htmlType = columnLength >= 500 || arraysContains(GenConstants.COLUMNTYPE_TEXT, dataType) ? HtmlTypeEnum.TEXTAREA : HtmlTypeEnum.INPUT;
            column.setHtmlType(htmlType);
        } else if (arraysContains(GenConstants.COLUMNTYPE_TIME, dataType)) {
            column.setJavaType(JavaTypeEnum.DATE);
            column.setHtmlType(HtmlTypeEnum.DATETIME);
        } else if (arraysContains(GenConstants.COLUMNTYPE_NUMBER, dataType)) {
            column.setHtmlType(HtmlTypeEnum.INPUT);

            // 如果是浮点型 统一用BigDecimal
            String[] str = StringUtils.split(StringUtils.substringBetween(column.getColumnType(), "(", ")"), StringUtils.SEPARATOR);
            if (str != null && str.length == 2 && Integer.parseInt(str[1]) > 0) {
                column.setJavaType(JavaTypeEnum.BIGDECIMAL);
            }
            // 如果是整形
            else if ((str != null && str.length == 1 && Integer.parseInt(str[0]) <= 10) || arraysContains(GenConstants.COLUMNTYPE_INTEGER, column.getColumnType())) {
                column.setJavaType(JavaTypeEnum.INTEGER);
            }
            // 长整形
            else {
                column.setJavaType(JavaTypeEnum.LONG);
            }
        }

        // 主键改成long
        if (column.isPk()){
            column.setJavaType(JavaTypeEnum.LONG);
        }

        // BO对象 碰到常规字段 和主键字段 不勾选 其他的都勾选
        if (!arraysContains(GenConstants.COLUMNNAME_NOT_ADD, columnName) && !column.isPk()) {
            column.setIsInsert(CommonYesOrNoEnum.YES);
        }
        // BO对象 碰到常规字段不勾选 其他的都勾选
        if (!arraysContains(GenConstants.COLUMNNAME_NOT_EDIT, columnName)) {
            column.setIsEdit(CommonYesOrNoEnum.YES);
        }
        // BO对象 碰到常规字段 必填项 不勾选
        if (arraysContains(GenConstants.COLUMNNAME_NOT_EDIT, columnName)) {
            column.setIsRequired(CommonYesOrNoEnum.NO);
        }
        // VO对象 碰到常规字段 不勾选 其他都勾选
        if (!arraysContains(GenConstants.COLUMNNAME_NOT_LIST, columnName)) {
            column.setIsList(CommonYesOrNoEnum.YES);
        }
        // VO必须对象 碰到常规字段 勾选
        if (arraysContains(GenConstants.COLUMNNAME_DEFAULT_VO_REQUIRED, columnName)) {
            column.setIsVoRequired(CommonYesOrNoEnum.YES);
        }
        // VO必须对象 碰到不勾选字段
        if (arraysContains(GenConstants.COLUMNNAME_NOT_VO_REQUIRED, columnName)) {
            column.setIsVoRequired(CommonYesOrNoEnum.NO);
        }
        // BO对象 默认查询勾选
        if (arraysContains(GenConstants.COLUMNNAME_DEFAULT_QUERY, columnName)) {
            column.setIsQuery(CommonYesOrNoEnum.YES);
        }

        // 查询字段类型
        if (StringUtils.endsWithIgnoreCase(columnName, "name")) {
            column.setQueryType(QueryTypeEnum.LIKE);
        }
        // 状态字段设置单选框
        if (StringUtils.endsWithIgnoreCase(columnName, "status")) {
            column.setHtmlType(HtmlTypeEnum.RADIO);
        }
        // 类型&性别字段设置下拉框
        else if (StringUtils.endsWithIgnoreCase(columnName, "type")
            || StringUtils.endsWithIgnoreCase(columnName, "sex")) {
            column.setHtmlType(HtmlTypeEnum.SELECT);
        }
        // 图片字段设置图片上传控件
        else if (StringUtils.endsWithIgnoreCase(columnName, "image")) {
            column.setHtmlType(HtmlTypeEnum.IMAGE);
        }
        // 文件字段设置文件上传控件
        else if (StringUtils.endsWithIgnoreCase(columnName, "file")) {
            column.setHtmlType(HtmlTypeEnum.UPLOAD);
        }
        // 内容字段设置富文本控件
        else if (StringUtils.endsWithIgnoreCase(columnName, "content")) {
            column.setHtmlType(HtmlTypeEnum.EDITOR);
        }
        // 内容字段设置时间控件
        else if (StringUtils.endsWithIgnoreCase(columnName, "time")) {
            column.setHtmlType(HtmlTypeEnum.DATETIME);
            // 时间格式 用范围查询方式
            column.setQueryType(QueryTypeEnum.BETWEEN);
        }
    }

    /**
     * 校验数组是否包含指定值
     *
     * @param arr         数组
     * @param targetValue 值
     * @return 是否包含
     */
    public static boolean arraysContains(String[] arr, String targetValue) {
        return Arrays.asList(arr).contains(targetValue);
    }

    /**
     * 获取模块名
     *
     * @param packageName 包名
     * @return 模块名
     */
    public static String getModuleName(String packageName) {
        int lastIndex = packageName.lastIndexOf(".");
        int nameLength = packageName.length();
        return StringUtils.substring(packageName, lastIndex + 1, nameLength);
    }

    /**
     * 获取业务名
     *
     * @param tableName 表名
     * @return 业务名
     */
    public static String getBusinessName(String tableName) {
        int firstIndex = tableName.indexOf("_");
        int nameLength = tableName.length();
        String businessName = StringUtils.substring(tableName, firstIndex + 1, nameLength);
        businessName = StringUtils.toCamelCase(businessName);
        return businessName;
    }

    /**
     * 表名转换成Java类名
     *
     * @param tableName 表名称
     * @return 类名
     */
    public static String convertClassName(String tableName) {
        boolean autoRemovePre = GenConfig.getAutoRemovePre();
        String tablePrefix = GenConfig.getTablePrefix();
        if (autoRemovePre && StringUtils.isNotEmpty(tablePrefix)) {
            String[] searchList = StringUtils.split(tablePrefix, StringUtils.SEPARATOR);
            tableName = replaceFirst(tableName, searchList);
        }
        return StringUtils.convertToCamelCase(tableName);
    }

    /**
     * 批量替换前缀
     *
     * @param replacementm 替换值
     * @param searchList   替换列表
     * @return
     */
    public static String replaceFirst(String replacementm, String[] searchList) {
        String text = replacementm;
        for (String searchString : searchList) {
            if (replacementm.startsWith(searchString)) {
                text = replacementm.replaceFirst(searchString, "");
                break;
            }
        }
        return text;
    }

    /**
     * 关键字替换
     *
     * @param text 需要被替换的名字
     * @return 替换后的名字
     */
    public static String replaceText(String text) {
        return RegExUtils.replaceAll(text, "(?:表|若依)", "");
    }

    /**
     * 获取数据库类型字段
     *
     * @param columnType 列类型
     * @return 截取后的列类型
     */
    public static String getDbType(String columnType) {
        if (StringUtils.indexOf(columnType, '(') > 0) {
            return StringUtils.substringBefore(columnType, "(");
        } else {
            return columnType;
        }
    }

    /**
     * 获取字段长度
     *
     * @param columnType 列类型
     * @return 截取后的列类型
     */
    public static Integer getColumnLength(String columnType) {
        if (StringUtils.indexOf(columnType, '(') > 0) {
            String length = StringUtils.substringBetween(columnType, "(", ")");
            return Integer.valueOf(length);
        } else {
            return 0;
        }
    }
}
