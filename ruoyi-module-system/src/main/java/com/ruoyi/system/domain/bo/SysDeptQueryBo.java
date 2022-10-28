package com.ruoyi.system.domain.bo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * 部门查询对象
 * @author weibocy
 */
@Data
@ApiModel(value = "SysDeptQueryBo", description = "部门查询对象")
public class SysDeptQueryBo implements Serializable {

    private static final long serialVersionUID=1L;

    /**
     * 部门ID
     */
    @ApiModelProperty(value = "部门id")
    private Long deptId;

    /**
     * 部门名称
     */
    @ApiModelProperty(value = "部门名称")
    private String deptName;


    /**
     * 父菜单ID
     */
    @ApiModelProperty(value = "父菜单ID")
    private Long parentId;


    /**
     * 部门状态:0正常,1停用
     */
    @ApiModelProperty(value = "部门状态:0正常,1停用")
    private String status;


}
