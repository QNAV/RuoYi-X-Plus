package com.ruoyi.web.controller.system;

import cn.dev33.satoken.exception.NotLoginException;
import cn.dev33.satoken.stp.StpUtil;
import com.ruoyi.common.annotation.Anonymous;
import com.ruoyi.common.core.domain.R;
import com.ruoyi.common.core.domain.bo.UserNameLoginBo;
import com.ruoyi.common.core.domain.entity.SysMenu;
import com.ruoyi.common.core.domain.entity.SysUser;
import com.ruoyi.common.core.domain.bo.SmsLoginBo;
import com.ruoyi.common.core.domain.vo.SysUserVo;
import com.ruoyi.common.helper.LoginHelper;
import com.ruoyi.common.core.domain.vo.RouterVo;
import com.ruoyi.common.utils.BeanCopyUtils;
import com.ruoyi.system.service.ISysMenuService;
import com.ruoyi.system.service.ISysUserService;
import com.ruoyi.system.service.SysLoginService;
import com.ruoyi.system.service.SysPermissionService;
import com.ruoyi.web.model.vo.LoginVo;
import com.ruoyi.web.model.vo.UserInfoVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotBlank;
import java.util.List;
import java.util.Set;

/**
 * 登录验证
 *
 * @author weibocy
 */
@Validated
@Api(value = "登录验证管理", tags = {"SysLoginService"})
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
    @Anonymous
    @ApiOperation(value = "用户名登录方法", nickname = "SysLoginPostLogin")
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
    @Anonymous
    @ApiOperation(value = "短信登录(示例)", nickname = "SysLoginPostSmsLogin")
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
    @Anonymous
    @ApiOperation(value = "小程序登录(示例)", nickname = "SysLoginGetXcxLogin")
    @GetMapping("/xcxLogin")
    public R<LoginVo> xcxLogin(@NotBlank(message = "{xcx.code.not.blank}") @ApiParam(value = "小程序code", required = true) @RequestParam String xcxCode) {
        LoginVo result = new LoginVo();
        // 生成令牌
        String token = loginService.xcxLogin(xcxCode);
        result.setToken(token);
        return R.ok(result);
    }

    @Anonymous
    @ApiOperation(value = "登出方法", nickname = "SysLoginPostLogout")
    @PostMapping("/logout")
    public R<Void> logout() {
        try {
            String username = LoginHelper.getUsername();
            StpUtil.logout();
            loginService.logout(username);
        } catch (NotLoginException e) {
        }
        return R.ok("退出成功");
    }

    /**
     * 获取已登录用户信息
     *
     * @return 用户信息
     */
    @ApiOperation(value = "获取已登录用户信息", nickname = "SysLoginGetInfo")
    @GetMapping("/info")
    public R<UserInfoVo> info() {
        UserInfoVo data = new UserInfoVo();
        SysUserVo userVo = userService.selectUserVoById(LoginHelper.getUserId());
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
    @ApiOperation(value = "获取菜单路由信息", nickname = "SysLoginGetRouters")
    @GetMapping("/routers")
    public R<List<RouterVo>> routers() {
        Long userId = LoginHelper.getUserId();
        List<SysMenu> menus = menuService.selectMenuTreeByUserId(userId);
        return R.ok(menuService.buildMenus(menus));
    }
}
