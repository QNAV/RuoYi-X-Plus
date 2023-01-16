package com.ruoyi.demo.domain.to;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.Date;

/**
 * 测试单表查询对象
 * @author weibocy
 */
@Schema(description = "测试单表查询对象")
@Data
public class TestDemoQuery {


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
