package com.ruoyi.wx.runner;

import com.ruoyi.wx.service.ISysWxConfigService;
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
public class WxApplicationRunner implements ApplicationRunner {

    private final ISysWxConfigService wxConfigService;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        wxConfigService.init();
        log.info("初始化WX配置成功");
    }

}
