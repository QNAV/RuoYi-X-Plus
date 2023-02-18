package com.ruoyi.generator.domain;


import com.baomidou.mybatisplus.annotation.FieldStrategy;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.ruoyi.common.core.domain.BaseEntity;
import com.ruoyi.common.enums.CommonYesOrNo;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.generator.enums.HtmlTypeEnum;
import com.ruoyi.generator.enums.JavaTypeEnum;
import com.ruoyi.generator.enums.QueryTypeEnum;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.apache.ibatis.type.JdbcType;

import javax.validation.constraints.NotBlank;

/**
 * 代码生成业务字段表 gen_table_column
 * ??? 暂时还不知道原作者设置updateStrategy的用意
 * ??? 我这边编辑后发现is_pk、is_increment 被写空
 * ??? 难道是原来vue版本的前端是对象参数全量传入的关系
 * @author ruoyi
 * @author Lion Li
 * @author weibocy
 */

@Data
@EqualsAndHashCode(callSuper = true)
@TableName("gen_table_column")
@Schema(description = "代码生成业务字段实体对象")
public class GenTableColumn extends BaseEntity {

    /**
     * 编号
     */
    @TableId(value = "column_id")
    @Schema(description = "编号")
    private Long columnId;

    /**
     * 归属表编号
     */
    @Schema(description = "归属表编号")
    private Long tableId;

    /**
     * 列名称
     */
    @Schema(description = "列名称")
    private String columnName;

    /**
     * 列描述
     */
    @Schema(description = "列描述")
    @TableField(updateStrategy = FieldStrategy.IGNORED, jdbcType = JdbcType.VARCHAR)
    private String columnComment;

    /**
     * 列类型
     */
    @Schema(description = "列类型")
    private String columnType;

    /**
     * 列长度限制（仅限字符串类型）
     */
    @Schema(description = "列长度限制（仅限字符串类型）")
    private Integer columnMaxLength;

    /**
     * JAVA类型（Long=长整型 Integer=整型 String=字符串 Date=日期 Double=浮点数 Boolean=布尔型 BigDecimal=金额）
     */
    @Schema(description = "JAVA类型（Long=长整型 Integer=整型 String=字符串 Date=日期 Double=浮点数 Boolean=布尔型 BigDecimal=金额）")
    private JavaTypeEnum javaType;

    /**
     * JAVA字段名
     */
    @Schema(description = "JAVA字段名")
    @NotBlank(message = "Java属性不能为空")
    private String javaField;

    /**
     * 是否主键（YES=是 NO=否）
     */
    @Schema(description = "是否主键（YES=是 NO=否）")
    @TableField(updateStrategy = FieldStrategy.IGNORED, jdbcType = JdbcType.VARCHAR)
    private CommonYesOrNo isPk;

    /**
     * 是否自增（YES=是 NO=否）
     */
    @Schema(description = "是否自增（YES=是 NO=否）")
    @TableField(updateStrategy = FieldStrategy.IGNORED, jdbcType = JdbcType.VARCHAR)
    private CommonYesOrNo isIncrement;

    /**
     * 是否必填（YES=是 NO=否）
     */
    @Schema(description = "是否必填（YES=是 NO=否）")
    @TableField(updateStrategy = FieldStrategy.IGNORED, jdbcType = JdbcType.VARCHAR)
    private CommonYesOrNo isRequired;

    /**
     * 是否为插入字段（YES=是 NO=否）
     */
    @Schema(description = "是否为插入字段（YES=是 NO=否）")
    @TableField(updateStrategy = FieldStrategy.IGNORED, jdbcType = JdbcType.VARCHAR)
    private CommonYesOrNo isInsert;

    /**
     * 是否编辑字段（YES=是 NO=否）
     */
    @Schema(description = "是否编辑字段（YES=是 NO=否）")
    @TableField(updateStrategy = FieldStrategy.IGNORED, jdbcType = JdbcType.VARCHAR)
    private CommonYesOrNo isEdit;

    /**
     * 是否列表字段（YES=是 NO=否）
     */
    @Schema(description = "是否列表字段（YES=是 NO=否）")
    @TableField(updateStrategy = FieldStrategy.IGNORED, jdbcType = JdbcType.VARCHAR)
    private CommonYesOrNo isList;

    /**
     * 是否VO必须返回（YES=是 NO=否）
     */
    @Schema(description = "是否VO必须返回（YES=是 NO=否）")
    @TableField(updateStrategy = FieldStrategy.IGNORED, jdbcType = JdbcType.VARCHAR)
    private CommonYesOrNo isVoRequired;

    /**
     * 是否查询字段（YES=是 NO=否）
     */
    @Schema(description = "是否查询字段（YES=是 NO=否）")
    @TableField(updateStrategy = FieldStrategy.IGNORED, jdbcType = JdbcType.VARCHAR)
    private CommonYesOrNo isQuery;

    /**
     * 查询方式（EQ=等于 NE=不等于 GT=大于 GE=大于等于 LT=小于 LE=小于等于 LIKE=模糊 BETWEEN=范围）
     */
    @Schema(description = "查询方式（EQ=等于 NE=不等于 GT=大于 GE=大于等于 LT=小于 LE=小于等于 LIKE=模糊 BETWEEN=范围）")
    private QueryTypeEnum queryType;

    /**
     * 显示类型（INPUT=文本框 TEXTAREA=文本域 SELECT=下拉框 CHECKBOX=复选框 RADIO=单选框 DATETIME=日期控件 IMAGE=图片上传控件 UPLOAD=文件上传控件 EDITOR=富文本控件）
     */
    @Schema(description = "显示类型（INPUT=文本框 TEXTAREA=文本域 SELECT=下拉框 CHECKBOX=复选框 RADIO=单选框 DATETIME=日期控件 IMAGE=图片上传控件 UPLOAD=文件上传控件 EDITOR=富文本控件）")
    private HtmlTypeEnum htmlType;

    /**
     * 字典类型
     */
    @Schema(description = "字典类型")
    private String dictType;

    /**
     * 排序
     */
    @Schema(description = "排序")
    private Integer sort;

    public String getCapJavaField() {
        return StringUtils.capitalize(javaField);
    }

    public boolean isPk() {
        return isPk(this.isPk);
    }

    public boolean isPk(CommonYesOrNo isPk) {
        return isPk != null && isPk.equals(CommonYesOrNo.YES);
    }

    public boolean isIncrement() {
        return isIncrement(this.isIncrement);
    }

    public boolean isIncrement(CommonYesOrNo isIncrement) {
        return isIncrement != null && isIncrement.equals(CommonYesOrNo.YES);
    }

    public boolean isRequired() {
        return isRequired(this.isRequired);
    }

    public boolean isRequired(CommonYesOrNo isRequired) {
        return isRequired != null && isRequired.equals(CommonYesOrNo.YES);
    }

    public boolean isInsert() {
        return isInsert(this.isInsert);
    }

    public boolean isInsert(CommonYesOrNo isInsert) {
        return isInsert != null && isInsert.equals(CommonYesOrNo.YES);
    }

    public boolean isEdit() {
        return isInsert(this.isEdit);
    }

    public boolean isEdit(CommonYesOrNo isEdit) {
        return isEdit != null && isEdit.equals(CommonYesOrNo.YES);
    }

    public boolean isList() {
        return isList(this.isList);
    }


    public boolean isList(CommonYesOrNo isList) {
        return isList != null && isList.equals(CommonYesOrNo.YES);
    }

    public boolean isVoRequired(CommonYesOrNo isVoRequired) {
        return isVoRequired != null && isVoRequired.equals(CommonYesOrNo.YES);
    }

    public boolean isVoRequired() {
        return isVoRequired(this.isVoRequired);
    }

    public boolean isQuery() {
        return isQuery(this.isQuery);
    }

    public boolean isQuery(CommonYesOrNo isQuery) {
        return isQuery != null && isQuery.equals(CommonYesOrNo.YES);
    }

    public boolean isSuperColumn() {
        return isSuperColumn(this.javaField);
    }

    public static boolean isSuperColumn(String javaField) {
        return StringUtils.equalsAnyIgnoreCase(javaField,
            // BaseEntity
            "createBy", "createTime", "updateBy", "updateTime",
            // TreeEntity
            "parentName", "parentId");
    }

    public boolean isUsableColumn() {
        return isUsableColumn(javaField);
    }

    public static boolean isUsableColumn(String javaField) {
        // isSuperColumn()中的名单用于避免生成多余Domain属性，若某些属性在生成页面时需要用到不能忽略，则放在此处白名单
        return StringUtils.equalsAnyIgnoreCase(javaField, "parentId", "orderNum", "remark");
    }

    public String readConverterExp() {
        String remarks = StringUtils.substringBetween(this.columnComment, "（", "）");
        StringBuffer sb = new StringBuffer();
        if (StringUtils.isNotEmpty(remarks)) {
            for (String value : remarks.split(" ")) {
                if (StringUtils.isNotEmpty(value)) {
                    Object startStr = value.subSequence(0, 1);
                    String endStr = value.substring(1);
                    sb.append("").append(startStr).append("=").append(endStr).append(StringUtils.SEPARATOR);
                }
            }
            return sb.deleteCharAt(sb.length() - 1).toString();
        } else {
            return this.columnComment;
        }
    }
}
