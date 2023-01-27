package com.ruoyi.admin.web.controller.monitor;

import cn.dev33.satoken.annotation.SaCheckPermission;
import cn.hutool.core.collection.CollUtil;
import com.ruoyi.admin.web.model.vo.CacheInfoVo;
import com.ruoyi.common.constant.CacheConstants;
import com.ruoyi.common.core.domain.R;
import com.ruoyi.common.utils.JsonUtils;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.utils.redis.RedisUtils;
import com.ruoyi.system.domain.SysCache;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.redisson.spring.data.connection.RedissonConnectionFactory;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.web.bind.annotation.*;

import java.util.*;

/**
 * 缓存监控
 *
 * @author ruoyi
 * @author Lion Li
 */
@Tag(description = "缓存监控管理", name = "CacheService")
@RequiredArgsConstructor
@RestController
@RequestMapping("/monitor/cache")
public class CacheController {

    private final RedissonConnectionFactory connectionFactory;

    private final static List<SysCache> CACHES = new ArrayList<>();

    static {
        CACHES.add(new SysCache(CacheConstants.ADMIN_LOGIN_TOKEN_KEY, "用户信息"));
        CACHES.add(new SysCache(CacheConstants.ONLINE_ADMIN_TOKEN_KEY, "在线后台用户"));
        CACHES.add(new SysCache(CacheConstants.LOGIN_ERROR, "登陆错误"));
        CACHES.add(new SysCache(CacheConstants.SYS_CONFIG_KEY, "配置信息"));
        CACHES.add(new SysCache(CacheConstants.SYS_DICT_KEY, "数据字典"));
        CACHES.add(new SysCache(CacheConstants.CAPTCHA_CODE_KEY, "验证码"));
        CACHES.add(new SysCache(CacheConstants.REPEAT_SUBMIT_KEY, "防重提交"));
        CACHES.add(new SysCache(CacheConstants.RATE_LIMIT_KEY, "限流处理"));
    }


    @Operation(description = "获取缓存监控详细信息", summary = "CacheGetInfo")
    @SaCheckPermission("monitor:cache:list")
    @GetMapping("/info")
    public R<CacheInfoVo> info() throws Exception {
        RedisConnection connection = connectionFactory.getConnection();
        Properties info = connection.info();
        Properties commandStats = connection.info("commandstats");
        Long dbSize = connection.dbSize();

        CacheInfoVo result = new CacheInfoVo();
        result.setInfo(info);
        result.setDbSize(dbSize.toString());

        List<Map<String, String>> pieList = new ArrayList<>();
        if (commandStats != null) {
            commandStats.stringPropertyNames().forEach(key -> {
                Map<String, String> data = new HashMap<>(2);
                String property = commandStats.getProperty(key);
                data.put("name", StringUtils.removeStart(key, "cmdstat_"));
                data.put("value", StringUtils.substringBetween(property, "calls=", ",usec"));
                pieList.add(data);
            });
        }
        result.setCommandStats(pieList);
        return R.ok(result);
    }


    @Operation(description = "获取缓存名称列表", summary = "CacheGetCacheNames")
    @SaCheckPermission("monitor:cache:list")
    @GetMapping("/cacheNames")
    public R<List<SysCache>> CacheNames() {
        return R.ok(CACHES);
    }

    @Operation(description = "获取KEYS基于缓存名", summary = "CacheGetCacheKeys")
    @SaCheckPermission("monitor:cache:list")
    @GetMapping("/cacheKeys/{cacheName}")
    public R<Collection<String>> cacheKeys(@PathVariable String cacheName) {
        Iterable<String> iterable = RedisUtils.getClient().getKeys().getKeysByPattern(cacheName + "*");
        Collection<String> cacheKyes = CollUtil.toCollection(iterable);
        return R.ok(cacheKyes);
    }

    @Operation(description = "获取值基于缓存名与KEY", summary = "CacheGetCacheValue")
    @SaCheckPermission("monitor:cache:list")
    @GetMapping("/cacheValue/{cacheName}/{cacheKey}")
    public R<SysCache> cacheValue(@PathVariable String cacheName, @PathVariable String cacheKey) {
        Object cacheValue = RedisUtils.getCacheObject(cacheKey);
        SysCache sysCache = new SysCache(cacheName, cacheKey, JsonUtils.toJsonString(cacheValue));
        return R.ok(sysCache);
    }

    @Operation(description = "清空缓存名", summary = "CacheDeleteClearCacheName")
    @SaCheckPermission("monitor:cache:list")
    @DeleteMapping("/clearCacheName/{cacheName}")
    public R<Void> clearCacheName(@PathVariable String cacheName) {
        RedisUtils.getClient().getKeys().deleteByPattern(cacheName + "*");
        return R.ok();
    }

    @Operation(description = "清空缓存KEY", summary = "CacheDeleteClearCacheKey")
    @SaCheckPermission("monitor:cache:list")
    @DeleteMapping("/clearCacheKey/{cacheKey}")
    public R<Void> clearCacheKey(@PathVariable String cacheKey) {
        RedisUtils.deleteObject(cacheKey);
        return R.ok();
    }

    @Operation(description = "清空所有缓存", summary = "CacheDeleteClearCacheAll")
    @SaCheckPermission("monitor:cache:list")
    @DeleteMapping("/clearCacheAll")
    public R<Void> clearCacheAll() {
        RedisUtils.getClient().getKeys().deleteByPattern("*");
        return R.ok();
    }

}
