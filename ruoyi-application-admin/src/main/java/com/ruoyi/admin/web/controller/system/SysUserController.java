package com.ruoyi.admin.web.controller.system;

import cn.dev33.satoken.annotation.SaCheckPermission;
import cn.dev33.satoken.secure.BCrypt;
import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.lang.tree.Tree;
import cn.hutool.core.util.ArrayUtil;
import cn.hutool.core.util.ObjectUtil;
import com.ruoyi.admin.controller.AdminBaseController;
import com.ruoyi.admin.helper.AdminLoginHelper;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.constant.UserConstants;
import com.ruoyi.common.core.domain.bo.PageQuery;
import com.ruoyi.common.core.domain.R;
import com.ruoyi.common.core.domain.entity.SysDept;
import com.ruoyi.common.core.domain.entity.SysRole;
import com.ruoyi.common.core.domain.entity.SysUser;
import com.ruoyi.common.core.domain.vo.SysRoleVo;
import com.ruoyi.common.core.domain.vo.SysUserVo;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.enums.CommonYesOrNo;
import com.ruoyi.common.excel.ExcelResult;
import com.ruoyi.common.utils.BeanCopyUtils;
import com.ruoyi.common.utils.StreamUtils;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.system.domain.bo.*;
import com.ruoyi.system.domain.vo.*;
import com.ruoyi.system.listener.SysUserImportListener;
import com.ruoyi.system.service.ISysDeptService;
import com.ruoyi.system.service.ISysPostService;
import com.ruoyi.system.service.ISysRoleService;
import com.ruoyi.system.service.ISysUserService;
import com.ruoyi.admin.web.model.vo.UserAuthRoleVo;
import com.ruoyi.admin.web.model.vo.UserDetailVo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;


/**
 * 用户信息
 *
 * @author ruoyi
 * @author Lion Li
 */
@Validated
@Tag(description = "用户信息管理", name = "SysUserService")
@RequiredArgsConstructor
@RestController
@RequestMapping("/system/user")
public class SysUserController extends AdminBaseController {

    private final ISysUserService userService;
    private final ISysRoleService roleService;
    private final ISysPostService postService;
    private final ISysDeptService deptService;

    /**
     * 获取用户列表
     */
    @Operation(description = "获取用户列表", operationId = "SysUserPostList")
    @SaCheckPermission("system:user:list")
    @PostMapping("/list")
    public TableDataInfo<SysUserVo> list(@RequestBody(required = false) SysUserPageQueryBo userPageQuery) {
        // 组装分页参数
        PageQuery pageQuery = BeanCopyUtils.copy(userPageQuery, PageQuery.class);
        // 组装查询参数
        SysUserQueryBo userQuery = BeanCopyUtils.copy(userPageQuery, SysUserQueryBo.class);
        return userService.selectPageUserVoList(userQuery, pageQuery);
    }

    /**
     * 导出用户列表
     */
    @Operation(description = "导出用户列表", operationId = "SysUserPostExport")
    @Log(title = "用户管理", businessType = BusinessType.EXPORT)
    @SaCheckPermission("system:user:export")
    @PostMapping("/export")
    public void export(@RequestBody(required = false) SysUserQueryBo userQuery, @Parameter(hidden = true) HttpServletResponse response) {
        List<SysUser> list = userService.selectUserList(userQuery);
        List<SysUserExportVo> listVo = BeanUtil.copyToList(list, SysUserExportVo.class);
        for (int i = 0; i < list.size(); i++) {
            SysDept dept = list.get(i).getDept();
            SysUserExportVo vo = listVo.get(i);
            if (ObjectUtil.isNotEmpty(dept)) {
                vo.setDeptName(dept.getDeptName());
                vo.setLeader(dept.getLeader());
            }
        }
        ExcelUtil.exportExcel(listVo, "用户数据", SysUserExportVo.class, response);
    }


    /**
     * 导入数据
     *
     * @param file          导入文件
     * @param updateSupport 是否更新已存在数据
     */
    @Operation(description = "导入用户列表", operationId = "SysUserPostImportData")
    @Log(title = "用户管理", businessType = BusinessType.IMPORT)
    @SaCheckPermission("system:user:import")
    @PostMapping("/importData")
    public R<Void> importData(@RequestPart("file") MultipartFile file, boolean updateSupport) throws Exception {
        ExcelResult<SysUserImportVo> result = ExcelUtil.importExcel(file.getInputStream(), SysUserImportVo.class, new SysUserImportListener(updateSupport));
        return R.ok(result.getAnalysis());
    }

    /**
     * 获取导入模板
     */
    @Operation(description = "下载导入模板", operationId = "SysUserPostImportTemplate")
    @PostMapping("/importTemplate")
    public void importTemplate(@Parameter(hidden = true) HttpServletResponse response) {
        ExcelUtil.exportExcel(new ArrayList<>(), "用户数据", SysUserImportVo.class, response);
    }

    /**
     * 根据用户编号获取详细信息
     */
    @Operation(description = "根据用户编号获取详细信息", operationId = "SysUserGetInfo")
    @SaCheckPermission("system:user:query")
    @GetMapping(value = {"/","/info"})
    public R<UserDetailVo> info(@Parameter(description = "用户ID",required = false) @RequestParam(required = false) Long userId) {
        userService.checkUserDataScope(userId);
        UserDetailVo data = new UserDetailVo();
        List<SysRoleVo> roles = roleService.selectRoleVoAll();
        data.setRoles(AdminLoginHelper.isAdmin(userId) ? roles : StreamUtils.filter(roles, r -> !r.isAdmin()));
        data.setPosts(BeanCopyUtils.copyList(postService.selectPostAll(), SysPostVo.class));
        if (ObjectUtil.isNotNull(userId)) {
            SysUserVo sysUser = userService.selectUserVoById(userId);
            data.setUser(sysUser);
            data.setPostIds(postService.selectPostListByUserId(userId));
            data.setRoleIds(StreamUtils.toList(sysUser.getRoles(), SysRoleVo::getRoleId));
        }
        return R.ok(data);
    }

    /**
     * 新增用户
     */
    @Operation(description = "新增用户", operationId = "SysUserPostAdd")
    @SaCheckPermission("system:user:add")
    @Log(title = "用户管理", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    public R<Void> add(@Validated @RequestBody SysUserAddBo userBo) {
        SysUser user = BeanCopyUtils.copy(userBo, SysUser.class);
        if (CommonYesOrNo.NO.equals(userService.checkUserNameUnique(user))) {
            return R.fail("新增用户'" + user.getUserName() + "'失败，登录账号已存在");
        } else if (StringUtils.isNotEmpty(user.getPhoneNumber())
            && CommonYesOrNo.NO.equals(userService.checkPhoneUnique(user))) {
            return R.fail("新增用户'" + user.getUserName() + "'失败，手机号码已存在");
        } else if (StringUtils.isNotEmpty(user.getEmail())
            && CommonYesOrNo.NO.equals(userService.checkEmailUnique(user))) {
            return R.fail("新增用户'" + user.getUserName() + "'失败，邮箱账号已存在");
        }
        user.setPassword(BCrypt.hashpw(user.getPassword()));
        return toAjax(userService.insertUser(user));
    }

    /**
     * 修改用户
     */
    @Operation(description = "修改用户", operationId = "SysUserPostEdit")
    @SaCheckPermission("system:user:edit")
    @Log(title = "用户管理", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    public R<Void> edit(@Validated @RequestBody SysUserEditBo userBo) {
        SysUser user = BeanCopyUtils.copy(userBo, SysUser.class);
        userService.checkUserAllowed(userBo);
        userService.checkUserDataScope(user.getUserId());
        if (CommonYesOrNo.NO.equals(userService.checkUserNameUnique(user)))
        {
            return R.fail("修改用户'" + user.getUserName() + "'失败，登录账号已存在");
        }
        else if (StringUtils.isNotEmpty(user.getPhoneNumber())
            && CommonYesOrNo.NO.equals(userService.checkPhoneUnique(user))) {
            return R.fail("修改用户'" + user.getUserName() + "'失败，手机号码已存在");
        } else if (StringUtils.isNotEmpty(user.getEmail())
            && CommonYesOrNo.NO.equals(userService.checkEmailUnique(user))) {
            return R.fail("修改用户'" + user.getUserName() + "'失败，邮箱账号已存在");
        }
        return toAjax(userService.updateUser(user));
    }

    /**
     * 删除用户
     */
    @Operation(description = "删除用户", operationId = "SysUserPostRemove")
    @SaCheckPermission("system:user:remove")
    @Log(title = "用户管理", businessType = BusinessType.DELETE)
    @PostMapping("/remove")
    public R<Void> remove(@Parameter(description = "角色ID串", required = true) @RequestParam Long[] userIds) {
        if (ArrayUtil.contains(userIds, getUserId())) {
            return R.fail("当前用户不能删除");
        }
        return toAjax(userService.deleteUserByIds(userIds));
    }

    /**
     * 重置密码
     */
    @Operation(description = "重置密码", operationId = "SysUserPostResetPwd")
    @SaCheckPermission("system:user:resetPwd")
    @Log(title = "用户管理", businessType = BusinessType.UPDATE)
    @PostMapping("/resetPwd")
    public R<Void> resetPwd(@RequestBody SysUserEditBo userBo) {
        userService.checkUserAllowed(userBo);
        userService.checkUserDataScope(userBo.getUserId());
        userBo.setPassword(BCrypt.hashpw(userBo.getPassword()));
        return toAjax(userService.resetPwd(userBo));
    }

    /**
     * 状态修改
     */
    @Operation(description = "状态修改", operationId = "SysUserPostChangeStatus")
    @SaCheckPermission("system:user:edit")
    @Log(title = "用户管理", businessType = BusinessType.UPDATE)
    @PostMapping("/changeStatus")
    public R<Void> changeStatus(@RequestBody SysUserEditBo userBo) {
        userService.checkUserAllowed(userBo);
        userService.checkUserDataScope(userBo.getUserId());
        return toAjax(userService.updateUserStatus(userBo));
    }

    /**
     * 根据用户编号获取授权角色
     */
    @Operation(description = "根据用户编号获取授权角色", operationId = "SysUserGetAuthRole")
    @SaCheckPermission("system:user:query")
    @GetMapping("/authRole")
    public R<UserAuthRoleVo> authRole(@Parameter(description = "用户ID", required = true) @RequestParam Long userId) {
        SysUser user = userService.selectUserById(userId);
        List<SysRole> roles = roleService.selectRolesByUserId(userId);
        UserAuthRoleVo data = new UserAuthRoleVo();
        data.setUser(user);
        data.setRoles(AdminLoginHelper.isAdmin(userId) ? roles : StreamUtils.filter(roles, r -> !r.isAdmin()));
        return R.ok(data);
    }

    /**
     * 用户授权角色
     */
    @Operation(description = "用户授权角色", operationId = "SysUserPostInsertAuthRole")
    @SaCheckPermission("system:user:edit")
    @Log(title = "用户管理", businessType = BusinessType.GRANT)
    @PostMapping("/insertAuthRole")
    public R<Void> insertAuthRole(@RequestBody @Validated AuthRoleAllBo body) {
        userService.checkUserDataScope(body.getUserId());
        userService.insertUserAuth(body.getUserId(), body.getRoleIds());
        return R.ok();
    }

    /**
     * 获取部门树列表
     */
    @Operation(description = "获取部门树列表", operationId = "SysUserGetDeptTree")
    @SaCheckPermission("system:user:list")
    @GetMapping("/deptTree")
    public R<List<Tree<Long>>> deptTree(SysDeptQueryBo deptQuery) {
        return R.ok(deptService.selectDeptTreeList(deptQuery));
    }
}
