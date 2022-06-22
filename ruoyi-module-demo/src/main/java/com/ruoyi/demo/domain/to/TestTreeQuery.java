package com.ruoyi.demo.domain.to;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * 测试树表查询对象
 * @author weibocy
 */
@ApiModel("测试树表查询对象")
@Data
public class TestTreeQuery {

    /**
     * 树节点名
     */
    @ApiModelProperty("树节点名")
    private String treeName;

    /**
     * 开始创建时间
     */
    @ApiModelProperty(value = "开始创建时间")
    private Date beginCreateTime;

    /**
     * 结束创建时间
     */
    @ApiModelProperty(value = "结束创建时间")
    private Date endCreateTime;

}
