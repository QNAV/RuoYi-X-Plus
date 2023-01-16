package com.ruoyi.demo.domain.to;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.Date;

/**
 * 测试树表查询对象
 * @author weibocy
 */
@Schema(description = "测试树表查询对象")
@Data
public class TestTreeQuery {

    /**
     * 树节点名
     */
    @Schema(description = "树节点名")
    private String treeName;

    /**
     * 开始创建时间
     */
    @Schema(description = "开始创建时间")
    private Date beginCreateTime;

    /**
     * 结束创建时间
     */
    @Schema(description = "结束创建时间")
    private Date endCreateTime;

}
