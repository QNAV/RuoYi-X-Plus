package com.ruoyi.system.domain.bo;

import com.ruoyi.common.core.domain.bo.PageQuery;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 菜单名称是否唯一分页查询对象
 * @author weibocy
 */
@Data
@ApiModel(value = "SysMenuUniquePageQueryBo", description = "菜单名称是否唯一分页查询对象", parent = PageQuery.class)
public class SysMenuUniquePageQueryBo extends PageQuery {

    private static final long serialVersionUID=1L;

    /**
     * 父菜单ID
     */
    @ApiModelProperty(value = "父菜单ID")
    private Long parentId;


    /**
     * 菜单ID
     */
    @ApiModelProperty(value = "菜单ID")
    private Long menuId;

    /**
     * 菜单名称
     */
    @ApiModelProperty(value = "菜单名称")
    private String menuName;


    /**
     * 路由地址
     */
    @ApiModelProperty(value = "路由地址")
    private String path;


    /**
     * 是否为外链（0是 1否）
     */
    @ApiModelProperty(value = "是否为外链（0是 1否）")
    private String isFrame;


}
