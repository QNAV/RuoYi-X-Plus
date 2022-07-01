package com.ruoyi.web.controller.monitor;

import cn.dev33.satoken.annotation.SaCheckPermission;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.R;
import com.ruoyi.common.core.domain.PageQuery;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.system.domain.SysOperLog;
import com.ruoyi.system.domain.to.SysOperLogQuery;
import com.ruoyi.system.service.ISysOperLogService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 操作日志记录
 *
 * @author weibocy
 */
@Validated
@Api(value = "操作日志记录管理", tags = {"SysOperlogService"})
@RequiredArgsConstructor
@RestController
@RequestMapping("/monitor/operlog")
public class SysOperlogController extends BaseController {

    private final ISysOperLogService operLogService;

    @ApiOperation(value = "查询操作日志记录列表", nickname = "SysOperLogPostList")
    @SaCheckPermission("monitor:operlog:list")
    @PostMapping("/list")
    public TableDataInfo<SysOperLog> list(@RequestBody SysOperLogQuery operLogQuery,
                                          @ApiParam(value = "当前页数", defaultValue = "1") @RequestParam Integer pageNum,
                                          @ApiParam(value = "分页大小", defaultValue = "10") @RequestParam Integer pageSize,
                                          @ApiParam("排序列") @RequestParam String orderByColumn,
                                          @ApiParam(value = "排序的方向", example = "asc,desc") @RequestParam String isAsc) {
        PageQuery pageQuery = new PageQuery();
        pageQuery.setPageNum(pageNum);
        pageQuery.setPageSize(pageSize);
        pageQuery.setOrderByColumn(orderByColumn);
        pageQuery.setIsAsc(isAsc);
        return operLogService.selectPageOperLogList(operLogQuery, pageQuery);
    }

    @ApiOperation(value = "导出操作日志记录列表", nickname = "SysOperLogPostExport")
    @Log(title = "操作日志", businessType = BusinessType.EXPORT)
    @SaCheckPermission("monitor:operlog:export")
    @PostMapping("/export")
    public void export(@RequestBody SysOperLogQuery operLogQuery, @ApiParam(hidden = true) HttpServletResponse response) {
        List<SysOperLog> list = operLogService.selectOperLogList(operLogQuery);
        ExcelUtil.exportExcel(list, "操作日志", SysOperLog.class, response);
    }

    @ApiOperation(value = "删除操作日志记录", nickname = "SysOperLogPostRemove")
    @Log(title = "操作日志", businessType = BusinessType.DELETE)
    @SaCheckPermission("monitor:operlog:remove")
    @PostMapping("/remove")
    public R<Void> remove(@ApiParam(value = "操作日志ID组", required = true) @RequestParam Long[] operIds) {
        return toAjax(operLogService.deleteOperLogByIds(operIds));
    }

    @ApiOperation(value = "清空操作日志记录", nickname = "SysOperLogPostClean")
    @Log(title = "操作日志", businessType = BusinessType.CLEAN)
    @SaCheckPermission("monitor:operlog:remove")
    @PostMapping("/clean")
    public R<Void> clean() {
        operLogService.cleanOperLog();
        return R.ok();
    }
}
