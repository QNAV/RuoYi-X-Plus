package com.ruoyi.common.core.page;

import cn.hutool.http.HttpStatus;
import com.baomidou.mybatisplus.core.metadata.IPage;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.List;

/**
 * 表格分页数据对象
 *
 * @author ruoyi
 * @author Lion Li
 */

@Data
@NoArgsConstructor
@Schema(name = "TableDataInfo", description = "分页响应对象")
public class TableDataInfo<T> implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 消息状态码
     */
    @Schema(description = "消息状态码", required = true)
    private int code;

    /**
     * 消息内容
     */
    @Schema(description = "消息内容", required = true)
    private String msg;

    /**
     * 数据对象
     */
    @Schema(description = "数据对象", required = true)
    private TableData<T> data;

    /**
     * 分页
     *
     * @param list  列表数据
     * @param total 总记录数
     */
    public TableDataInfo(List<T> list, long total) {
        this.data = new TableData<>();
        this.data.setData(list);
        this.data.setTotal(total);
    }

    public static <T> TableDataInfo<T> build(IPage<T> page) {
        TableDataInfo<T> rspData = new TableDataInfo<>();
        rspData.setCode(HttpStatus.HTTP_OK);
        rspData.setMsg("查询成功");
        TableData<T> tableData = new TableData<>();
        tableData.setData(page.getRecords());
        tableData.setTotal(page.getTotal());
        rspData.setData(tableData);
        return rspData;
    }

    public static <T> TableDataInfo<T> build(List<T> list) {
        TableDataInfo<T> rspData = new TableDataInfo<>();
        rspData.setCode(HttpStatus.HTTP_OK);
        rspData.setMsg("查询成功");
        TableData<T> tableData = new TableData<>();
        tableData.setData(list);
        tableData.setTotal(list.size());
        rspData.setData(tableData);
        return rspData;
    }

    public static <T> TableDataInfo<T> build() {
        TableDataInfo<T> rspData = new TableDataInfo<>();
        rspData.setCode(HttpStatus.HTTP_OK);
        rspData.setMsg("查询成功");
        rspData.setData(null);
        return rspData;
    }

    /**
     * 分页数据对象
     */
    @Data
    @Schema(name = "TableData", description = "分页数据对象")
    @NoArgsConstructor
    public static class TableData<T> {
        /**
         * 总记录数
         */
        @Schema(description = "总记录数", required = true)
        private long total;

        /**
         * 列表数据
         */
        @Schema(description = "列表数据", required = true)
        private List<T> data;
    }

}
