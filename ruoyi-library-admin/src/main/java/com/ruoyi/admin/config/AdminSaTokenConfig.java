package com.ruoyi.admin.config;

import cn.dev33.satoken.dao.SaTokenDao;
import cn.dev33.satoken.interceptor.SaInterceptor;
import cn.dev33.satoken.jwt.StpLogicJwtForSimple;
import cn.dev33.satoken.router.SaRouter;
import cn.dev33.satoken.stp.StpInterface;
import cn.dev33.satoken.stp.StpLogic;
import cn.dev33.satoken.stp.StpUtil;
import com.ruoyi.admin.satoken.AdminSaTokenDao;
import com.ruoyi.admin.satoken.SecurityProperties;
import com.ruoyi.admin.service.AdminSaPermissionImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 后台 sa-token 配置
 *
 * @author Lion Li
 */
@RequiredArgsConstructor
@Slf4j
@Configuration
public class AdminSaTokenConfig implements WebMvcConfigurer {

    private final SecurityProperties securityProperties;

    /**
     * 注册sa-token的拦截器
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 注册路由拦截器，自定义验证规则
        registry.addInterceptor(new SaInterceptor(handler -> {
            // 登录验证 -- 排除多个路径
            SaRouter
                // 获取所有的
                .match("/**")
                // 对未排除的路径进行检查
                .check(() -> {
                    // 检查是否登录 是否有token
                    StpUtil.checkLogin();

                    // 有效率影响 用于临时测试
                    // if (log.isDebugEnabled()) {
                    //     log.debug("剩余有效时间: {}", StpUtil.getTokenTimeout());
                    //     log.debug("临时有效时间: {}", StpUtil.getTokenActivityTimeout());
                    // }

                });
        })).addPathPatterns("/**")
            // 排除不需要拦截的路径
            .excludePathPatterns(securityProperties.getExcludes());
    }

    @Bean
    public StpLogic getStpLogicJwt() {
        // Sa-Token 整合 jwt (简单模式)
        return new StpLogicJwtForSimple();
    }

    /**
     * 权限接口实现(使用bean注入方便用户替换)
     */
    @Bean
    public StpInterface stpInterface() {
        return new AdminSaPermissionImpl();
    }

    /**
     * 自定义dao层存储
     */
    @Bean
    public SaTokenDao saTokenDao() {
        return new AdminSaTokenDao();
    }

}
