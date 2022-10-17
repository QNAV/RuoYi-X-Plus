package com.ruoyi.system.domain.vo;

import com.ruoyi.common.core.domain.BaseVo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;


/**
 * 岗位信息视图对象
 *
 * @author weibocy
 */

@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "SysPostVo", description = "岗位信息视图对象", parent = BaseVo.class)
public class SysPostVo extends BaseVo {

    /**
     * 岗位序号
     */
    @ApiModelProperty(value = "岗位序号", required = true)
    private Long postId;

    /**
     * 岗位编码
     */
    @ApiModelProperty(value = "岗位编码", required = true)
    private String postCode;

    /**
     * 岗位名称
     */
    @ApiModelProperty(value = "岗位名称", required = true)
    private String postName;

    /**
     * 岗位排序
     */
    @ApiModelProperty(value = "岗位排序", required = true)
    private Integer postSort;

    /**
     * 状态（0正常 1停用）
     */
    @ApiModelProperty(value = "状态（0正常 1停用）", required = true)
    private String status;

    /**
     * 备注
     */
    @ApiModelProperty(value = "备注")
    private String remark;

    /**
     * 用户是否存在此岗位标识 默认不存在
     */
    @ApiModelProperty(value = "用户是否存在此岗位标识 默认不存在", required = true)
    private boolean flag = false;

}
