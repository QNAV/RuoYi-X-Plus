package com.ruoyi.common.core.domain.bo;

import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.utils.sql.SqlUtil;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;

/**
 * 分页查询实体类
 *
 * @author weibocy
 */

@Schema(description = "分页查询实体类")
@Data
public class PageQuery implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 分页大小
     */
    @Schema(description = "分页大小")
    private Integer pageSize;

    /**
     * 当前页数
     */
    @Schema(description = "当前页数")
    private Integer pageNum;

    /**
     * 排序列
     */
    @Schema(description = "排序列")
    private String orderByColumn;

    /**
     * 排序的方向desc或者asc
     */
    @Schema(description = "排序的方向", example = "asc,desc")
    private String isAsc;

    /**
     * 当前记录起始索引 默认值 1
     */
    public static final int DEFAULT_PAGE_NUM = 1;

    /**
     * 每页显示记录数 默认值 10
     */
    public static final int DEFAULT_PAGE_SIZE = 10;

    public <T> Page<T> build() {
        Integer pageNum = ObjectUtil.defaultIfNull(getPageNum(), DEFAULT_PAGE_NUM);
        Integer pageSize = ObjectUtil.defaultIfNull(getPageSize(), DEFAULT_PAGE_SIZE);
        if (pageNum <= 0) {
            pageNum = DEFAULT_PAGE_NUM;
        }
        Page<T> page = new Page<>(pageNum, pageSize);
        OrderItem orderItem = buildOrderItem();
        if (ObjectUtil.isNotNull(orderItem)) {
            page.addOrder(orderItem);
        }
        return page;
    }

    private OrderItem buildOrderItem() {
        // 兼容前端排序类型
        if ("ascending".equals(isAsc)) {
            isAsc = "asc";
        } else if ("descending".equals(isAsc)) {
            isAsc = "desc";
        }
        if (StringUtils.isNotBlank(orderByColumn)) {
            String orderBy = SqlUtil.escapeOrderBySql(orderByColumn);
            orderBy = StringUtils.toUnderScoreCase(orderBy);
            if ("asc".equals(isAsc)) {
                return OrderItem.asc(orderBy);
            } else if ("desc".equals(isAsc)) {
                return OrderItem.desc(orderBy);
            }
        }
        return null;
    }

}
