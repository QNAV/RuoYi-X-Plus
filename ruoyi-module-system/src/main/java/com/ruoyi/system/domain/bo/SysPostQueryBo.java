package com.ruoyi.system.domain.bo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * 岗位信息查询对象
 * @author weibocy
 */
@ApiModel(value = "SysPostQueryBo", description = "岗位信息查询对象")
@Data
public class SysPostQueryBo implements Serializable {

    private static final long serialVersionUID=1L;

    /**
     * 岗位编码
     */
    @ApiModelProperty(value = "岗位编码")
    private String postCode;

    /**
     * 岗位名称
     */
    @ApiModelProperty(value = "岗位名称")
    private String postName;


    /**
     * 状态（0正常 1停用）
     */
    @ApiModelProperty(value = "状态（0正常 1停用）")
    private String status;

}
