package com.ruoyi.sms.runner;

import com.ruoyi.sms.service.ISysSmsConfigService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Component;

/**
 * 初始化 system 模块对应业务数据
 *
 * @author Lion Li
 */
@Slf4j
@RequiredArgsConstructor
@Component
@DependsOn("flywayInitializer")
public class SmsApplicationRunner implements ApplicationRunner {

    private final ISysSmsConfigService smsConfigService;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        smsConfigService.init();
        log.info("初始化SMS配置成功");
    }

}
