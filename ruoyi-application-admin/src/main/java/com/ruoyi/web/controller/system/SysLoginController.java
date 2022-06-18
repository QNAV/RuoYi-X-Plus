package com.ruoyi.web.controller.system;

import cn.dev33.satoken.exception.NotLoginException;
import cn.dev33.satoken.stp.StpUtil;
import com.ruoyi.common.annotation.Anonymous;
import com.ruoyi.common.core.domain.R;
import com.ruoyi.common.core.domain.entity.SysMenu;
import com.ruoyi.common.core.domain.entity.SysUser;
import com.ruoyi.common.core.domain.model.LoginBody;
import com.ruoyi.common.core.domain.model.SmsLoginBody;
import com.ruoyi.common.helper.LoginHelper;
import com.ruoyi.system.domain.vo.RouterVo;
import com.ruoyi.system.service.ISysMenuService;
import com.ruoyi.system.service.ISysUserService;
import com.ruoyi.system.service.SysLoginService;
import com.ruoyi.system.service.SysPermissionService;
import com.ruoyi.web.model.dto.LoginDTO;
import com.ruoyi.web.model.dto.UserInfoDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotBlank;
import java.util.List;
import java.util.Set;

/**
 * 登录验证
 *
 * @author weibocy
 */
@Validated
@Api(value = "登录验证控制器", tags = {"登录验证管理"})
@RequiredArgsConstructor
@RestController
public class SysLoginController {

    private final SysLoginService loginService;
    private final ISysMenuService menuService;
    private final ISysUserService userService;
    private final SysPermissionService permissionService;

    /**
     * 登录方法
     *
     * @param loginBody 登录信息
     * @return 结果
     */
    @Anonymous
    @ApiOperation("登录方法")
    @PostMapping("/login")
    public R<LoginDTO> login(@Validated @RequestBody LoginBody loginBody) {
        LoginDTO result = new LoginDTO();
        // 生成令牌
        String token = loginService.login(loginBody.getUsername(), loginBody.getPassword(), loginBody.getCode(),
            loginBody.getUuid());
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
    @ApiOperation("短信登录(示例)")
    @PostMapping("/smsLogin")
    public R<LoginDTO> smsLogin(@Validated @RequestBody SmsLoginBody smsLoginBody) {
        LoginDTO result = new LoginDTO();
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
    @ApiOperation("小程序登录(示例)")
    @PostMapping("/xcxLogin")
    public R<LoginDTO> xcxLogin(@NotBlank(message = "{xcx.code.not.blank}") String xcxCode) {
        LoginDTO result = new LoginDTO();
        // 生成令牌
        String token = loginService.xcxLogin(xcxCode);
        result.setToken(token);
        return R.ok(result);
    }

    @Anonymous
    @ApiOperation("登出方法")
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
     * 获取用户信息
     *
     * @return 用户信息
     */
    @ApiOperation("获取用户信息")
    @GetMapping("getInfo")
    public R<UserInfoDTO> getInfo() {
        UserInfoDTO data = new UserInfoDTO();
        SysUser user = userService.selectUserById(LoginHelper.getUserId());
        // 角色集合
        Set<String> roles = permissionService.getRolePermission(user);
        // 权限集合
        Set<String> permissions = permissionService.getMenuPermission(user);
        data.setUser(user);
        data.setRoles(roles);
        data.setPermissions(permissions);
        return R.ok(data);
    }

    /**
     * 获取路由信息
     *
     * @return 路由信息
     */
    @ApiOperation("获取路由信息")
    @GetMapping("getRouters")
    public R<List<RouterVo>> getRouters() {
        Long userId = LoginHelper.getUserId();
        List<SysMenu> menus = menuService.selectMenuTreeByUserId(userId);
        return R.ok(menuService.buildMenus(menus));
    }
}
