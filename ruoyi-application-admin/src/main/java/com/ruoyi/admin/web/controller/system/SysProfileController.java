package com.ruoyi.admin.web.controller.system;

import cn.dev33.satoken.secure.BCrypt;
import cn.hutool.core.io.FileUtil;
import com.ruoyi.admin.controller.AdminBaseController;
import com.ruoyi.admin.helper.AdminLoginHelper;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.constant.UserConstants;
import com.ruoyi.common.core.domain.R;
import com.ruoyi.common.core.domain.bo.UpdatePwdBo;
import com.ruoyi.common.core.domain.entity.SysUser;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.BeanCopyUtils;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.utils.file.MimeTypeUtils;
import com.ruoyi.system.domain.SysOss;
import com.ruoyi.system.domain.bo.LoginUserUpdateBo;
import com.ruoyi.common.core.domain.vo.SysUserVo;
import com.ruoyi.system.service.ISysOssService;
import com.ruoyi.system.service.ISysUserService;
import com.ruoyi.admin.web.model.vo.AvatarUploadVo;
import com.ruoyi.admin.web.model.vo.ProfileVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.Arrays;

/**
 * 个人信息 业务处理
 *
 * @author ruoyi
 * @author Lion Li
 */
@Validated
@Api(value = "个人信息管理", tags = {"SysProfileService"})
@RequiredArgsConstructor
@RestController
@RequestMapping("/system/user/profile")
public class SysProfileController extends AdminBaseController {

    private final ISysUserService userService;
    private final ISysOssService iSysOssService;

    /**
     * 个人信息
     */
    @ApiOperation(value = "个人信息", nickname = "SysProfileGetProfile")
    @GetMapping
    public R<ProfileVo> profile() {
        SysUserVo user = userService.selectUserVoById(getUserId());
        ProfileVo data = new ProfileVo();
        data.setUser(user);
        data.setRoleGroup(userService.selectUserRoleGroup(user.getUserName()));
        data.setPostGroup(userService.selectUserPostGroup(user.getUserName()));
        return R.ok(data);
    }

    /**
     * 修改用户
     */
    @ApiOperation(value = "修改用户", nickname = "SysProfilePostUpdateProfile")
    @Log(title = "个人信息", businessType = BusinessType.UPDATE)
    @PostMapping
    public R<Void> updateProfile(@RequestBody LoginUserUpdateBo userBo) {
        SysUser user = BeanCopyUtils.copy(userBo, SysUser.class);
        if (StringUtils.isNotEmpty(user.getPhoneNumber())
            && UserConstants.NOT_UNIQUE.equals(userService.checkPhoneUnique(user))) {
            return R.fail("修改用户'" + user.getUserName() + "'失败，手机号码已存在");
        }
        if (StringUtils.isNotEmpty(user.getEmail())
            && UserConstants.NOT_UNIQUE.equals(userService.checkEmailUnique(user))) {
            return R.fail("修改用户'" + user.getUserName() + "'失败，邮箱账号已存在");
        }
        user.setUserId(getUserId());
        user.setUserName(null);
        user.setPassword(null);
        if (userService.updateUserProfile(user) > 0) {
            return R.ok();
        }
        return R.fail("修改个人信息异常，请联系管理员");
    }

    /**
     * 重置密码
     */
    @ApiOperation(value = "重置密码", nickname = "SysProfilePostUpdatePwd")
    @Log(title = "个人信息", businessType = BusinessType.UPDATE)
    @PostMapping("/updatePwd")
    public R<Void> updatePwd(@Validated @RequestBody UpdatePwdBo updatePwdBody) {
        SysUser user = userService.selectUserById(AdminLoginHelper.getUserId());
        String userName = user.getUserName();
        String password = user.getPassword();
        if (!BCrypt.checkpw(updatePwdBody.getOldPassword(), password)) {
            return R.fail("修改密码失败，旧密码错误");
        }
        if (BCrypt.checkpw(updatePwdBody.getNewPassword(), password)) {
            return R.fail("新密码不能与旧密码相同");
        }

        if (userService.resetUserPwd(userName, BCrypt.hashpw(updatePwdBody.getNewPassword())) > 0) {
            return R.ok();
        }
        return R.fail("修改密码异常，请联系管理员");
    }

    /**
     * 头像上传
     */
    @ApiOperation(value = "头像上传", nickname = "SysProfilePostAvatar")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "avatarfile", value = "用户头像", paramType = "query", dataTypeClass = File.class, required = true)
    })
    @Log(title = "用户头像", businessType = BusinessType.UPDATE)
    @PostMapping("/avatar")
    public R<AvatarUploadVo> avatar(@RequestPart("avatarfile") MultipartFile file) {
        AvatarUploadVo data = new AvatarUploadVo();
        if (!file.isEmpty()) {
            String extension = FileUtil.extName(file.getOriginalFilename());
            if (!StringUtils.equalsAnyIgnoreCase(extension, MimeTypeUtils.IMAGE_EXTENSION)) {
                return R.fail("文件格式不正确，请上传" + Arrays.toString(MimeTypeUtils.IMAGE_EXTENSION) + "格式");
            }
            SysOss oss = iSysOssService.upload(file);
            String avatar = oss.getUrl();
            if (userService.updateUserAvatar(getUsername(), avatar)) {
                data.setImgUrl(avatar);
                return R.ok(data);
            }
        }
        return R.fail("上传图片异常，请联系管理员");
    }
}
