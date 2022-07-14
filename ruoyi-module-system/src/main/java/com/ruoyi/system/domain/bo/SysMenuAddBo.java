package com.ruoyi.system.domain.bo;

import com.ruoyi.common.core.domain.entity.SysMenu;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

/**
 * 菜单权限新增业务对象
 *
 * @author weibocy
 */

@Data
@ApiModel(value = "SysMenuAddBo", description = "菜单权限新增业务对象")
public class SysMenuAddBo {

    /**
     * 菜单名称
     */
    @ApiModelProperty(value = "菜单名称", required = true)
    @NotBlank(message = "菜单名称不能为空")
    @Size(min = 0, max = 50, message = "菜单名称长度不能超过50个字符")
    private String menuName;

    /**
     * 显示顺序
     */
    @ApiModelProperty(value = "显示顺序", required = true)
    @NotNull(message = "显示顺序不能为空")
    private Integer orderNum;

    /**
     * 路由地址
     */
    @ApiModelProperty(value = "路由地址")
    @Size(min = 0, max = 200, message = "路由地址不能超过200个字符")
    private String path;

    /**
     * 组件路径
     */
    @ApiModelProperty(value = "组件路径")
    @Size(min = 0, max = 200, message = "组件路径不能超过255个字符")
    private String component;

    /**
     * 路由参数
     */
    @ApiModelProperty(value = "路由参数")
    private String queryParam;

    /**
     * 是否为外链（0是 1否）
     */
    @ApiModelProperty(value = "是否为外链（0是 1否）")
    private String isFrame;

    /**
     * 是否缓存（0缓存 1不缓存）
     */
    @ApiModelProperty(value = "是否缓存（0缓存 1不缓存）")
    private String isCache;

    /**
     * 类型（M目录 C菜单 F按钮）
     */
    @ApiModelProperty(value = "类型（M目录 C菜单 F按钮）", required = true)
    @NotBlank(message = "菜单类型不能为空")
    private String menuType;

    /**
     * 显示状态（0显示 1隐藏）
     */
    @ApiModelProperty(value = "显示状态（0显示 1隐藏）")
    private String visible;

    /**
     * 菜单状态（0显示 1隐藏）
     */
    @ApiModelProperty(value = "菜单状态（0显示 1隐藏）")
    private String status;

    /**
     * 权限字符串
     */
    @ApiModelProperty(value = "权限字符串")
    @Size(min = 0, max = 100, message = "权限标识长度不能超过100个字符")
    private String perms;

    /**
     * 菜单图标
     */
    @ApiModelProperty(value = "菜单图标")
    private String icon;

    /**
     * 备注
     */
    @ApiModelProperty(value = "备注")
    private String remark;


    /**
     * 父菜单名称
     */
    @ApiModelProperty(value = "父菜单名称")
    private String parentName;

    /**
     * 父菜单ID
     */
    @ApiModelProperty(value = "父菜单ID")
    private Long parentId;

    /**
     * 子菜单
     */
    @ApiModelProperty(value = "子菜单")
    private List<SysMenu> children = new ArrayList<>();

}
