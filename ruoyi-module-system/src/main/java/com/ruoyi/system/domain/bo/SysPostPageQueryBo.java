package com.ruoyi.system.domain.bo;

import com.ruoyi.common.core.domain.bo.PageQuery;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 岗位信息分页查询对象
 * @author weibocy
 */
@ApiModel(value = "SysPostPageQueryBo", description = "岗位信息分页查询对象")
@Data
public class SysPostPageQueryBo extends PageQuery {

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
