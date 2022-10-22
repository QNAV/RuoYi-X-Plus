package com.ruoyi.demo.domain.vo;

import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;
import com.ruoyi.common.annotation.ExcelDictFormat;
import com.ruoyi.common.convert.ExcelDictConvert;
import com.ruoyi.common.core.domain.BaseVo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import java.util.Date;



/**
 * 测试单视图对象 test_demo
 *
 * @author ruoyi
 * @date 2022-10-21
 */
@Data
@ApiModel(value = "TestDemoVo", description = "测试单视图对象", parent = BaseVo.class)
@ExcelIgnoreUnannotated
public class TestDemoVo extends BaseVo {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @ExcelProperty(value = "主键")
    @ApiModelProperty(value = "主键", required = true)
    private Long id;

    /**
     * 部门id
     */
    @ExcelProperty(value = "部门id")
    @ApiModelProperty(value = "部门id", required = true)
    private Long deptId;

    /**
     * 用户id
     */
    @ExcelProperty(value = "用户id")
    @ApiModelProperty(value = "用户id", required = true)
    private Long userId;

    /**
     * 排序号
     */
    @ExcelProperty(value = "排序号")
    @ApiModelProperty(value = "排序号")
    private Integer orderNum;

    /**
     * key键
     */
    @ExcelProperty(value = "key键")
    @ApiModelProperty(value = "key键", required = true)
    private String testKey;

    /**
     * 值
     */
    @ExcelProperty(value = "值")
    @ApiModelProperty(value = "值", required = true)
    private String value;


}
