package com.ruoyi.admin.web.controller.system;

import cn.dev33.satoken.annotation.SaCheckPermission;
import cn.dev33.satoken.exception.NotLoginException;
import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.core.collection.CollUtil;
import com.ruoyi.common.constant.CacheConstants;
import com.ruoyi.common.constant.UserConstants;
import com.ruoyi.admin.controller.AdminBaseController;
import com.ruoyi.admin.domain.model.AdminLoginUser;
import com.ruoyi.admin.helper.AdminLoginHelper;
import com.ruoyi.common.annotation.AdminLog;
import com.ruoyi.common.core.domain.R;
import com.ruoyi.common.core.domain.bo.PageQuery;
import com.ruoyi.common.core.domain.entity.SysRole;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessTypeEnum;
import com.ruoyi.common.enums.CommonYesOrNoEnum;
import com.ruoyi.common.utils.BeanCopyUtils;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.system.domain.SysUserRole;
import com.ruoyi.system.domain.bo.*;
import com.ruoyi.common.core.domain.vo.SysRoleVo;
import com.ruoyi.common.core.domain.vo.SysUserVo;
import com.ruoyi.system.service.ISysDeptService;
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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    private final ISysDeptService deptService;

    @Operation(description = "查询角色信息列表", operationId = "SysRolePostList")
    @SaCheckPermission("system:role:list")
    @PostMapping("/list")
    public TableDataInfo<SysRoleVo> list(@RequestBody(required = false) SysRolePageQueryBo rolePageQuery) {
        // 组装分页参数
        PageQuery pageQuery = BeanCopyUtils.copy(rolePageQuery, PageQuery.class);
        // 组装查询参数
        SysRoleQueryBo roleQuery = BeanCopyUtils.copy(rolePageQuery, SysRoleQueryBo.class);
        return roleService.selectPageRoleVoList(roleQuery, pageQuery);
    }

    @Operation(description = "导出角色信息列表", operationId = "SysRolePostExport")
    @AdminLog(title = "角色管理", businessType = BusinessTypeEnum.EXPORT)
    @SaCheckPermission("system:role:export")
    @PostMapping("/export")
    public void export(@RequestBody(required = false) SysRoleQueryBo roleQuery, @Parameter(hidden = true) HttpServletResponse response) {
        List<SysRole> list = roleService.selectRoleList(roleQuery);
        ExcelUtil.exportExcel(list, "角色数据", SysRole.class, response);
    }

    /**
     * 根据角色编号获取详细信息
     */
    @Operation(description = "根据角色编号获取详细信息", operationId = "SysRoleGetInfo")
    @SaCheckPermission("system:role:query")
    @GetMapping(value = "/info")
    public R<SysRoleVo> info(@Parameter(description = "角色ID",required = true) @RequestParam Long roleId) {
        roleService.checkRoleDataScope(roleId);
        return R.ok(roleService.selectRoleVoById(roleId));
    }

    /**
     * 新增角色
     */
    @Operation(description = "新增角色", operationId = "SysRolePostAdd")
    @SaCheckPermission("system:role:add")
    @AdminLog(title = "角色管理", businessType = BusinessTypeEnum.ADD)
    @PostMapping("/add")
    public R<Void> add(@Validated @RequestBody SysRole role) {
        if (CommonYesOrNoEnum.NO.equals(roleService.checkRoleNameUnique(role))) {
            return R.fail("新增角色'" + role.getRoleName() + "'失败，角色名称已存在");
        } else if (CommonYesOrNoEnum.NO.equals(roleService.checkRoleKeyUnique(role))) {
            return R.fail("新增角色'" + role.getRoleName() + "'失败，角色权限已存在");
        }
        return toAjax(roleService.insertRole(role));

    }

    /**
     * 修改保存角色
     */
    @Operation(description = "修改保存角色", operationId = "SysRolePostEdit")
    @SaCheckPermission("system:role:edit")
    @AdminLog(title = "角色管理", businessType = BusinessTypeEnum.MODIFY)
    @PostMapping("/edit")
    public R<Void> edit(@Validated @RequestBody SysRole role) {
        roleService.checkRoleAllowed(role);
        roleService.checkRoleDataScope(role.getRoleId());
        if (CommonYesOrNoEnum.NO.equals(roleService.checkRoleNameUnique(role))) {
            return R.fail("修改角色'" + role.getRoleName() + "'失败，角色名称已存在");
        } else if (CommonYesOrNoEnum.NO.equals(roleService.checkRoleKeyUnique(role))) {
            return R.fail("修改角色'" + role.getRoleName() + "'失败，角色权限已存在");
        }

        if (roleService.updateRole(role) > 0) {
            List<String> keys = StpUtil.searchTokenValue("", 0, -1, false);
            if (CollUtil.isEmpty(keys)) {
                return R.ok();
            }
            // 角色关联的在线用户量过大会导致redis阻塞卡顿 谨慎操作
            keys.parallelStream().forEach(key -> {
                String token = key.replace(CacheConstants.ADMIN_LOGIN_TOKEN_KEY, "");
                // 如果已经过期则跳过
                if (StpUtil.stpLogic.getTokenActivityTimeoutByToken(token) < -1) {
                    return;
                }
                AdminLoginUser loginUser = AdminLoginHelper.getLoginUser(token);
                if (loginUser.getRoles().stream().anyMatch(r -> r.getRoleId().equals(role.getRoleId()))) {
                    try {
                        StpUtil.logoutByTokenValue(token);
                    } catch (NotLoginException ignored) {
                    }
                }
            });
            return R.ok();
        }
        return R.fail("修改角色'" + role.getRoleName() + "'失败，请联系管理员");
    }

    /**
     * 修改保存数据权限
     */
    @Operation(description = "修改保存数据权限", operationId = "SysRolePostDataScope")
    @SaCheckPermission("system:role:edit")
    @AdminLog(title = "角色管理", businessType = BusinessTypeEnum.MODIFY)
    @PostMapping("/dataScope")
    public R<Void> dataScope(@RequestBody SysRole role) {
        roleService.checkRoleAllowed(role);
        roleService.checkRoleDataScope(role.getRoleId());
        return toAjax(roleService.authDataScope(role));
    }

    /**
     * 状态修改
     */
    @Operation(description = "状态修改", operationId = "SysRolePostChangeStatus")
    @SaCheckPermission("system:role:edit")
    @AdminLog(title = "角色管理", businessType = BusinessTypeEnum.MODIFY)
    @PostMapping("/changeStatus")
    public R<Void> changeStatus(@RequestBody SysRole role) {
        roleService.checkRoleAllowed(role);
        roleService.checkRoleDataScope(role.getRoleId());
        return toAjax(roleService.updateRoleStatus(role));
    }

    /**
     * 删除角色
     */
    @Operation(description = "删除角色", operationId = "SysRolePostRemove")
    @SaCheckPermission("system:role:remove")
    @AdminLog(title = "角色管理", businessType = BusinessTypeEnum.DELETE)
    @PostMapping("/remove")
    public R<Void> remove(@Parameter(description = "角色ID串",required = true) @RequestParam Long[] roleIds) {
        return toAjax(roleService.deleteRoleByIds(roleIds));
    }

    /**
     * 获取角色选择框列表
     */
    @Operation(description = "获取角色选择框列表", operationId = "SysRoleGetOptionSelect")
    @SaCheckPermission("system:role:query")
    @GetMapping("/optionSelect")
    public R<List<SysRoleVo>> optionSelect() {
        return R.ok(roleService.selectRoleVoAll());
    }

    /**
     * 查询已分配用户角色列表
     */
    @Operation(description = "查询已分配用户角色列表", operationId = "SysRolePostAllocatedList")
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
    @Operation(description = "查询未分配用户角色列表", operationId = "SysRolePostUnallocatedList")
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
    @Operation(description = "取消授权用户", operationId = "SysRolePostCancelAuthUser")
    @SaCheckPermission("system:role:edit")
    @AdminLog(title = "角色管理", businessType = BusinessTypeEnum.GRANT)
    @PostMapping("/authUser/cancel")
    public R<Void> cancelAuthUser(@RequestBody SysUserRole userRole) {
        return toAjax(roleService.deleteAuthUser(userRole));
    }

    /**
     * 批量取消授权用户
     */
    @Operation(description = "批量取消授权用户", operationId = "SysRolePostCancelAuthUserAll")
    @SaCheckPermission("system:role:edit")
    @AdminLog(title = "角色管理", businessType = BusinessTypeEnum.GRANT)
    @PostMapping("/authUser/cancelAll")
    public R<Void> cancelAuthUserAll(@RequestBody @Validated AuthUserAllBo body) {
        return toAjax(roleService.deleteAuthUsers(body.getRoleId(), body.getUserIds()));
    }

    /**
     * 批量选择用户授权
     */
    @Operation(description = "批量选择用户授权", operationId = "SysRolePostSelectAuthUserAll")
    @SaCheckPermission("system:role:edit")
    @AdminLog(title = "角色管理", businessType = BusinessTypeEnum.GRANT)
    @PostMapping("/authUser/selectAll")
    public R<Void> selectAuthUserAll(@RequestBody @Validated AuthUserAllBo body) {
        roleService.checkRoleDataScope(body.getRoleId());
        return toAjax(roleService.insertAuthUsers(body.getRoleId(), body.getUserIds()));
    }

    /**
     * 获取对应角色部门树列表
     *
     * @param roleId 角色ID
     */
    @Operation(description = "获取对应角色部门树列表", operationId = "SysRoleGetRoleDeptTreeSelect")
    @SaCheckPermission("system:role:query")
    @GetMapping(value = "/deptTree/{roleId}")
    public R<Map<String, Object>> roleDeptTreeSelect(@PathVariable("roleId") Long roleId) {
        Map<String, Object> ajax = new HashMap<>();
        ajax.put("checkedKeys", deptService.selectDeptListByRoleId(roleId));
        ajax.put("depts", deptService.selectDeptTreeList(new SysDeptQueryBo()));
        return R.ok(ajax);
    }
}
