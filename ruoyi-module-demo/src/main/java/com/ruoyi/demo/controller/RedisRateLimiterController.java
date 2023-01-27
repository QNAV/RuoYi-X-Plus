package com.ruoyi.demo.controller;

import com.ruoyi.common.annotation.RateLimiter;
import com.ruoyi.common.core.domain.R;
import com.ruoyi.common.enums.LimitType;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * 测试分布式限流样例
 * !!!不推荐模块内写控制器
 * @author Lion Li
 */
@Tag(description = "测试分布式限流样例", name = "RedisRateLimiterService")
@Slf4j
@RestController
@RequestMapping("/demo/rateLimiter")
public class RedisRateLimiterController {

    /**
     * 测试全局限流
     * 全局影响
     */
    @Operation(description = "测试全局限流", summary = "RedisRateLimiterServiceGetTest")
    @RateLimiter(count = 2, time = 10)
    @GetMapping("/test")
    public R<String> test(String value) {
        return R.ok("操作成功", value);
    }

    /**
     * 测试请求IP限流
     * 同一IP请求受影响
     */
    @Operation(description = "测试请求IP限流", summary = "RedisRateLimiterServiceGetTestIp")
    @RateLimiter(count = 2, time = 10, limitType = LimitType.IP)
    @GetMapping("/testIp")
    public R<String> testIp(String value) {
        return R.ok("操作成功", value);
    }

    /**
     * 测试集群实例限流
     * 启动两个后端服务互不影响
     */
    @Operation(description = "测试集群实例限流", summary = "RedisRateLimiterServiceGetTestCluster")
    @RateLimiter(count = 2, time = 10, limitType = LimitType.CLUSTER)
    @GetMapping("/testCluster")
    public R<String> testCluster(String value) {
        return R.ok("操作成功", value);
    }

}
