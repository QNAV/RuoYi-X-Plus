package com.ruoyi.common.core.domain.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 路由配置信息
 *
 * @author weibocy
 */
@Data
@JsonInclude(JsonInclude.Include.NON_EMPTY)
@Schema(description = "路由配置信息")
public class RouterVo implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 路由名字
     */
    @Schema(description = "路由名字", required = true)
    private String name;

    /**
     * 路由地址
     */
    @Schema(description = "路由地址", required = true)
    private String path;

    /**
     * 是否隐藏路由，当设置 true 的时候该路由不会再侧边栏出现
     */
    @Schema(description = "是否隐藏路由，当设置 true 的时候该路由不会再侧边栏出现", required = true)
    private boolean hidden;

    /**
     * 重定向地址，当设置 noRedirect 的时候该路由在面包屑导航中不可被点击
     */
    @Schema(description = "重定向地址，当设置 noRedirect 的时候该路由在面包屑导航中不可被点击")
    private String redirect;

    /**
     * 组件地址
     */
    @Schema(description = "组件地址")
    private String component;

    /**
     * 路由参数：如 {"id": 1, "name": "ry"}
     */
    @Schema(description = "路由参数：如 {\"id\": 1, \"name\": \"ry\"}")
    private String query;

    /**
     * 当你一个路由下面的 children 声明的路由大于1个时，自动会变成嵌套的模式--如组件页面
     */
    @Schema(description = "当你一个路由下面的 children 声明的路由大于1个时，自动会变成嵌套的模式--如组件页面", required = true)
    private Boolean alwaysShow = false;

    /**
     * 其他元素
     */
    @Schema(description = "其他元素")
    private MetaVo meta;

    /**
     * 子路由
     */
    @Schema(description = "子路由")
    private List<RouterVo> children;

}
