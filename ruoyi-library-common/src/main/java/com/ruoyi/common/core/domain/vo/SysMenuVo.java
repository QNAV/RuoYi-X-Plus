package com.ruoyi.common.core.domain.vo;

import com.ruoyi.common.core.domain.TreeVo;
import com.ruoyi.common.enums.CommonNormalDisableEnum;
import com.ruoyi.common.enums.CommonYesOrNoEnum;
import com.ruoyi.common.enums.MenuTypeEnum;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;


/**
 * 菜单权限视图对象
 *
 * @author weibocy
 */

@Data
@EqualsAndHashCode(callSuper = true)
@Schema(description = "菜单权限视图对象")
public class SysMenuVo extends TreeVo<SysMenuVo> {

    private static final long serialVersionUID = 1L;

    /**
     * 菜单ID
     */
    @Schema(description = "菜单ID", required = true)
    private Long menuId;

    /**
     * 菜单名称
     */
    @Schema(description = "菜单名称", required = true)
    private String menuName;

    /**
     * 显示顺序
     */
    @Schema(description = "显示顺序", required = true)
    private Integer orderNum;

    /**
     * 路由地址
     */
    @Schema(description = "路由地址")
    private String path;

    /**
     * 组件路径
     */
    @Schema(description = "组件路径")
    private String component;

    /**
     * 路由参数
     */
    @Schema(description = "路由参数")
    private String queryParam;

    /**
     * 是否为外链（YES=缓存 NO=不缓存）
     */
    @Schema(description = "是否为外链（YES=缓存 NO=不缓存）", required = true)
    private CommonYesOrNoEnum isFrame;

    /**
     * 是否缓存（YES=缓存 NO=不缓存）
     */
    @Schema(description = "是否缓存（YES=缓存 NO=不缓存）", required = true)
    private CommonYesOrNoEnum isCache;

    /**
     * 类型（DIRECTORY=目录 MENU=菜单 BUTTON=按钮）
     */
    @Schema(description = "类型（DIRECTORY=目录 MENU=菜单 BUTTON=按钮）", required = true)
    private MenuTypeEnum menuType;

    /**
     * 显示状态（YES=显示 NO=隐藏）
     */
    @Schema(description = "显示状态（YES=显示 NO=隐藏）", required = true)
    private CommonYesOrNoEnum visible;

    /**
     * 菜单状态（NORMAL=正常 DISABLE=停用）
     */
    @Schema(description = "菜单状态（NORMAL=正常 DISABLE=停用）", required = true)
    private CommonNormalDisableEnum status;

    /**
     * 权限字符串
     */
    @Schema(description = "权限字符串")
    private String perms;

    /**
     * 菜单图标
     */
    @Schema(description = "菜单图标")
    private String icon;

    /**
     * 备注
     */
    @Schema(description = "备注")
    private String remark;

}
