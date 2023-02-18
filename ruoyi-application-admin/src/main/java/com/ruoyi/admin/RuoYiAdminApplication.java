package com.ruoyi.admin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.metrics.buffering.BufferingApplicationStartup;

import javax.annotation.PostConstruct;
import java.util.TimeZone;

/**
 * 启动程序
 *
 * @author ruoyi
 * @author weibocy
 */

@SpringBootApplication(scanBasePackages = {"com.ruoyi"})
public class RuoYiAdminApplication {

    public static void main(String[] args) {
        System.setProperty("spring.devtools.restart.enabled", "false");
        SpringApplication application = new SpringApplication(RuoYiAdminApplication.class);
        application.setApplicationStartup(new BufferingApplicationStartup(2048));
        application.run(args);
        System.out.println("(♥◠‿◠)ﾉﾞ  RuoYi-X-Plus启动成功   ლ(´ڡ`ლ)ﾞ");
        // 统一时区
        TimeZone.setDefault(TimeZone.getTimeZone("GMT+08"));
    }

    /**
     * 统一时区
     */
    @PostConstruct
    void setDefaultTimezone() {
        TimeZone.setDefault(TimeZone.getTimeZone("GMT+08"));
    }

}
