package com.ruoyi.generator.domain;

import com.baomidou.mybatisplus.annotation.*;
import com.ruoyi.common.constant.GenConstants;
import com.ruoyi.common.core.domain.BaseEntity;
import com.ruoyi.common.utils.StringUtils;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
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
 * @author weibocy
 */

@Data
@EqualsAndHashCode(callSuper = true)
@TableName("gen_table")
@ApiModel(value = "GenTable", description = "代码生成实体对象")
public class GenTable extends BaseEntity {

    /**
     * 编号
     */
    @TableId(value = "table_id")
    @ApiModelProperty("编号")
    private Long tableId;

    /**
     * 表名称
     */
    @NotBlank(message = "表名称不能为空")
    @ApiModelProperty(value = "表名称", required = true)
    private String tableName;

    /**
     * 表描述
     */
    @NotBlank(message = "表描述不能为空")
    @ApiModelProperty(value = "表描述", required = true)
    private String tableComment;

    /**
     * 关联父表的表名
     */
    @ApiModelProperty("关联父表的表名")
    private String subTableName;

    /**
     * 本表关联父表的外键名
     */
    @ApiModelProperty("本表关联父表的外键名")
    private String subTableFkName;

    /**
     * 实体类名称(首字母大写)
     */
    @ApiModelProperty(value = "表名称", required = true)
    @NotBlank(message = "实体类名称(首字母大写)")
    private String className;

    /**
     * 使用的模板（crud单表操作 tree树表操作 sub主子表操作）
     */
    @ApiModelProperty("使用的模板（crud单表操作 tree树表操作 sub主子表操作）")
    private String tplCategory;

    /**
     * 生成包路径
     */
    @ApiModelProperty(value = "生成包路径", required = true)
    @NotBlank(message = "生成包路径不能为空")
    private String packageName;

    /**
     * 生成模块名
     */
    @ApiModelProperty(value = "生成模块名", required = true)
    @NotBlank(message = "生成模块名不能为空")
    private String moduleName;

    /**
     * 生成业务名
     */
    @ApiModelProperty(value = "生成业务名", required = true)
    @NotBlank(message = "生成业务名不能为空")
    private String businessName;

    /**
     * 生成功能名
     */
    @ApiModelProperty(value = "生成功能名", required = true)
    @NotBlank(message = "生成功能名不能为空")
    private String functionName;

    /**
     * 生成作者
     */
    @ApiModelProperty(value = "生成作者", required = true)
    @NotBlank(message = "作者不能为空")
    private String functionAuthor;

    /**
     * 生成代码方式（0zip压缩包 1自定义路径）
     */
    @ApiModelProperty("生成代码方式（0zip压缩包 1自定义路径）")
    private String genType;

    /**
     * 生成路径（不填默认项目路径）
     */
    @ApiModelProperty("生成路径（不填默认项目路径）")
    @TableField(updateStrategy = FieldStrategy.NOT_EMPTY)
    private String genPath;

    /**
     * 主键信息
     */
    @ApiModelProperty("主键信息")
    @TableField(exist = false)
    private GenTableColumn pkColumn;

    /**
     * 子表信息
     */
    @ApiModelProperty("子表信息")
    @TableField(exist = false)
    private GenTable subTable;

    /**
     * 表列信息
     */
    @ApiModelProperty(value = "表列信息", required = true)
    @Valid
    @TableField(exist = false)
    @NotNull
    private List<GenTableColumn> columns;

    /**
     * 其它生成选项
     */
    @ApiModelProperty("其它生成选项")
    private String options;

    /**
     * 备注
     */
    @ApiModelProperty("备注")
    private String remark;

    /**
     * 树编码字段
     */
    @ApiModelProperty("树编码字段")
    @TableField(exist = false)
    private String treeCode;

    /**
     * 树父编码字段
     */
    @ApiModelProperty("树父编码字段")
    @TableField(exist = false)
    private String treeParentCode;

    /**
     * 树名称字段
     */
    @ApiModelProperty("树名称字段")
    @TableField(exist = false)
    private String treeName;

    /**
     * 菜单id列表
     */
    @TableField(exist = false)
    @ApiModelProperty("菜单id列表")
    private List<Long> menuIds;

    /**
     * 上级菜单ID字段
     */
    @ApiModelProperty("上级菜单ID字段")
    @TableField(exist = false)
    private String parentMenuId;

    /**
     * 上级菜单名称字段
     */
    @ApiModelProperty("上级菜单名称字段")
    @TableField(exist = false)
    private String parentMenuName;

    /**
     * 传入参数（暂时预留）
     */
    @ApiModelProperty("传入参数（暂时预留）")
    @TableField(exist = false)
    private Map<String, String> params;

    public boolean isSub() {
        return isSub(this.tplCategory);
    }

    public static boolean isSub(String tplCategory) {
        return tplCategory != null && StringUtils.equals(GenConstants.TPL_SUB, tplCategory);
    }

    public boolean isTree() {
        return isTree(this.tplCategory);
    }

    public static boolean isTree(String tplCategory) {
        return tplCategory != null && StringUtils.equals(GenConstants.TPL_TREE, tplCategory);
    }

    public boolean isCrud() {
        return isCrud(this.tplCategory);
    }

    public static boolean isCrud(String tplCategory) {
        return tplCategory != null && StringUtils.equals(GenConstants.TPL_CRUD, tplCategory);
    }

    public boolean isSuperColumn(String javaField) {
        return isSuperColumn(this.tplCategory, javaField);
    }

    public static boolean isSuperColumn(String tplCategory, String javaField) {
        if (isTree(tplCategory)) {
            return StringUtils.equalsAnyIgnoreCase(javaField,
                ArrayUtils.addAll(GenConstants.TREE_ENTITY, GenConstants.BASE_ENTITY));
        }
        return StringUtils.equalsAnyIgnoreCase(javaField, GenConstants.BASE_ENTITY);
    }

}
