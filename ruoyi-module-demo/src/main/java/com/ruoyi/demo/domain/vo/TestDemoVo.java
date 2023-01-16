package com.ruoyi.demo.domain.vo;

import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;
import com.ruoyi.common.core.domain.BaseVo;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import java.util.Date;



/**
 * 测试单视图对象 test_demo
 *
 * @author ruoyi
 * @date 2022-10-21
 */
@Data
@Schema(description = "测试单视图对象")
@ExcelIgnoreUnannotated
public class TestDemoVo extends BaseVo {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @ExcelProperty(value = "主键")
    @Schema(description = "主键", required = true)
    private Long id;

    /**
     * 部门id
     */
    @ExcelProperty(value = "部门id")
    @Schema(description = "部门id", required = true)
    private Long deptId;

    /**
     * 用户id
     */
    @ExcelProperty(value = "用户id")
    @Schema(description = "用户id", required = true)
    private Long userId;

    /**
     * 排序号
     */
    @ExcelProperty(value = "排序号")
    @Schema(description = "排序号")
    private Integer orderNum;

    /**
     * key键
     */
    @ExcelProperty(value = "key键")
    @Schema(description = "key键", required = true)
    private String testKey;

    /**
     * 值
     */
    @ExcelProperty(value = "值")
    @Schema(description = "值", required = true)
    private String value;


}
