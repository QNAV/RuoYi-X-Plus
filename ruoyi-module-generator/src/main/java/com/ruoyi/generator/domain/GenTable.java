package com.ruoyi.generator.domain;

import com.baomidou.mybatisplus.annotation.*;
import com.ruoyi.common.constant.GenConstants;
import com.ruoyi.common.core.domain.BaseEntity;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.generator.enums.GenTypeEnum;
import com.ruoyi.generator.enums.TplCategoryEnum;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.apache.commons.lang3.ArrayUtils;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Map;

/**
 * 业务表 gen_table
 *
 * @author ruoyi
 * @author Lion Li
 */

@Data
@EqualsAndHashCode(callSuper = true)
@TableName("gen_table")
@Schema(description = "代码生成实体对象")
public class GenTable extends BaseEntity {

    /**
     * 编号
     */
    @TableId(value = "table_id")
    @Schema(description = "编号")
    private Long tableId;

    /**
     * 表名称
     */
    @NotBlank(message = "表名称不能为空")
    @Schema(description = "表名称", required = true)
    private String tableName;

    /**
     * 表描述
     */
    @NotBlank(message = "表描述不能为空")
    @Schema(description = "表描述", required = true)
    private String tableComment;

    /**
     * 关联父表的表名
     */
    @Schema(description = "关联父表的表名")
    private String subTableName;

    /**
     * 本表关联父表的外键名
     */
    @Schema(description = "本表关联父表的外键名")
    private String subTableFkName;

    /**
     * 实体类名称(首字母大写)
     */
    @Schema(description = "表名称", required = true)
    @NotBlank(message = "实体类名称(首字母大写)")
    private String className;

    /**
     * 使用的模板（CRUD=单表操作 TREE=树表操作 SUB=主子表操作）
     */
    @Schema(description = "使用的模板（CRUD=单表操作 TREE=树表操作 SUB=主子表操作）")
    private TplCategoryEnum tplCategory;

    /**
     * 生成包路径
     */
    @Schema(description = "生成包路径", required = true)
    @NotBlank(message = "生成包路径不能为空")
    private String packageName;

    /**
     * 生成模块名
     */
    @Schema(description = "生成模块名", required = true)
    @NotBlank(message = "生成模块名不能为空")
    private String moduleName;

    /**
     * 生成业务名
     */
    @Schema(description = "生成业务名", required = true)
    @NotBlank(message = "生成业务名不能为空")
    private String businessName;

    /**
     * 生成功能名
     */
    @Schema(description = "生成功能名", required = true)
    @NotBlank(message = "生成功能名不能为空")
    private String functionName;

    /**
     * 生成作者
     */
    @Schema(description = "生成作者", required = true)
    @NotBlank(message = "作者不能为空")
    private String functionAuthor;

    /**
     * 生成代码方式（ZIP=zip压缩包 CUSTOM=自定义路径）
     */
    @Schema(description = "生成代码方式（ZIP=zip压缩包 CUSTOM=自定义路径）")
    private GenTypeEnum genType;

    /**
     * 生成路径（不填默认项目路径）
     */
    @Schema(description = "生成路径（不填默认项目路径）")
    @TableField(updateStrategy = FieldStrategy.NOT_EMPTY)
    private String genPath;

    /**
     * 主键信息
     */
    @Schema(description = "主键信息")
    @TableField(exist = false)
    private GenTableColumn pkColumn;

    /**
     * 子表信息
     */
    @Schema(description = "子表信息")
    @TableField(exist = false)
    private GenTable subTable;

    /**
     * 表列信息
     */
    @Schema(description = "表列信息", required = true)
    @Valid
    @TableField(exist = false)
    @NotNull
    private List<GenTableColumn> columns;

    /**
     * 其它生成选项
     */
    @Schema(description = "其它生成选项")
    private String options;

    /**
     * 备注
     */
    @Schema(description = "备注")
    private String remark;

    /**
     * 树编码字段
     */
    @Schema(description = "树编码字段")
    @TableField(exist = false)
    private String treeCode;

    /**
     * 树父编码字段
     */
    @Schema(description = "树父编码字段")
    @TableField(exist = false)
    private String treeParentCode;

    /**
     * 树名称字段
     */
    @Schema(description = "树名称字段")
    @TableField(exist = false)
    private String treeName;

    /**
     * 菜单id列表
     */
    @TableField(exist = false)
    @Schema(description = "菜单id列表")
    private List<Long> menuIds;

    /**
     * 上级菜单ID字段
     */
    @Schema(description = "上级菜单ID字段")
    @TableField(exist = false)
    private String parentMenuId;

    /**
     * 上级菜单名称字段
     */
    @Schema(description = "上级菜单名称字段")
    @TableField(exist = false)
    private String parentMenuName;

    /**
     * 传入参数（暂时预留）
     */
    @Schema(description = "传入参数（暂时预留）")
    @TableField(exist = false)
    private Map<String, String> params;

    public boolean isSub() {
        return isSub(this.tplCategory);
    }

    public static boolean isSub(TplCategoryEnum tplCategory) {
        return tplCategory != null && tplCategory.equals(TplCategoryEnum.SUB);
    }

    public boolean isTree() {
        return isTree(this.tplCategory);
    }

    public static boolean isTree(TplCategoryEnum tplCategory) {
        return tplCategory != null && tplCategory.equals(TplCategoryEnum.TREE);
    }

    public boolean isCrud() {
        return isCrud(this.tplCategory);
    }

    public static boolean isCrud(TplCategoryEnum tplCategory) {
        return tplCategory != null && tplCategory.equals(TplCategoryEnum.CURD);
    }

    public boolean isSuperColumn(String javaField) {
        return isSuperColumn(this.tplCategory, javaField);
    }

    public static boolean isSuperColumn(TplCategoryEnum tplCategory, String javaField) {
        if (isTree(tplCategory)) {
            return StringUtils.equalsAnyIgnoreCase(javaField,
                ArrayUtils.addAll(GenConstants.TREE_ENTITY, GenConstants.BASE_ENTITY));
        }
        return StringUtils.equalsAnyIgnoreCase(javaField, GenConstants.BASE_ENTITY);
    }

}
