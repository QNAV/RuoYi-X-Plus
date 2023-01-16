package com.ruoyi.demo.domain.bo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.Date;


/**
 * 测试单业务查询对象 test_demo
 *
 * @author ruoyi
 * @date 2022-10-21
 */

@Data
@Schema(description = "测试单业务查询对象")
public class TestDemoQueryBo {

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
     * 创建开始时间
     */
    @Schema(description = "创建开始时间")
    private Date createBeginTime;

    /**
     * 创建结束时间
     */
    @Schema(description = "创建结束时间")
    private Date createEndTime;
}
