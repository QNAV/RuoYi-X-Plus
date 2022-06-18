package com.ruoyi.web.model.dto;

import cn.hutool.core.lang.tree.Tree;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * 角色菜单列表树返回对象
 * @author weibocy
 */
@Data
@ApiModel("角色菜单列表树返回对象")
public class RoleMenuTreeSelectDTO {

    /**
     * 菜单树信息keys
     */
    @ApiModelProperty("菜单树信息keys")
    private List<Long> checkedKeys;

    /**
     * 前端所需要下拉树结构
     */
    @ApiModelProperty("前端所需要下拉树结构")
    private List<Tree<Long>> menus;

}
