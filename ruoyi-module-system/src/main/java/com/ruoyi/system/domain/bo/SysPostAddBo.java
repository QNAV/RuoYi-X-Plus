package com.ruoyi.system.domain.bo;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * 岗位信息新增业务对象
 *
 * @author weibocy
 */

@Data
@ApiModel(value = "SysPostAddBo", description = "岗位信息新增业务对象")
public class SysPostAddBo {

    /**
     * 岗位编码
     */
    @ApiModelProperty(value = "岗位编码", required = true)
    @NotBlank(message = "岗位编码不能为空")
    @Size(min = 0, max = 64, message = "岗位编码长度不能超过64个字符")
    private String postCode;

    /**
     * 岗位名称
     */
    @ApiModelProperty(value = "岗位名称", required = true)
    @NotBlank(message = "岗位名称不能为空")
    @Size(min = 1, max = 50, message = "岗位名称长度不能超过50个字符")
    private String postName;

    /**
     * 岗位排序
     */
    @ApiModelProperty(value = "岗位排序", required = true)
    @NotNull(message = "显示顺序不能为空")
    private Integer postSort;

    /**
     * 状态（0正常 1停用）
     */
    @ApiModelProperty(value = "状态（0正常 1停用）", required = true)
    @NotBlank(message = "状态不能为空")
    private String status;

    /**
     * 备注
     */
    @ApiModelProperty(value = "备注")
    private String remark;


}
