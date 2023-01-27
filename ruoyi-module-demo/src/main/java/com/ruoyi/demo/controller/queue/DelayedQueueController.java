package com.ruoyi.demo.controller.queue;

import com.ruoyi.common.core.domain.R;
import com.ruoyi.common.utils.redis.QueueUtils;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

/**
 * 延迟队列 演示案例
 * !!!不推荐模块内写控制器
 * <p>
 * 轻量级队列 重量级数据量 请使用 MQ
 * 例如: 创建订单30分钟后过期处理
 * <p>
 * 集群测试通过 同一个数据只会被消费一次 做好事务补偿
 * 集群测试流程 两台集群分别开启订阅 在其中一台发送数据 观察接收消息的规律
 *
 * @author Lion Li
 */
@Slf4j
@Tag(description = "延迟队列 演示案例", name = "DelayedQueueService")
@RequiredArgsConstructor
@RestController
@RequestMapping("/demo/queue/delayed")
public class DelayedQueueController {

    @Operation(description = "订阅队列", summary = "DelayedQueueServiceGetSubscribe")
    @GetMapping("/subscribe")
    public R<Void> subscribe(@Parameter(description = "队列名") String queueName) {
        log.info("通道: {} 监听中......", queueName);
        // 项目初始化设置一次即可
        QueueUtils.subscribeBlockingQueue(queueName, (String orderNum) -> {
            // 观察接收时间
            log.info("通道: {}, 收到数据: {}", queueName, orderNum);
        });
        return R.ok("操作成功");
    }

    @Operation(description = "添加队列数据", summary = "DelayedQueueServiceGetAdd")
    @GetMapping("/add")
    public R<Void> add(@Parameter(description = "队列名") String queueName,
                                @Parameter(description = "订单号") String orderNum,
                                @Parameter(description = "延迟时间(秒)") Long time) {
        QueueUtils.addDelayedQueueObject(queueName, orderNum, time, TimeUnit.SECONDS);
        // 观察发送时间
        log.info("通道: {} , 发送数据: {}", queueName, orderNum);
        return R.ok("操作成功");
    }

    @Operation(description = "删除队列数据", summary = "DelayedQueueServiceGetRemove")
    @GetMapping("/remove")
    public R<Void> remove(@Parameter(description = "队列名") String queueName,
                                   @Parameter(description = "订单号") String orderNum) {
        if (QueueUtils.removeDelayedQueueObject(queueName, orderNum)) {
            log.info("通道: {} , 删除数据: {}", queueName, orderNum);
        } else {
            return R.fail("操作失败");
        }
        return R.ok("操作成功");
    }

    @Operation(description = "销毁队列", summary = "DelayedQueueServiceGetDestroy")
    @GetMapping("/destroy")
    public R<Void> destroy(@Parameter(description = "队列名") String queueName) {
        // 用完了一定要销毁 否则会一直存在
        QueueUtils.destroyDelayedQueue(queueName);
        return R.ok("操作成功");
    }

}
