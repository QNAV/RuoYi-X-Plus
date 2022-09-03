package com.ruoyi.common.core.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
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
@ApiModel(description = "TreeVo基类", parent = BaseVo.class)
public class TreeVo<T> extends BaseVo {

    private static final long serialVersionUID = 1L;

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
     * 子部门
     */
    @ApiModelProperty(value = "子部门")
    private List<T> children = new ArrayList<>();

}
