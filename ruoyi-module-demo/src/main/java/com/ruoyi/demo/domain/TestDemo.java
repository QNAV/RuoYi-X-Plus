package com.ruoyi.demo.domain;

import com.baomidou.mybatisplus.annotation.*;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import java.io.Serializable;
import java.util.Date;
import java.math.BigDecimal;

import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 测试单对象 test_demo
 *
 * @author ruoyi
 * @date 2022-10-20
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("test_demo")
@ApiModel(value = "TestDemo", description = "测试单对象", parent = BaseEntity.class)
public class TestDemo extends BaseEntity {

    private static final long serialVersionUID=1L;

    /**
     * 主键
     */
    @TableId(value = "id")
    @ApiModelProperty(value = "主键")
    private Long id;

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
     * 版本
     */
    @Version
    @ApiModelProperty(value = "版本")
    private Integer version;

    /**
     * 删除标志
     */
    @TableLogic
    @ApiModelProperty(value = "删除标志")
    private Integer delFlag;


}
