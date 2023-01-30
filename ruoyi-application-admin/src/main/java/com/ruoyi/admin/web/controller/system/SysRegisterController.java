package com.ruoyi.admin.web.controller.system;

import cn.dev33.satoken.annotation.SaIgnore;
import com.ruoyi.admin.controller.AdminBaseController;
import com.ruoyi.common.core.domain.R;
import com.ruoyi.common.core.domain.bo.UserNameRegisterBo;
import com.ruoyi.system.service.ISysConfigService;
import com.ruoyi.system.service.SysRegisterService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * 注册验证
 *
 * @author ruoyi
 */
@Validated
@Tag(description = "注册验证管理", name = "SysRegisterService")
@RequiredArgsConstructor
@RestController
public class SysRegisterController extends AdminBaseController {

    private final SysRegisterService registerService;
    private final ISysConfigService configService;

    @SaIgnore
    @Operation(description = "用户注册", summary = "SysRegisterPostRegister")
    @PostMapping("/register")
    public R<Void> register(@Validated @RequestBody UserNameRegisterBo user) {
        if (!("true".equals(configService.selectConfigByKey("sys.account.registerUser")))) {
            return R.fail("当前系统没有开启注册功能！");
        }
        registerService.register(user);
        return R.ok();
    }
}
