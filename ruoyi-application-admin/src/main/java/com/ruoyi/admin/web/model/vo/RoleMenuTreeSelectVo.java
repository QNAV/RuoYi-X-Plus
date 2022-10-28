package com.ruoyi.admin.web.model.vo;

import cn.hutool.core.lang.tree.Tree;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 角色菜单列表树返回对象
 * @author weibocy
 */
@Data
@ApiModel(value = "RoleMenuTreeSelectVo", description = "角色菜单列表树返回对象")
public class RoleMenuTreeSelectVo implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 菜单树信息keys
     */
    @ApiModelProperty(value = "菜单树信息keys", required = true)
    private List<Long> checkedKeys;

    /**
     * 前端所需要下拉树结构
     */
    @ApiModelProperty(value = "前端所需要下拉树结构", required = true)
    private List<Tree<Long>> menus;

}
