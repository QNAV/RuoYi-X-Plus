package com.ruoyi.system.domain.bo;

import com.ruoyi.common.core.domain.bo.PageQuery;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * 菜单名称是否唯一分页查询对象
 * @author weibocy
 */
@Data
@Schema(description = "菜单名称是否唯一分页查询对象")
public class SysMenuUniquePageQueryBo extends PageQuery {

    private static final long serialVersionUID=1L;

    /**
     * 父菜单ID
     */
    @Schema(description = "父菜单ID")
    private Long parentId;


    /**
     * 菜单ID
     */
    @Schema(description = "菜单ID")
    private Long menuId;

    /**
     * 菜单名称
     */
    @Schema(description = "菜单名称")
    private String menuName;


    /**
     * 路由地址
     */
    @Schema(description = "路由地址")
    private String path;


    /**
     * 是否为外链（0是 1否）
     */
    @Schema(description = "是否为外链（0是 1否）")
    private String isFrame;


}
