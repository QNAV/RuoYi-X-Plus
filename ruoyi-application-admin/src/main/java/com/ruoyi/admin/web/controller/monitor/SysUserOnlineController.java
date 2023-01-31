package com.ruoyi.admin.web.controller.monitor;

import cn.dev33.satoken.annotation.SaCheckPermission;
import cn.dev33.satoken.exception.NotLoginException;
import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.core.bean.BeanUtil;
import com.ruoyi.admin.controller.AdminBaseController;
import com.ruoyi.admin.domain.bo.AdminUserOnlineBo;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.constant.CacheConstants;
import com.ruoyi.common.constant.CacheNames;
import com.ruoyi.common.core.domain.R;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.StreamUtils;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.utils.redis.CacheUtils;
import com.ruoyi.common.utils.redis.RedisUtils;
import com.ruoyi.system.domain.vo.SysUserOnlineVo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 在线用户监控
 *
 * @author ruoyi
 * @author Lion Li
 */
@Tag(description = "在线用户监控管理", name = "SysUserOnlineService")
@RequiredArgsConstructor
@RestController
@RequestMapping("/monitor/online")
public class SysUserOnlineController extends AdminBaseController {

    /**
     * 获取在线用户监控列表
     * @param ipaddr IP地址
     * @param userName 用户名
     * @return
     */
    @Operation(description = "在线用户列表", summary = "SysUserOnlineGetList")
    @SaCheckPermission("monitor:online:list")
    @GetMapping("/list")
    public TableDataInfo<SysUserOnlineVo> list(@Parameter(description = "ip地址") @RequestParam(required = false) String ipaddr, @Parameter(description = "用户名") @RequestParam(required = false) String userName) {
        // 获取所有未过期的 token
        List<String> keys = StpUtil.searchTokenValue("", 0, -1, false);
        List<AdminUserOnlineBo> userOnlineBoList = new ArrayList<>();
        for (String key : keys) {
            String token = key.replace(CacheConstants.ADMIN_LOGIN_TOKEN_KEY, "");
            // 如果已经过期则跳过
            if (StpUtil.stpLogic.getTokenActivityTimeoutByToken(token) < -1) {
                continue;
            }
            userOnlineBoList.add(RedisUtils.getCacheObject(CacheConstants.ONLINE_ADMIN_TOKEN_KEY + token));
        }
        if (StringUtils.isNotEmpty(ipaddr) && StringUtils.isNotEmpty(userName)) {
            userOnlineBoList = StreamUtils.filter(userOnlineBoList, userOnline ->
                    StringUtils.equals(ipaddr, userOnline.getIpaddr()) &&
                            StringUtils.equals(userName, userOnline.getUserName())
            );
        } else if (StringUtils.isNotEmpty(ipaddr)) {
            userOnlineBoList = StreamUtils.filter(userOnlineBoList, userOnline ->
                    StringUtils.equals(ipaddr, userOnline.getIpaddr())
            );
        } else if (StringUtils.isNotEmpty(userName)) {
            userOnlineBoList = StreamUtils.filter(userOnlineBoList, userOnline ->
                    StringUtils.equals(userName, userOnline.getUserName())
            );
        }
        Collections.reverse(userOnlineBoList);
        userOnlineBoList.removeAll(Collections.singleton(null));
        List<SysUserOnlineVo> userOnlineList = BeanUtil.copyToList(userOnlineBoList, SysUserOnlineVo.class);
        return TableDataInfo.build(userOnlineList);
    }

    /**
     * 强退用户
     *
     * @param tokenId token值
     */
    @Operation(description = "强退用户", summary = "SysUserOnlinePostForceLogout")
    @SaCheckPermission("monitor:online:forceLogout")
    @Log(title = "在线用户", businessType = BusinessType.FORCE)
    @PostMapping("/forceLogout")
    public R<Void> forceLogout(@Parameter(description = "tokenId", required = true) @RequestParam String tokenId) {
        try {
            StpUtil.kickoutByTokenValue(tokenId);
        } catch (NotLoginException ignored) {
        }
        return R.ok();
    }
}
