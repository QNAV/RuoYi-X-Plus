package com.ruoyi.web.controller.system;

import cn.dev33.satoken.annotation.SaCheckPermission;
import cn.dev33.satoken.secure.BCrypt;
import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.ArrayUtil;
import cn.hutool.core.util.ObjectUtil;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.constant.UserConstants;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.PageQuery;
import com.ruoyi.common.core.domain.R;
import com.ruoyi.common.core.domain.entity.SysDept;
import com.ruoyi.common.core.domain.entity.SysRole;
import com.ruoyi.common.core.domain.entity.SysUser;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.excel.ExcelResult;
import com.ruoyi.common.helper.LoginHelper;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.system.domain.to.AuthRoleAllBody;
import com.ruoyi.system.domain.to.SysUserQuery;
import com.ruoyi.system.domain.vo.SysUserExportVo;
import com.ruoyi.system.domain.vo.SysUserImportVo;
import com.ruoyi.system.listener.SysUserImportListener;
import com.ruoyi.system.service.ISysPostService;
import com.ruoyi.system.service.ISysRoleService;
import com.ruoyi.system.service.ISysUserService;
import com.ruoyi.web.model.dto.UserAuthRoleDTO;
import com.ruoyi.web.model.dto.UserDetailDTO;
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
public class SysUserController extends BaseController {

    private final ISysUserService userService;
    private final ISysRoleService roleService;
    private final ISysPostService postService;

    /**
     * 获取用户列表
     */
    @ApiOperation(value = "获取用户列表", nickname = "SysUserPostList")
    @SaCheckPermission("system:user:list")
    @PostMapping("/list")
    public TableDataInfo<SysUser> list(@RequestBody(required = false) SysUserQuery userQuery,
                                       @ApiParam(value = "当前页数", defaultValue = "1") @RequestParam(required = false) Integer pageNum,
                                       @ApiParam(value = "分页大小", defaultValue = "10") @RequestParam(required = false) Integer pageSize,
                                       @ApiParam("排序列") @RequestParam(required = false) String orderByColumn,
                                       @ApiParam(value = "排序的方向", example = "asc,desc") @RequestParam(required = false) String isAsc) {
        PageQuery pageQuery = new PageQuery();
        pageQuery.setPageNum(pageNum);
        pageQuery.setPageSize(pageSize);
        pageQuery.setOrderByColumn(orderByColumn);
        pageQuery.setIsAsc(isAsc);
        return userService.selectPageUserList(userQuery, pageQuery);
    }

    @ApiOperation(value = "导出用户列表", nickname = "SysUserPostExport")
    @Log(title = "用户管理", businessType = BusinessType.EXPORT)
    @SaCheckPermission("system:user:export")
    @PostMapping("/export")
    public void export(@RequestBody(required = false) SysUserQuery userQuery, @ApiParam(hidden = true) HttpServletResponse response) {
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
    @GetMapping(value = {"/info"})
    public R<UserDetailDTO> info(@ApiParam(value = "用户ID",required = true) @RequestParam Long userId) {
        userService.checkUserDataScope(userId);
        UserDetailDTO data = new UserDetailDTO();
        List<SysRole> roles = roleService.selectRoleAll();
        data.setRoles(LoginHelper.isAdmin(userId) ? roles : roles.stream().filter(r -> !r.isAdmin()).collect(Collectors.toList()));
        data.setPosts(postService.selectPostAll());
        if (ObjectUtil.isNotNull(userId)) {
            SysUser sysUser = userService.selectUserById(userId);
            data.setUser(sysUser);
            data.setPostIds(postService.selectPostListByUserId(userId));
            data.setRoleIds(sysUser.getRoles().stream().map(SysRole::getRoleId).collect(Collectors.toList()));
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
    public R<Void> add(@Validated @RequestBody SysUser user) {
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
    public R<Void> edit(@Validated @RequestBody SysUser user) {
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
    public R<UserAuthRoleDTO> authRole(@ApiParam(value = "用户ID", required = true) @RequestParam Long userId) {
        SysUser user = userService.selectUserById(userId);
        List<SysRole> roles = roleService.selectRolesByUserId(userId);
        UserAuthRoleDTO data = new UserAuthRoleDTO();
        data.setUser(user);
        data.setRoles(LoginHelper.isAdmin(userId) ? roles : roles.stream().filter(r -> !r.isAdmin()).collect(Collectors.toList()));
        return R.ok(data);
    }

    /**
     * 用户授权角色
     */
    @ApiOperation(value = "用户授权角色", nickname = "SysUserPostInsertAuthRole")
    @SaCheckPermission("system:user:edit")
    @Log(title = "用户管理", businessType = BusinessType.GRANT)
    @PostMapping("/insertAuthRole")
    public R<Void> insertAuthRole(@RequestBody @Validated AuthRoleAllBody body) {
        userService.checkUserDataScope(body.getUserId());
        userService.insertUserAuth(body.getUserId(), body.getRoleIds());
        return R.ok();
    }
}
