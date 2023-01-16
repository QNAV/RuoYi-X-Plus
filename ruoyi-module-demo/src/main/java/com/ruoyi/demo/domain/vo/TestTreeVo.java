package com.ruoyi.demo.domain.vo;

import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.Date;


/**
 * 测试树表视图对象 test_tree
 *
 * @author weibocy
 * @date 2021-07-26
 */
@Data
@Schema(description = "测试树表视图对象")
@ExcelIgnoreUnannotated
public class TestTreeVo {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @Schema(description = "主键")
    private Long id;

    /**
     * 父id
     */
    @ExcelProperty(value = "父id")
    @Schema(description = "父id")
    private Long parentId;

    /**
     * 部门id
     */
    @ExcelProperty(value = "部门id")
    @Schema(description = "部门id")
    private Long deptId;

    /**
     * 用户id
     */
    @ExcelProperty(value = "用户id")
    @Schema(description = "用户id")
    private Long userId;

    /**
     * 树节点名
     */
    @ExcelProperty(value = "树节点名")
    @Schema(description = "树节点名")
    private String treeName;

    /**
     * 创建时间
     */
    @ExcelProperty(value = "创建时间")
    @Schema(description = "创建时间")
    private Date createTime;


}
