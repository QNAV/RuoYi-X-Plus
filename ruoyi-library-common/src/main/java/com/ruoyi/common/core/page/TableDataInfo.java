package com.ruoyi.common.core.page;

import cn.hutool.http.HttpStatus;
import com.baomidou.mybatisplus.core.metadata.IPage;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.List;

/**
 * 表格分页数据对象
 *
 * @author weibocy
 */

@Data
@NoArgsConstructor
@ApiModel("分页响应对象")
public class TableDataInfo<T> implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 消息状态码
     */
    @ApiModelProperty("消息状态码")
    private int code;

    /**
     * 消息内容
     */
    @ApiModelProperty("消息内容")
    private String msg;

    /**
     * 数据对象
     */
    @ApiModelProperty("数据对象")
    private TableData<T> data;

    /**
     * 分页
     *
     * @param list  列表数据
     * @param total 总记录数
     */
    public TableDataInfo(List<T> list, long total) {
        this.data = new TableData<>();
        this.data.setRows(list);
        this.data.setTotal(total);
    }

    public static <T> TableDataInfo<T> build(IPage<T> page) {
        TableDataInfo<T> rspData = new TableDataInfo<>();
        rspData.setCode(HttpStatus.HTTP_OK);
        rspData.setMsg("查询成功");
        TableData<T> tableData = new TableData<>();
        tableData.setRows(page.getRecords());
        tableData.setTotal(page.getTotal());
        rspData.setData(tableData);
        return rspData;
    }

    public static <T> TableDataInfo<T> build(List<T> list) {
        TableDataInfo<T> rspData = new TableDataInfo<>();
        rspData.setCode(HttpStatus.HTTP_OK);
        rspData.setMsg("查询成功");
        TableData<T> tableData = new TableData<>();
        tableData.setRows(list);
        tableData.setTotal(list.size());
        rspData.setData(tableData);
        return rspData;
    }

    public static <T> TableDataInfo<T> build() {
        TableDataInfo<T> rspData = new TableDataInfo<>();
        rspData.setCode(HttpStatus.HTTP_OK);
        rspData.setMsg("查询成功");
        return rspData;
    }

    /**
     * 分页数据对象
     */
    @Data
    @ApiModel("分页数据对象")
    @NoArgsConstructor
    public static class TableData<T> {
        /**
         * 总记录数
         */
        @ApiModelProperty("总记录数")
        private long total;

        /**
         * 列表数据
         */
        @ApiModelProperty("列表数据")
        private List<T> rows;
    }

}
