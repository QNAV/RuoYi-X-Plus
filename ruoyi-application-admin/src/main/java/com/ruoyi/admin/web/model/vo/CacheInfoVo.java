package com.ruoyi.admin.web.model.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.Properties;

/**
 * 缓存监控详细信息返回对象
 * @author weibocy
 */
@Data
@Schema(description = "缓存监控详细信息返回对象")
public class CacheInfoVo implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 缓存服务器原始配置信息
     */
    @Schema(description = "缓存服务器原始配置信息")
    private Properties info;

    /**
     * 数据库大小
     */
    @Schema(description = "数据库大小")
    private String dbSize;

    /**
     * redis命令统计
     */
    @Schema(description = "redis命令统计")
    private List<Map<String, String>> commandStats;

}
