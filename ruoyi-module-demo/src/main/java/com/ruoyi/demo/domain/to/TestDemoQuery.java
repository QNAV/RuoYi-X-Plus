package com.ruoyi.demo.domain.to;

import com.ruoyi.common.core.validate.AddGroup;
import com.ruoyi.common.core.validate.EditGroup;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * 测试单表查询对象
 * @author weibocy
 */
@ApiModel("测试单表查询对象")
@Data
public class TestDemoQuery {


    /**
     * key键
     */
    @ApiModelProperty("key键")
    private String testKey;

    /**
     * 值
     */
    @ApiModelProperty("值")
    private String value;


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
