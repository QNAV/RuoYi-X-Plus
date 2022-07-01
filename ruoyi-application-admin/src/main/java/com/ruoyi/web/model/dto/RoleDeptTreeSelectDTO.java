package com.ruoyi.web.model.dto;

import cn.hutool.core.lang.tree.Tree;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * 角色部门列表树返回对象
 * @author weibocy
 */
@Data
@ApiModel(value = "RoleDeptTreeSelectDTO", description = "角色部门列表树返回对象")
public class RoleDeptTreeSelectDTO {

    /**
     * 选中部门ID列表
     */
    @ApiModelProperty("选中部门ID列表")
    private List<Long> checkedKeys;

    /**
     * 下拉树结构列表
     */
    @ApiModelProperty("下拉树结构列表")
    private List<Tree<Long>> depts;

}
