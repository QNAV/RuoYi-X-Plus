package com.ruoyi.web.model.dto;

import com.ruoyi.web.model.RedisServerInfo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;
import java.util.Map;
import java.util.Properties;

/**
 * 缓存监控详细信息返回对象
 * @author weibocy
 */
@Data
@ApiModel(value = "CacheInfoDTO", description = "缓存监控详细信息返回对象")
public class CacheInfoDTO {

    /**
     * 缓存服务器原始配置信息
     */
    @ApiModelProperty(value = "缓存服务器原始配置信息")
    private Properties info;

    /**
     * 数据库大小
     */
    @ApiModelProperty(value = "数据库大小")
    private String dbSize;

    /**
     * redis命令统计
     */
    @ApiModelProperty(value = "redis命令统计")
    private List<Map<String, String>> commandStats;

}
