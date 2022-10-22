package com.ruoyi.demo.domain.bo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import javax.validation.constraints.*;

import java.util.Date;


/**
 * 测试单业务查询对象 test_demo
 *
 * @author ruoyi
 * @date 2022-10-21
 */

@Data
@ApiModel(value = "TestDemoQueryBo", description = "测试单业务查询对象")
public class TestDemoQueryBo {

    /**
     * 部门id
     */
    @ApiModelProperty(value = "部门id")
    private Long deptId;

    /**
     * 用户id
     */
    @ApiModelProperty(value = "用户id")
    private Long userId;

    /**
     * 排序号
     */
    @ApiModelProperty(value = "排序号")
    private Integer orderNum;

    /**
     * key键
     */
    @ApiModelProperty(value = "key键")
    private String testKey;

    /**
     * 值
     */
    @ApiModelProperty(value = "值")
    private String value;

    /**
     * 创建开始时间
     */
    @ApiModelProperty(value = "创建开始时间")
    private Date createBeginTime;

    /**
     * 创建结束时间
     */
    @ApiModelProperty(value = "创建结束时间")
    private Date createEndTime;
}
