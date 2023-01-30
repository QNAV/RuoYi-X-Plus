package com.ruoyi.admin.web.controller.system;


import cn.dev33.satoken.annotation.SaIgnore;
import com.ruoyi.admin.helper.AdminLoginHelper;
import com.ruoyi.common.core.domain.R;
import com.ruoyi.common.core.domain.bo.UserNameLoginBo;
import com.ruoyi.common.core.domain.entity.SysMenu;
import com.ruoyi.common.core.domain.entity.SysUser;
import com.ruoyi.common.core.domain.bo.SmsLoginBo;
import com.ruoyi.common.core.domain.vo.SysUserVo;
import com.ruoyi.common.core.domain.vo.RouterVo;
import com.ruoyi.common.utils.BeanCopyUtils;
import com.ruoyi.system.service.ISysMenuService;
import com.ruoyi.system.service.ISysUserService;
import com.ruoyi.system.service.SysLoginService;
import com.ruoyi.system.service.SysPermissionService;
import com.ruoyi.admin.web.model.vo.LoginVo;
import com.ruoyi.admin.web.model.vo.UserInfoVo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotBlank;
import java.util.List;
import java.util.Set;

/**
 * 登录验证
 *
 * @author ruoyi
 * @author Lion Li
 */
@Validated
@Tag(description = "登录验证管理", name = "SysLoginService")
@RequiredArgsConstructor
@RestController
public class SysLoginController {

    private final SysLoginService loginService;
    private final ISysMenuService menuService;
    private final ISysUserService userService;
    private final SysPermissionService permissionService;

    /**
     * 用户名登录方法
     *
     * @param userNameLoginBody 用户名登录信息
     * @return 结果
     */
    @SaIgnore
    @Operation(description = "用户名登录方法", summary = "SysLoginPostLogin")
    @PostMapping("/login")
    public R<LoginVo> login(@Validated @RequestBody UserNameLoginBo userNameLoginBody) {
        LoginVo result = new LoginVo();
        // 生成令牌
        String token = loginService.login(userNameLoginBody.getUsername(), userNameLoginBody.getPassword(), userNameLoginBody.getCode(),
            userNameLoginBody.getUuid());
        result.setToken(token);
        return R.ok(result);
    }

    /**
     * 短信登录(示例)
     *
     * @param smsLoginBody 登录信息
     * @return 结果
     */
    @SaIgnore
    @Operation(description = "短信登录(示例)", summary = "SysLoginPostSmsLogin")
    @PostMapping("/smsLogin")
    public R<LoginVo> smsLogin(@Validated @RequestBody SmsLoginBo smsLoginBody) {
        LoginVo result = new LoginVo();
        // 生成令牌
        String token = loginService.smsLogin(smsLoginBody.getPhoneNumber(), smsLoginBody.getSmsCode());
        result.setToken(token);
        return R.ok(result);
    }

    /**
     * 小程序登录(示例)
     *
     * @param xcxCode 小程序code
     * @return 结果
     */
    @SaIgnore
    @Operation(description = "小程序登录(示例)", summary = "SysLoginGetXcxLogin")
    @GetMapping("/xcxLogin")
    public R<LoginVo> xcxLogin(@NotBlank(message = "{xcx.code.not.blank}") @Parameter(description = "小程序code", required = true) @RequestParam String xcxCode) {
        LoginVo result = new LoginVo();
        // 生成令牌
        String token = loginService.xcxLogin(xcxCode);
        result.setToken(token);
        return R.ok(result);
    }

    @SaIgnore
    @Operation(description = "登出方法", summary = "SysLoginPostLogout")
    @PostMapping("/logout")
    public R<Void> logout() {
        loginService.logout();
        return R.ok("退出成功");
    }

    /**
     * 获取已登录用户信息
     *
     * @return 用户信息
     */
    @Operation(description = "获取已登录用户信息", summary = "SysLoginGetInfo")
    @GetMapping("/info")
    public R<UserInfoVo> info() {
        UserInfoVo data = new UserInfoVo();
        SysUserVo userVo = userService.selectUserVoById(AdminLoginHelper.getUserId());
        SysUser user = BeanCopyUtils.copy(userVo, SysUser.class);
        // 角色集合
        Set<String> roles = permissionService.getRolePermission(user);
        // 权限集合
        Set<String> permissions = permissionService.getMenuPermission(user);
        data.setUser(userVo);
        data.setRoles(roles);
        data.setPermissions(permissions);
        return R.ok(data);
    }

    /**
     * 获取菜单路由信息
     *
     * @return 路由信息
     */
    @Operation(description = "获取菜单路由信息", summary = "SysLoginGetRouters")
    @GetMapping("/routers")
    public R<List<RouterVo>> routers() {
        Long userId = AdminLoginHelper.getUserId();
        List<SysMenu> menus = menuService.selectMenuTreeByUserId(userId);
        return R.ok(menuService.buildMenus(menus));
    }
}
