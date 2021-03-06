package com.ruoyi.common.core.service;

import com.ruoyi.common.core.domain.bo.OperLogBo;
import org.springframework.scheduling.annotation.Async;

/**
 * 通用 操作日志
 *
 * @author weibocy
 */
public interface OperLogService {

    @Async
    void recordOper(OperLogBo operLog);
}
