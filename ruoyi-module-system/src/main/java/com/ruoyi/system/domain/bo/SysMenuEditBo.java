package com.ruoyi.system.domain.bo;

import com.ruoyi.common.core.domain.entity.SysMenu;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 菜单权限编辑业务对象
 *
 * @author weibocy
 */

@Data
@Schema(description = "菜单权限编辑业务对象")
public class SysMenuEditBo implements Serializable {

    private static final long serialVersionUID=1L;

    /**
     * 菜单ID
     */
    @Schema(description = "菜单ID", required = true)
    @NotNull(message = "菜单ID不能为空")
    private Long menuId;

    /**
     * 菜单名称
     */
    @Schema(description = "菜单名称")
    @Size(min = 1, max = 50, message = "菜单名称长度不能超过50个字符")
    private String menuName;

    /**
     * 显示顺序
     */
    @Schema(description = "显示顺序")
    private Integer orderNum;

    /**
     * 路由地址
     */
    @Schema(description = "路由地址")
    @Size(min = 0, max = 200, message = "路由地址不能超过200个字符")
    private String path;

    /**
     * 组件路径
     */
    @Schema(description = "组件路径")
    @Size(min = 0, max = 200, message = "组件路径不能超过255个字符")
    private String component;

    /**
     * 路由参数
     */
    @Schema(description = "路由参数")
    private String queryParam;

    /**
     * 是否为外链（0是 1否）
     */
    @Schema(description = "是否为外链（0是 1否）")
    private String isFrame;

    /**
     * 是否缓存（0缓存 1不缓存）
     */
    @Schema(description = "是否缓存（0缓存 1不缓存）")
    private String isCache;

    /**
     * 类型（M目录 C菜单 F按钮）
     */
    @Schema(description = "类型（M目录 C菜单 F按钮）")
    private String menuType;

    /**
     * 显示状态（0显示 1隐藏）
     */
    @Schema(description = "显示状态（0显示 1隐藏）")
    private String visible;

    /**
     * 菜单状态（0显示 1隐藏）
     */
    @Schema(description = "菜单状态（0显示 1隐藏）")
    private String status;

    /**
     * 权限字符串
     */
    @Schema(description = "权限字符串")
    @Size(min = 1, max = 100, message = "权限标识长度不能超过100个字符")
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


    /**
     * 父菜单名称
     */
    @Schema(description = "父菜单名称")
    private String parentName;

    /**
     * 父菜单ID
     */
    @Schema(description = "父菜单ID")
    private Long parentId;

    /**
     * 子菜单
     */
    @Schema(description = "子菜单")
    private List<SysMenu> children = new ArrayList<>();

}
