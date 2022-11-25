package com.ruoyi.demo.domain.bo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import javax.validation.constraints.*;

import java.util.Date;


/**
 * 测试单业务新增对象 test_demo
 *
 * @author ruoyi
 * @date 2022-10-21
 */

@Data
@ApiModel(value = "TestDemoAddBo", description = "测试单业务新增对象")
public class TestDemoAddBo {

    /**
     * 部门id
     */
    @ApiModelProperty(value = "部门id", required = true)
    @NotNull(message = "部门id不能为空")
    private Long deptId;

    /**
     * 用户id
     */
    @ApiModelProperty(value = "用户id", required = true)
    @NotNull(message = "用户id不能为空")
    private Long userId;

    /**
     * 排序号
     */
    @ApiModelProperty(value = "排序号")
    private Integer orderNum;

    /**
     * key键
     */
    @ApiModelProperty(value = "key键", required = true)
    @NotBlank(message = "key键不能为空")
    private String testKey;

    /**
     * 值
     */
    @ApiModelProperty(value = "值", required = true)
    @NotBlank(message = "值不能为空")
    private String value;


}
