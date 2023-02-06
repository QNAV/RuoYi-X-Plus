package com.ruoyi.common.core.domain;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.ArrayList;
import java.util.List;

/**
 * TreeVo基类
 *
 * @author weibocy
 */

@Data
@EqualsAndHashCode(callSuper = true)
public class TreeVo<T> extends BaseVo {

    private static final long serialVersionUID = 1L;

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
     * 子部门
     */
    @Schema(description = "子部门")
    private List<T> children = new ArrayList<>();

}
