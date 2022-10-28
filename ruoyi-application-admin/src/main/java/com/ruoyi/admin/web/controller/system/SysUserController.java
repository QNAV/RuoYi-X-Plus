package com.ruoyi.admin.web.controller.system;

import cn.dev33.satoken.annotation.SaCheckPermission;
import cn.dev33.satoken.secure.BCrypt;
import cn.hutool.core.bean.BeanUtil;
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
import com.ruoyi.common.excel.ExcelResult;
import com.ruoyi.common.utils.BeanCopyUtils;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.system.domain.bo.*;
import com.ruoyi.system.domain.vo.*;
import com.ruoyi.system.listener.SysUserImportListener;
import com.ruoyi.system.service.ISysPostService;
import com.ruoyi.system.service.ISysRoleService;
import com.ruoyi.system.service.ISysUserService;
import com.ruoyi.admin.web.model.vo.UserAuthRoleVo;
import com.ruoyi.admin.web.model.vo.UserDetailVo;
import io.swagger.annotations.*;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 用户信息
 *
 * @author weibocy
 */
@Validated
@Api(value = "用户信息管理", tags = {"SysUserService"})
@RequiredArgsConstructor
@RestController
@RequestMapping("/system/user")
public class SysUserController extends AdminBaseController {

    private final ISysUserService userService;
    private final ISysRoleService roleService;
    private final ISysPostService postService;

    /**
     * 获取用户列表
     */
    @ApiOperation(value = "获取用户列表", nickname = "SysUserPostList")
    @SaCheckPermission("system:user:list")
    @PostMapping("/list")
    public TableDataInfo<SysUserVo> list(@RequestBody(required = false) SysUserPageQueryBo userPageQuery) {
        // 组装分页参数
        PageQuery pageQuery = BeanCopyUtils.copy(userPageQuery, PageQuery.class);
        // 组装查询参数
        SysUserQueryBo userQuery = BeanCopyUtils.copy(userPageQuery, SysUserQueryBo.class);
        return userService.selectPageUserVoList(userQuery, pageQuery);
    }

    @ApiOperation(value = "导出用户列表", nickname = "SysUserPostExport")
    @Log(title = "用户管理", businessType = BusinessType.EXPORT)
    @SaCheckPermission("system:user:export")
    @PostMapping("/export")
    public void export(@RequestBody(required = false) SysUserQueryBo userQuery, @ApiParam(hidden = true) HttpServletResponse response) {
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

    @ApiOperation(value = "导入用户列表", nickname = "SysUserPostImportData")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "file", value = "导入文件", dataType = "java.io.File", required = true),
    })
    @Log(title = "用户管理", businessType = BusinessType.IMPORT)
    @SaCheckPermission("system:user:import")
    @PostMapping("/importData")
    public R<Void> importData(@RequestPart("file") MultipartFile file, boolean updateSupport) throws Exception {
        ExcelResult<SysUserImportVo> result = ExcelUtil.importExcel(file.getInputStream(), SysUserImportVo.class, new SysUserImportListener(updateSupport));
        return R.ok(result.getAnalysis());
    }

    @ApiOperation(value = "下载导入模板", nickname = "SysUserPostImportTemplate")
    @PostMapping("/importTemplate")
    public void importTemplate(@ApiParam(hidden = true) HttpServletResponse response) {
        ExcelUtil.exportExcel(new ArrayList<>(), "用户数据", SysUserImportVo.class, response);
    }

    /**
     * 根据用户编号获取详细信息
     */
    @ApiOperation(value = "根据用户编号获取详细信息", nickname = "SysUserGetInfo")
    @SaCheckPermission("system:user:query")
    @GetMapping(value = {"/","/info"})
    public R<UserDetailVo> info(@ApiParam(value = "用户ID",required = false) @RequestParam(required = false) Long userId) {
        userService.checkUserDataScope(userId);
        UserDetailVo data = new UserDetailVo();
        List<SysRoleVo> roles = roleService.selectRoleVoAll();
        data.setRoles(AdminLoginHelper.isAdmin(userId) ? roles : roles.stream().filter(r -> !r.isAdmin()).collect(Collectors.toList()));
        data.setPosts(BeanCopyUtils.copyList(postService.selectPostAll(), SysPostVo.class));
        if (ObjectUtil.isNotNull(userId)) {
            SysUserVo sysUser = userService.selectUserVoById(userId);
            data.setUser(sysUser);
            data.setPostIds(postService.selectPostListByUserId(userId));
            data.setRoleIds(sysUser.getRoles().stream().map(SysRoleVo::getRoleId).collect(Collectors.toList()));
        }
        return R.ok(data);
    }

    /**
     * 新增用户
     */
    @ApiOperation(value = "新增用户", nickname = "SysUserPostAdd")
    @SaCheckPermission("system:user:add")
    @Log(title = "用户管理", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    public R<Void> add(@Validated @RequestBody SysUserAddBo userBo) {
        SysUser user = BeanCopyUtils.copy(userBo, SysUser.class);
        if (UserConstants.NOT_UNIQUE.equals(userService.checkUserNameUnique(user.getUserName()))) {
            return R.fail("新增用户'" + user.getUserName() + "'失败，登录账号已存在");
        } else if (StringUtils.isNotEmpty(user.getPhoneNumber())
            && UserConstants.NOT_UNIQUE.equals(userService.checkPhoneUnique(user))) {
            return R.fail("新增用户'" + user.getUserName() + "'失败，手机号码已存在");
        } else if (StringUtils.isNotEmpty(user.getEmail())
            && UserConstants.NOT_UNIQUE.equals(userService.checkEmailUnique(user))) {
            return R.fail("新增用户'" + user.getUserName() + "'失败，邮箱账号已存在");
        }
        user.setPassword(BCrypt.hashpw(user.getPassword()));
        return toAjax(userService.insertUser(user));
    }

    /**
     * 修改用户
     */
    @ApiOperation(value = "修改用户", nickname = "SysUserPostEdit")
    @SaCheckPermission("system:user:edit")
    @Log(title = "用户管理", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    public R<Void> edit(@Validated @RequestBody SysUserEditBo userBo) {
        SysUser user = BeanCopyUtils.copy(userBo, SysUser.class);
        userService.checkUserAllowed(user);
        userService.checkUserDataScope(user.getUserId());
        if (StringUtils.isNotEmpty(user.getPhoneNumber())
            && UserConstants.NOT_UNIQUE.equals(userService.checkPhoneUnique(user))) {
            return R.fail("修改用户'" + user.getUserName() + "'失败，手机号码已存在");
        } else if (StringUtils.isNotEmpty(user.getEmail())
            && UserConstants.NOT_UNIQUE.equals(userService.checkEmailUnique(user))) {
            return R.fail("修改用户'" + user.getUserName() + "'失败，邮箱账号已存在");
        }
        return toAjax(userService.updateUser(user));
    }

    /**
     * 删除用户
     */
    @ApiOperation(value = "删除用户", nickname = "SysUserPostRemove")
    @SaCheckPermission("system:user:remove")
    @Log(title = "用户管理", businessType = BusinessType.DELETE)
    @PostMapping("/remove")
    public R<Void> remove(@ApiParam(value = "角色ID串", required = true, allowMultiple = true) @RequestParam Long[] userIds) {
        if (ArrayUtil.contains(userIds, getUserId())) {
            return R.fail("当前用户不能删除");
        }
        return toAjax(userService.deleteUserByIds(userIds));
    }

    /**
     * 重置密码
     */
    @ApiOperation(value = "重置密码", nickname = "SysUserPostResetPwd")
    @SaCheckPermission("system:user:resetPwd")
    @Log(title = "用户管理", businessType = BusinessType.UPDATE)
    @PostMapping("/resetPwd")
    public R<Void> resetPwd(@RequestBody SysUser user) {
        userService.checkUserAllowed(user);
        userService.checkUserDataScope(user.getUserId());
        user.setPassword(BCrypt.hashpw(user.getPassword()));
        return toAjax(userService.resetPwd(user));
    }

    /**
     * 状态修改
     */
    @ApiOperation(value = "状态修改", nickname = "SysUserPostChangeStatus")
    @SaCheckPermission("system:user:edit")
    @Log(title = "用户管理", businessType = BusinessType.UPDATE)
    @PostMapping("/changeStatus")
    public R<Void> changeStatus(@RequestBody SysUser user) {
        userService.checkUserAllowed(user);
        userService.checkUserDataScope(user.getUserId());
        return toAjax(userService.updateUserStatus(user));
    }

    /**
     * 根据用户编号获取授权角色
     */
    @ApiOperation(value = "根据用户编号获取授权角色", nickname = "SysUserGetAuthRole")
    @SaCheckPermission("system:user:query")
    @GetMapping("/authRole")
    public R<UserAuthRoleVo> authRole(@ApiParam(value = "用户ID", required = true) @RequestParam Long userId) {
        SysUser user = userService.selectUserById(userId);
        List<SysRole> roles = roleService.selectRolesByUserId(userId);
        UserAuthRoleVo data = new UserAuthRoleVo();
        data.setUser(user);
        data.setRoles(AdminLoginHelper.isAdmin(userId) ? roles : roles.stream().filter(r -> !r.isAdmin()).collect(Collectors.toList()));
        return R.ok(data);
    }

    /**
     * 用户授权角色
     */
    @ApiOperation(value = "用户授权角色", nickname = "SysUserPostInsertAuthRole")
    @SaCheckPermission("system:user:edit")
    @Log(title = "用户管理", businessType = BusinessType.GRANT)
    @PostMapping("/insertAuthRole")
    public R<Void> insertAuthRole(@RequestBody @Validated AuthRoleAllBo body) {
        userService.checkUserDataScope(body.getUserId());
        userService.insertUserAuth(body.getUserId(), body.getRoleIds());
        return R.ok();
    }
}
