package com.ruoyi.admin.web.controller.system;

import cn.dev33.satoken.annotation.SaCheckPermission;
import cn.hutool.core.lang.tree.Tree;
import com.ruoyi.admin.controller.AdminBaseController;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.constant.UserConstants;
import com.ruoyi.common.core.domain.R;
import com.ruoyi.common.core.domain.entity.SysMenu;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.BeanCopyUtils;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.system.domain.bo.SysMenuAddBo;
import com.ruoyi.system.domain.bo.SysMenuEditBo;
import com.ruoyi.system.domain.bo.SysMenuQueryBo;
import com.ruoyi.common.core.domain.vo.SysMenuVo;
import com.ruoyi.system.service.ISysMenuService;
import com.ruoyi.admin.web.model.vo.RoleMenuTreeSelectVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 菜单信息
 *
 * @author ruoyi
 * @author Lion Li
 */
@Validated
@Api(value = "菜单信息管理", tags = {"SysMenuService"})
@RequiredArgsConstructor
@RestController
@RequestMapping("/system/menu")
public class SysMenuController extends AdminBaseController {

    private final ISysMenuService menuService;

    /**
     * 获取菜单列表
     */
    @ApiOperation(value = "获取菜单列表", nickname = "SysMenuPostList")
    @SaCheckPermission("system:menu:list")
    @PostMapping("/list")
    public R<List<SysMenuVo>> list(@RequestBody(required = false) SysMenuQueryBo menuQuery) {
        List<SysMenuVo> menus = menuService.selectMenuList(menuQuery, getUserId());
        return R.ok(menus);
    }

    /**
     * 根据菜单编号获取详细信息
     */
    @ApiOperation(value = "根据菜单编号获取详细信息", nickname = "SysMenuGetInfo")
    @SaCheckPermission("system:menu:query")
    @GetMapping(value = "/info")
    public R<SysMenuVo> info(@ApiParam(value = "菜单ID", required = true) @RequestParam Long menuId) {
        return R.ok(menuService.selectMenuById(menuId));
    }

    /**
     * 获取菜单下拉树列表
     */
    @ApiOperation(value = "获取菜单下拉树列表", nickname = "SysMenuPostTreeSelect")
    @PostMapping("/treeSelect")
    public R<List<Tree<Long>>> treeSelect(@RequestBody(required = false) SysMenuQueryBo menuQuery) {
        List<SysMenuVo> menus = menuService.selectMenuList(menuQuery, getUserId());
        return R.ok(menuService.buildMenuTreeSelect(menus));
    }

    /**
     * 加载对应角色菜单列表树
     */
    @ApiOperation(value = "加载对应角色菜单列表树", nickname = "SysMenuGetRoleMenuTreeSelect")
    @GetMapping(value = "/roleMenuTreeSelect")
    public R<RoleMenuTreeSelectVo> roleMenuTreeSelect(@ApiParam(value = "角色ID", required = true) @RequestParam Long roleId) {
        List<SysMenuVo> menus = menuService.selectMenuList(getUserId());
        RoleMenuTreeSelectVo data = new RoleMenuTreeSelectVo();
        data.setCheckedKeys(menuService.selectMenuListByRoleId(roleId));
        data.setMenus(menuService.buildMenuTreeSelect(menus));
        return R.ok(data);
    }

    /**
     * 新增菜单
     */
    @ApiOperation(value = "新增菜单", nickname = "SysMenuPostAdd")
    @SaCheckPermission("system:menu:add")
    @Log(title = "菜单管理", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    public R<Void> add(@Validated @RequestBody SysMenuAddBo menuBo) {
        SysMenu menu = BeanCopyUtils.copy(menuBo, SysMenu.class);
        if (UserConstants.NOT_UNIQUE.equals(menuService.checkMenuNameUnique(menu))) {
            return R.fail("新增菜单'" + menu.getMenuName() + "'失败，菜单名称已存在");
        } else if (UserConstants.YES_FRAME.equals(menu.getIsFrame()) && !StringUtils.ishttp(menu.getPath())) {
            return R.fail("新增菜单'" + menu.getMenuName() + "'失败，地址必须以http(s)://开头");
        }
        return toAjax(menuService.insertMenu(menu));
    }

    /**
     * 修改菜单
     */
    @ApiOperation(value = "修改菜单", nickname = "SysMenuPostEdit")
    @SaCheckPermission("system:menu:edit")
    @Log(title = "菜单管理", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    public R<Void> edit(@Validated @RequestBody SysMenuEditBo menuBo) {
        SysMenu menu = BeanCopyUtils.copy(menuBo, SysMenu.class);
        if (UserConstants.NOT_UNIQUE.equals(menuService.checkMenuNameUnique(menu))) {
            return R.fail("修改菜单'" + menu.getMenuName() + "'失败，菜单名称已存在");
        } else if (UserConstants.YES_FRAME.equals(menu.getIsFrame()) && !StringUtils.ishttp(menu.getPath())) {
            return R.fail("修改菜单'" + menu.getMenuName() + "'失败，地址必须以http(s)://开头");
        } else if (menu.getMenuId().equals(menu.getParentId())) {
            return R.fail("修改菜单'" + menu.getMenuName() + "'失败，上级菜单不能选择自己");
        }
        return toAjax(menuService.updateMenu(menu));
    }

    /**
     * 删除菜单
     */
    @ApiOperation(value = "删除菜单", nickname = "SysMenuPostRemove")
    @SaCheckPermission("system:menu:remove")
    @Log(title = "菜单管理", businessType = BusinessType.DELETE)
    @PostMapping("/remove")
    public R<Void> remove(@ApiParam(value = "菜单ID", required = true) @RequestParam Long menuId) {
        if (menuService.hasChildByMenuId(menuId)) {
            return R.fail("存在子菜单,不允许删除");
        }
        if (menuService.checkMenuExistRole(menuId)) {
            return R.fail("菜单已分配,不允许删除");
        }
        return toAjax(menuService.deleteMenuById(menuId));
    }
}
