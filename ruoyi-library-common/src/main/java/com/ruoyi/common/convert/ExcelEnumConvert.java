package com.ruoyi.common.convert;

import cn.hutool.core.annotation.AnnotationUtil;
import cn.hutool.core.convert.Convert;
import cn.hutool.core.util.ObjectUtil;
import com.alibaba.excel.converters.Converter;
import com.alibaba.excel.metadata.GlobalConfiguration;
import com.alibaba.excel.metadata.data.ReadCellData;
import com.alibaba.excel.metadata.data.WriteCellData;
import com.alibaba.excel.metadata.property.ExcelContentProperty;
import com.ruoyi.common.annotation.ExcelEnumFormat;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * 枚举格式化转换处理
 *
 * @author weibocy
 */
public class ExcelEnumConvert implements Converter<Object> {


    /**
     * 单元格转换成java对象
     * @param cellData 单元格信息
     * @param contentProperty 字段属性
     * @param globalConfiguration 全局属性
     * @return
     * @throws Exception
     */
    @Override
    public Object convertToJavaData(ReadCellData<?> cellData, ExcelContentProperty contentProperty, GlobalConfiguration globalConfiguration) throws Exception {

        // 获取注解
        ExcelEnumFormat anno = getAnnotation(contentProperty.getField());
        // 获取注解中的枚举信息
        Class<? extends Enum> eumClass = anno.enumClass();
        // 单元格内的枚举说明
        String info = cellData.getStringValue();
        // 根据枚举说明查到枚举 获取枚举类的方法名“getEnumByInfo”(根据枚举说明查询对应枚举) String.class是声明方法的入参类型
        Method getEnumByInfo = eumClass.getMethod("getEnumByInfo", String.class);
        Class<? extends Enum> value = (Class<? extends Enum>) getEnumByInfo.invoke(eumClass, info);
        return Convert.convert(contentProperty.getField().getType(), value);
    }

    /**
     * java对象转换成单元格信息
     * @param value 字段信息
     * @param contentProperty 字段属性
     * @param globalConfiguration 全局属性
     * @return
     * @throws Exception
     */
    @Override
    public WriteCellData<?> convertToExcelData(Object value, ExcelContentProperty contentProperty, GlobalConfiguration globalConfiguration) throws Exception {

        // 空对象直接转换为空字符串
        if (ObjectUtil.isNull(value)) {
            return new WriteCellData<>("");
        }
        // 获取注解
        ExcelEnumFormat anno = getAnnotation(contentProperty.getField());
        // 获取注解中的枚举信息
        Class<? extends Enum> eumClass = anno.enumClass();
        // 获取枚举类的方法名“getInfo”(获取说明信息) Object.class是声明方法的入参类型
        Method getInfo = eumClass.getMethod("getInfo", Object.class);
        // 反射执行方法，getInfo方法返回说明信息 因此得到结果  value是作为参数传入方法
        String info = Convert.toStr(getInfo.invoke(eumClass, value));
        // 写入单元格
        return new WriteCellData<>(info);
    }


    /**
     * 获取字段属性中的注解信息
     * @param field 字段属性
     * @return 注解信息
     */
    private ExcelEnumFormat getAnnotation(Field field) {
        return AnnotationUtil.getAnnotation(field, ExcelEnumFormat.class);
    }
}
