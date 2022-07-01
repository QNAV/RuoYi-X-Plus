package com.ruoyi.web.controller.system;

import cn.dev33.satoken.annotation.SaCheckPermission;
import cn.hutool.core.util.ObjectUtil;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.constant.UserConstants;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.R;
import com.ruoyi.common.core.domain.PageQuery;
import com.ruoyi.common.core.domain.entity.SysRole;
import com.ruoyi.common.core.domain.entity.SysUser;
import com.ruoyi.common.core.domain.model.LoginUser;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.helper.LoginHelper;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.system.domain.SysUserRole;
import com.ruoyi.system.domain.to.AuthUserAllBody;
import com.ruoyi.system.domain.to.SysRoleQuery;
import com.ruoyi.system.domain.to.SysUserQuery;
import com.ruoyi.system.service.ISysRoleService;
import com.ruoyi.system.service.ISysUserService;
import com.ruoyi.system.service.SysPermissionService;
import io.swagger.annotations.*;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 角色信息
 *
 * @author weibocy
 */
@Validated
@Api(value = "角色信息管理", tags = {"SysRoleService"})
@RequiredArgsConstructor
@RestController
@RequestMapping("/system/role")
public class SysRoleController extends BaseController {

    private final ISysRoleService roleService;
    private final ISysUserService userService;
    private final SysPermissionService permissionService;

    @ApiOperation(value = "查询角色信息列表", nickname = "SysRolePostList")
    @SaCheckPermission("system:role:list")
    @PostMapping("/list")
    public TableDataInfo<SysRole> list(@RequestBody SysRoleQuery roleQuery,
                                       @ApiParam(value = "当前页数", defaultValue = "1") @RequestParam Integer pageNum,
                                       @ApiParam(value = "分页大小", defaultValue = "10") @RequestParam Integer pageSize,
                                       @ApiParam("排序列") @RequestParam String orderByColumn,
                                       @ApiParam(value = "排序的方向", example = "asc,desc") @RequestParam String isAsc) {
        PageQuery pageQuery = new PageQuery();
        pageQuery.setPageNum(pageNum);
        pageQuery.setPageSize(pageSize);
        pageQuery.setOrderByColumn(orderByColumn);
        pageQuery.setIsAsc(isAsc);
        return roleService.selectPageRoleList(roleQuery, pageQuery);
    }

    @ApiOperation(value = "导出角色信息列表", nickname = "SysRolePostExport")
    @Log(title = "角色管理", businessType = BusinessType.EXPORT)
    @SaCheckPermission("system:role:export")
    @PostMapping("/export")
    public void export(@RequestBody SysRoleQuery roleQuery, @ApiParam(hidden = true) HttpServletResponse response) {
        List<SysRole> list = roleService.selectRoleList(roleQuery);
        ExcelUtil.exportExcel(list, "角色数据", SysRole.class, response);
    }

    /**
     * 根据角色编号获取详细信息
     */
    @ApiOperation(value = "根据角色编号获取详细信息", nickname = "SysRoleGetInfo")
    @SaCheckPermission("system:role:query")
    @GetMapping(value = "/info")
    public R<SysRole> info(@ApiParam(value = "角色ID",required = true) @RequestParam Long roleId) {
        roleService.checkRoleDataScope(roleId);
        return R.ok(roleService.selectRoleById(roleId));
    }

    /**
     * 新增角色
     */
    @ApiOperation(value = "新增角色", nickname = "SysRolePostAdd")
    @SaCheckPermission("system:role:add")
    @Log(title = "角色管理", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    public R<Void> add(@Validated @RequestBody SysRole role) {
        if (UserConstants.NOT_UNIQUE.equals(roleService.checkRoleNameUnique(role))) {
            return R.fail("新增角色'" + role.getRoleName() + "'失败，角色名称已存在");
        } else if (UserConstants.NOT_UNIQUE.equals(roleService.checkRoleKeyUnique(role))) {
            return R.fail("新增角色'" + role.getRoleName() + "'失败，角色权限已存在");
        }
        return toAjax(roleService.insertRole(role));

    }

    /**
     * 修改保存角色
     */
    @ApiOperation(value = "修改保存角色", nickname = "SysRolePostEdit")
    @SaCheckPermission("system:role:edit")
    @Log(title = "角色管理", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    public R<Void> edit(@Validated @RequestBody SysRole role) {
        roleService.checkRoleAllowed(role);
        roleService.checkRoleDataScope(role.getRoleId());
        if (UserConstants.NOT_UNIQUE.equals(roleService.checkRoleNameUnique(role))) {
            return R.fail("修改角色'" + role.getRoleName() + "'失败，角色名称已存在");
        } else if (UserConstants.NOT_UNIQUE.equals(roleService.checkRoleKeyUnique(role))) {
            return R.fail("修改角色'" + role.getRoleName() + "'失败，角色权限已存在");
        }

        if (roleService.updateRole(role) > 0) {
            // 更新缓存用户权限
            LoginUser loginUser = getLoginUser();
            SysUser sysUser = userService.selectUserById(loginUser.getUserId());
            if (ObjectUtil.isNotNull(sysUser) && !sysUser.isAdmin()) {
                loginUser.setMenuPermission(permissionService.getMenuPermission(sysUser));
                LoginHelper.setLoginUser(loginUser);
            }
            return R.ok();
        }
        return R.fail("修改角色'" + role.getRoleName() + "'失败，请联系管理员");
    }

    /**
     * 修改保存数据权限
     */
    @ApiOperation(value = "修改保存数据权限", nickname = "SysRolePostDataScope")
    @SaCheckPermission("system:role:edit")
    @Log(title = "角色管理", businessType = BusinessType.UPDATE)
    @PostMapping("/dataScope")
    public R<Void> dataScope(@RequestBody SysRole role) {
        roleService.checkRoleAllowed(role);
        roleService.checkRoleDataScope(role.getRoleId());
        return toAjax(roleService.authDataScope(role));
    }

    /**
     * 状态修改
     */
    @ApiOperation(value = "状态修改", nickname = "SysRolePostChangeStatus")
    @SaCheckPermission("system:role:edit")
    @Log(title = "角色管理", businessType = BusinessType.UPDATE)
    @PostMapping("/changeStatus")
    public R<Void> changeStatus(@RequestBody SysRole role) {
        roleService.checkRoleAllowed(role);
        roleService.checkRoleDataScope(role.getRoleId());
        return toAjax(roleService.updateRoleStatus(role));
    }

    /**
     * 删除角色
     */
    @ApiOperation(value = "删除角色", nickname = "SysRolePostRemove")
    @SaCheckPermission("system:role:remove")
    @Log(title = "角色管理", businessType = BusinessType.DELETE)
    @PostMapping("/remove")
    public R<Void> remove(@ApiParam(value = "角色ID串",required = true) @RequestParam Long[] roleIds) {
        return toAjax(roleService.deleteRoleByIds(roleIds));
    }

    /**
     * 获取角色选择框列表
     */
    @ApiOperation(value = "获取角色选择框列表", nickname = "SysRoleGetOptionSelect")
    @SaCheckPermission("system:role:query")
    @GetMapping("/optionSelect")
    public R<List<SysRole>> optionSelect() {
        return R.ok(roleService.selectRoleAll());
    }

    /**
     * 查询已分配用户角色列表
     */
    @ApiOperation(value = "查询已分配用户角色列表", nickname = "SysRolePostAllocatedList")
    @SaCheckPermission("system:role:list")
    @PostMapping("/authUser/allocatedList")
    public TableDataInfo<SysUser> allocatedList(@RequestBody SysUserQuery userQuery,
                                                @ApiParam(value = "当前页数", defaultValue = "1") @RequestParam Integer pageNum,
                                                @ApiParam(value = "分页大小", defaultValue = "10") @RequestParam Integer pageSize,
                                                @ApiParam("排序列") @RequestParam String orderByColumn,
                                                @ApiParam(value = "排序的方向", example = "asc,desc") @RequestParam String isAsc) {
        PageQuery pageQuery = new PageQuery();
        pageQuery.setPageNum(pageNum);
        pageQuery.setPageSize(pageSize);
        pageQuery.setOrderByColumn(orderByColumn);
        pageQuery.setIsAsc(isAsc);
        return userService.selectAllocatedList(userQuery, pageQuery);
    }

    /**
     * 查询未分配用户角色列表
     */
    @ApiOperation(value = "查询未分配用户角色列表", nickname = "SysRolePostUnallocatedList")
    @SaCheckPermission("system:role:list")
    @PostMapping("/authUser/unallocatedList")
    public TableDataInfo<SysUser> unallocatedList(@RequestBody SysUserQuery userQuery,
                                                  @ApiParam(value = "当前页数", defaultValue = "1") @RequestParam Integer pageNum,
                                                  @ApiParam(value = "分页大小", defaultValue = "10") @RequestParam Integer pageSize,
                                                  @ApiParam("排序列") @RequestParam String orderByColumn,
                                                  @ApiParam(value = "排序的方向", example = "asc,desc") @RequestParam String isAsc) {
        PageQuery pageQuery = new PageQuery();
        pageQuery.setPageNum(pageNum);
        pageQuery.setPageSize(pageSize);
        pageQuery.setOrderByColumn(orderByColumn);
        pageQuery.setIsAsc(isAsc);
        return userService.selectUnallocatedList(userQuery, pageQuery);
    }

    /**
     * 取消授权用户
     */
    @ApiOperation(value = "取消授权用户", nickname = "SysRolePostCancelAuthUser")
    @SaCheckPermission("system:role:edit")
    @Log(title = "角色管理", businessType = BusinessType.GRANT)
    @PostMapping("/authUser/cancel")
    public R<Void> cancelAuthUser(@RequestBody SysUserRole userRole) {
        return toAjax(roleService.deleteAuthUser(userRole));
    }

    /**
     * 批量取消授权用户
     */
    @ApiOperation(value = "批量取消授权用户", nickname = "SysUserPostCancelAuthUserAll")
    @SaCheckPermission("system:role:edit")
    @Log(title = "角色管理", businessType = BusinessType.GRANT)
    @PostMapping("/authUser/cancelAll")
    public R<Void> cancelAuthUserAll(@RequestBody @Validated AuthUserAllBody body) {
        return toAjax(roleService.deleteAuthUsers(body.getRoleId(), body.getUserIds()));
    }

    /**
     * 批量选择用户授权
     */
    @ApiOperation(value = "批量选择用户授权", nickname = "SysUserPostSelectAuthUserAll")
    @SaCheckPermission("system:role:edit")
    @Log(title = "角色管理", businessType = BusinessType.GRANT)
    @PostMapping("/authUser/selectAll")
    public R<Void> selectAuthUserAll(@RequestBody @Validated AuthUserAllBody body) {
        roleService.checkRoleDataScope(body.getRoleId());
        return toAjax(roleService.insertAuthUsers(body.getRoleId(), body.getUserIds()));
    }
}
