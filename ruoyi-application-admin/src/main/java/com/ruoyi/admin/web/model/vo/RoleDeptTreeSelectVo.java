package com.ruoyi.admin.web.model.vo;

import cn.hutool.core.lang.tree.Tree;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 角色部门列表树视图对象
 * @author weibocy
 */
@Data
@Schema(description = "角色部门列表树视图对象")
public class RoleDeptTreeSelectVo implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 选中部门ID列表
     */
    @Schema(description = "选中部门ID列表")
    private List<Long> checkedKeys;

    /**
     * 下拉树结构列表
     */
    @Schema(description = "下拉树结构列表")
    private List<Tree<Long>> depts;

}
