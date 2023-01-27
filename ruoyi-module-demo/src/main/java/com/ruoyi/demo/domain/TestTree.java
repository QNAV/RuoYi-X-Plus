package com.ruoyi.demo.domain;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.Version;
import com.ruoyi.common.core.domain.TreeEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 测试树表对象 test_tree
 *
 * @author weibocy
 * @date 2021-07-26
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("test_tree")
@Schema(description = "测试树表对象")
public class TestTree extends TreeEntity<TestTree> {

    private static final long serialVersionUID = 1L;


    /**
     * 主键
     */
    @Schema(description = "主键")
    @TableId(value = "id")
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
     * 树节点名
     */
    @Schema(description = "树节点名")
    private String treeName;

    /**
     * 版本
     */
    @Schema(description = "版本")
    @Version
    private Long version;

    /**
     * 删除标志
     */
    @Schema(description = "删除标志")
    @TableLogic
    private Long delFlag;

}
