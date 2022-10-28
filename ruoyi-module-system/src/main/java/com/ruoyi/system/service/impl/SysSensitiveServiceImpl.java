package com.ruoyi.system.service.impl;

import com.ruoyi.admin.helper.AdminLoginHelper;
import com.ruoyi.common.core.service.SensitiveService;
import org.springframework.stereotype.Service;

/**
 * 脱敏服务
 * 默认管理员不过滤
 * 需自行根据业务重写实现
 *
 * @author weibocy
 */
@Service
public class SysSensitiveServiceImpl implements SensitiveService {

    /**
     * 是否脱敏
     */
    @Override
    public boolean isSensitive() {
        return !AdminLoginHelper.isAdmin();
    }

}
