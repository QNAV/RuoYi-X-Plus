package com.ruoyi.common.core.domain.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.ruoyi.common.core.domain.TreeEntity;
import com.ruoyi.common.enums.CommonNormalDisableEnum;
import com.ruoyi.common.enums.CommonYesOrNoEnum;
import com.ruoyi.common.enums.MenuTypeEnum;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * 菜单权限表 sys_menu
 *
 * @author ruoyi
 * @author Lion Li
 */

@Data
@EqualsAndHashCode(callSuper = true)
@TableName("sys_menu")
@Schema(description = "菜单权限业务对象")
public class SysMenu extends TreeEntity<SysMenu> {

    private static final long serialVersionUID = 1L;

    /**
     * 菜单ID
     */
    @Schema(description = "菜单ID")
    @TableId(value = "menu_id")
    private Long menuId;

    /**
     * 菜单名称
     */
    @Schema(description = "菜单名称", required = true)
    @NotBlank(message = "菜单名称不能为空")
    @Size(min = 0, max = 50, message = "菜单名称长度不能超过{max}个字符")
    private String menuName;

    /**
     * 显示顺序
     */
    @Schema(description = "显示顺序", required = true)
    @NotNull(message = "显示顺序不能为空")
    private Integer orderNum;

    /**
     * 路由地址
     */
    @Schema(description = "路由地址")
    @Size(min = 0, max = 200, message = "路由地址不能超过{max}个字符")
    private String path;

    /**
     * 组件路径
     */
    @Schema(description = "组件路径")
    @Size(min = 0, max = 200, message = "组件路径不能超过{max}个字符")
    private String component;

    /**
     * 路由参数
     */
    @Schema(description = "路由参数")
    private String queryParam;

    /**
     * 是否为外链（YES=是 NO=否）
     */
    @Schema(description = "是否为外链（YES=是 NO=否）")
    private CommonYesOrNoEnum isFrame;

    /**
     * 是否缓存（YES=缓存 NO=不缓存）
     */
    @Schema(description = "是否缓存（YES=缓存 NO=不缓存）")
    private CommonYesOrNoEnum isCache;

    /**
     * 类型（DIRECTORY=目录 MENU=菜单 BUTTON=按钮）
     */
    @Schema(description = "类型（DIRECTORY=目录 MENU=菜单 BUTTON=按钮）", required = true)
    @NotNull(message = "菜单类型不能为空")
    private MenuTypeEnum menuType;

    /**
     * 显示状态（YES=显示 NO=隐藏）
     */
    @Schema(description = "显示状态（YES=显示 NO=隐藏）")
    private CommonYesOrNoEnum visible;

    /**
     * 菜单状态（NORMAL=正常 DISABLE=停用）
     */
    @Schema(description = "菜单状态（NORMAL=正常 DISABLE=停用）")
    private CommonNormalDisableEnum status;

    /**
     * 权限字符串
     */
    @Schema(description = "权限字符串")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @Size(min = 0, max = 100, message = "权限标识长度不能超过{max}个字符")
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
