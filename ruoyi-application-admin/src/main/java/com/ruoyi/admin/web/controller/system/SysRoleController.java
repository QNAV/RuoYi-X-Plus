package com.ruoyi.admin.web.controller.system;

import cn.dev33.satoken.annotation.SaCheckPermission;
import cn.hutool.core.util.ObjectUtil;
import com.ruoyi.admin.controller.AdminBaseController;
import com.ruoyi.admin.domain.model.AdminLoginUser;
import com.ruoyi.admin.helper.AdminLoginHelper;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.constant.UserConstants;
import com.ruoyi.common.core.domain.R;
import com.ruoyi.common.core.domain.bo.PageQuery;
import com.ruoyi.common.core.domain.entity.SysRole;
import com.ruoyi.common.core.domain.entity.SysUser;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.BeanCopyUtils;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.system.domain.SysUserRole;
import com.ruoyi.system.domain.bo.*;
import com.ruoyi.common.core.domain.vo.SysRoleVo;
import com.ruoyi.common.core.domain.vo.SysUserVo;
import com.ruoyi.system.service.ISysRoleService;
import com.ruoyi.system.service.ISysUserService;
import com.ruoyi.system.service.SysPermissionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 角色信息
 *
 * @author ruoyi
 * @author Lion Li
 */
@Validated
@Tag(description = "角色信息管理", name = "SysRoleService")
@RequiredArgsConstructor
@RestController
@RequestMapping("/system/role")
public class SysRoleController extends AdminBaseController {

    private final ISysRoleService roleService;
    private final ISysUserService userService;
    private final SysPermissionService permissionService;

    @Operation(description = "查询角色信息列表", summary = "SysRolePostList")
    @SaCheckPermission("system:role:list")
    @PostMapping("/list")
    public TableDataInfo<SysRoleVo> list(@RequestBody(required = false) SysRolePageQueryBo rolePageQuery) {
        // 组装分页参数
        PageQuery pageQuery = BeanCopyUtils.copy(rolePageQuery, PageQuery.class);
        // 组装查询参数
        SysRoleQueryBo roleQuery = BeanCopyUtils.copy(rolePageQuery, SysRoleQueryBo.class);
        return roleService.selectPageRoleVoList(roleQuery, pageQuery);
    }

    @Operation(description = "导出角色信息列表", summary = "SysRolePostExport")
    @Log(title = "角色管理", businessType = BusinessType.EXPORT)
    @SaCheckPermission("system:role:export")
    @PostMapping("/export")
    public void export(@RequestBody(required = false) SysRoleQueryBo roleQuery, @Parameter(hidden = true) HttpServletResponse response) {
        List<SysRole> list = roleService.selectRoleList(roleQuery);
        ExcelUtil.exportExcel(list, "角色数据", SysRole.class, response);
    }

    /**
     * 根据角色编号获取详细信息
     */
    @Operation(description = "根据角色编号获取详细信息", summary = "SysRoleGetInfo")
    @SaCheckPermission("system:role:query")
    @GetMapping(value = "/info")
    public R<SysRoleVo> info(@Parameter(description = "角色ID",required = true) @RequestParam Long roleId) {
        roleService.checkRoleDataScope(roleId);
        return R.ok(roleService.selectRoleVoById(roleId));
    }

    /**
     * 新增角色
     */
    @Operation(description = "新增角色", summary = "SysRolePostAdd")
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
    @Operation(description = "修改保存角色", summary = "SysRolePostEdit")
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
            AdminLoginUser loginUser = getLoginUser();
            SysUser sysUser = userService.selectUserById(loginUser.getUserId());
            if (ObjectUtil.isNotNull(sysUser) && !sysUser.isAdmin()) {
                loginUser.setMenuPermission(permissionService.getMenuPermission(sysUser));
                AdminLoginHelper.setLoginUser(loginUser);
            }
            return R.ok();
        }
        return R.fail("修改角色'" + role.getRoleName() + "'失败，请联系管理员");
    }

    /**
     * 修改保存数据权限
     */
    @Operation(description = "修改保存数据权限", summary = "SysRolePostDataScope")
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
    @Operation(description = "状态修改", summary = "SysRolePostChangeStatus")
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
    @Operation(description = "删除角色", summary = "SysRolePostRemove")
    @SaCheckPermission("system:role:remove")
    @Log(title = "角色管理", businessType = BusinessType.DELETE)
    @PostMapping("/remove")
    public R<Void> remove(@Parameter(description = "角色ID串",required = true) @RequestParam Long[] roleIds) {
        return toAjax(roleService.deleteRoleByIds(roleIds));
    }

    /**
     * 获取角色选择框列表
     */
    @Operation(description = "获取角色选择框列表", summary = "SysRoleGetOptionSelect")
    @SaCheckPermission("system:role:query")
    @GetMapping("/optionSelect")
    public R<List<SysRoleVo>> optionSelect() {
        return R.ok(roleService.selectRoleVoAll());
    }

    /**
     * 查询已分配用户角色列表
     */
    @Operation(description = "查询已分配用户角色列表", summary = "SysRolePostAllocatedList")
    @SaCheckPermission("system:role:list")
    @PostMapping("/authUser/allocatedList")
    public TableDataInfo<SysUserVo> allocatedList(@RequestBody(required = false) SysUserPageQueryBo userPageQuery) {
        // 组装分页参数
        PageQuery pageQuery = BeanCopyUtils.copy(userPageQuery, PageQuery.class);
        // 组装查询参数
        SysUserQueryBo userQuery = BeanCopyUtils.copy(userPageQuery, SysUserQueryBo.class);
        return userService.selectAllocatedVoList(userQuery, pageQuery);
    }

    /**
     * 查询未分配用户角色列表
     */
    @Operation(description = "查询未分配用户角色列表", summary = "SysRolePostUnallocatedList")
    @SaCheckPermission("system:role:list")
    @PostMapping("/authUser/unallocatedList")
    public TableDataInfo<SysUserVo> unallocatedList(@RequestBody(required = false) SysUserPageQueryBo userPageQuery) {
        // 组装分页参数
        PageQuery pageQuery = BeanCopyUtils.copy(userPageQuery, PageQuery.class);
        // 组装查询参数
        SysUserQueryBo userQuery = BeanCopyUtils.copy(userPageQuery, SysUserQueryBo.class);
        return userService.selectUnallocatedVoList(userQuery, pageQuery);
    }

    /**
     * 取消授权用户
     */
    @Operation(description = "取消授权用户", summary = "SysRolePostCancelAuthUser")
    @SaCheckPermission("system:role:edit")
    @Log(title = "角色管理", businessType = BusinessType.GRANT)
    @PostMapping("/authUser/cancel")
    public R<Void> cancelAuthUser(@RequestBody SysUserRole userRole) {
        return toAjax(roleService.deleteAuthUser(userRole));
    }

    /**
     * 批量取消授权用户
     */
    @Operation(description = "批量取消授权用户", summary = "SysUserPostCancelAuthUserAll")
    @SaCheckPermission("system:role:edit")
    @Log(title = "角色管理", businessType = BusinessType.GRANT)
    @PostMapping("/authUser/cancelAll")
    public R<Void> cancelAuthUserAll(@RequestBody @Validated AuthUserAllBo body) {
        return toAjax(roleService.deleteAuthUsers(body.getRoleId(), body.getUserIds()));
    }

    /**
     * 批量选择用户授权
     */
    @Operation(description = "批量选择用户授权", summary = "SysUserPostSelectAuthUserAll")
    @SaCheckPermission("system:role:edit")
    @Log(title = "角色管理", businessType = BusinessType.GRANT)
    @PostMapping("/authUser/selectAll")
    public R<Void> selectAuthUserAll(@RequestBody @Validated AuthUserAllBo body) {
        roleService.checkRoleDataScope(body.getRoleId());
        return toAjax(roleService.insertAuthUsers(body.getRoleId(), body.getUserIds()));
    }
}
