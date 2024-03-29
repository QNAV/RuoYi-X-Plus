package com.ruoyi.common.core.domain.vo;

import com.ruoyi.common.utils.StringUtils;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;

/**
 * 路由显示信息视图对象
 *
 * @author weibocy
 */

@Data
@Schema(description = "路由显示信息视图对象")
public class MetaVo implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 设置该路由在侧边栏和面包屑中展示的名字
     */
    @Schema(description = "设置该路由在侧边栏和面包屑中展示的名字", required = true)
    private String title;

    /**
     * 设置该路由的图标，对应路径src/assets/icons/svg
     */
    @Schema(description = "设置该路由的图标，对应路径src/assets/icons/svg")
    private String icon;

    /**
     * 设置为true，则不会被 <keep-alive>缓存
     */
    @Schema(description = "设置为true，则不会被 <keep-alive>缓存")
    private boolean noCache;

    /**
     * 内链地址（http(s)://开头）
     */
    @Schema(description = "内链地址（http(s)://开头）")
    private String link;

    public MetaVo(String title, String icon) {
        this.title = title;
        this.icon = icon;
    }

    public MetaVo(String title, String icon, boolean noCache) {
        this.title = title;
        this.icon = icon;
        this.noCache = noCache;
    }

    public MetaVo(String title, String icon, String link) {
        this.title = title;
        this.icon = icon;
        this.link = link;
    }

    public MetaVo(String title, String icon, boolean noCache, String link) {
        this.title = title;
        this.icon = icon;
        this.noCache = noCache;
        if (StringUtils.ishttp(link)) {
            this.link = link;
        }
    }

}
