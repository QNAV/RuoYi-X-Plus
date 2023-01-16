package com.ruoyi.demo.domain;

import com.baomidou.mybatisplus.annotation.*;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

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
@Schema(description = "测试单对象")
public class TestDemo extends BaseEntity {

    private static final long serialVersionUID=1L;

    /**
     * 主键
     */
    @TableId(value = "id")
    @Schema(description = "主键")
    private Long id;

    /**
     * 部门id
     */
    @Schema(description = "部门id")
    private Long deptId;

    /**
     * 用户id
     */
    @Schema(description = "用户id")
    private Long userId;

    /**
     * 排序号
     */
    @Schema(description = "排序号")
    private Integer orderNum;

    /**
     * key键
     */
    @Schema(description = "key键")
    private String testKey;

    /**
     * 值
     */
    @Schema(description = "值")
    private String value;

    /**
     * 版本
     */
    @Version
    @Schema(description = "版本")
    private Integer version;

    /**
     * 删除标志
     */
    @TableLogic
    @Schema(description = "删除标志")
    private Integer delFlag;


}
