package com.ruoyi.demo.domain.bo;

import io.swagger.v3.oas.annotations.media.Schema;
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
@Schema(description = "测试单业务新增对象")
public class TestDemoAddBo {

    /**
     * 部门id
     */
    @Schema(description = "部门id", required = true)
    @NotNull(message = "部门id不能为空")
    private Long deptId;

    /**
     * 用户id
     */
    @Schema(description = "用户id", required = true)
    @NotNull(message = "用户id不能为空")
    private Long userId;

    /**
     * 排序号
     */
    @Schema(description = "排序号")
    private Integer orderNum;

    /**
     * key键
     */
    @Schema(description = "key键", required = true)
    @NotBlank(message = "key键不能为空")
    private String testKey;

    /**
     * 值
     */
    @Schema(description = "值", required = true)
    @NotBlank(message = "值不能为空")
    private String value;


}
