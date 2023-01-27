package com.ruoyi.demo.controller;

import com.ruoyi.common.core.domain.R;
import com.ruoyi.common.utils.redis.RedisUtils;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Redis 发布订阅 演示案例
 * !!!不推荐模块内写控制器
 * @author Lion Li
 */
@Tag(description = "Redis发布订阅 演示案例", name = "RedisPubSubService")
@RequiredArgsConstructor
@RestController
@RequestMapping("/demo/redis/pubsub")
public class RedisPubSubController {

    @Operation(description = "发布消息", summary = "RedisPubSubServiceGetPub")
    @GetMapping("/pub")
    public R<Void> pub(@Parameter(description = "通道Key") String key, @Parameter(description = "发送内容") String value) {
        RedisUtils.publish(key, value, consumer -> {
            System.out.println("发布通道 => " + key + ", 发送值 => " + value);
        });
        return R.ok("操作成功");
    }

    @Operation(description = "订阅消息", summary = "RedisPubSubServiceGetSub")
    @GetMapping("/sub")
    public R<Void> sub(@Parameter(description = "通道Key") String key) {
        RedisUtils.subscribe(key, String.class, msg -> {
            System.out.println("订阅通道 => " + key + ", 接收值 => " + msg);
        });
        return R.ok("操作成功");
    }

}
