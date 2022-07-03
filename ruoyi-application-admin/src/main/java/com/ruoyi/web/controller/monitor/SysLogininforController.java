package com.ruoyi.web.controller.monitor;

import cn.dev33.satoken.annotation.SaCheckPermission;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.R;
import com.ruoyi.common.core.domain.PageQuery;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.system.domain.SysLogininfor;
import com.ruoyi.system.domain.to.SysLogininforQuery;
import com.ruoyi.system.service.ISysLogininforService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 系统访问记录
 *
 * @author weibocy
 */
@Validated
@Api(value = "系统访问记录管理", tags = {"SysLoginService"})
@RequiredArgsConstructor
@RestController
@RequestMapping("/monitor/logininfor")
public class SysLogininforController extends BaseController {

    private final ISysLogininforService logininforService;

    @ApiOperation(value = "查询系统访问记录列表", nickname = "SysLogininforPostList")
    @SaCheckPermission("monitor:logininfor:list")
    @PostMapping("/list")
    public TableDataInfo<SysLogininfor> list(@RequestBody(required = false) SysLogininforQuery logininforQuery,
                                             @ApiParam(value = "当前页数", defaultValue = "1") @RequestParam(required = false) Integer pageNum,
                                             @ApiParam(value = "分页大小", defaultValue = "10") @RequestParam(required = false) Integer pageSize,
                                             @ApiParam("排序列") @RequestParam(required = false) String orderByColumn,
                                             @ApiParam(value = "排序的方向", example = "asc,desc") @RequestParam(required = false) String isAsc) {
        // 分页参数组装
        PageQuery pageQuery = new PageQuery();
        pageQuery.setPageNum(pageNum);
        pageQuery.setPageSize(pageSize);
        pageQuery.setOrderByColumn(orderByColumn);
        pageQuery.setIsAsc(isAsc);
        return logininforService.selectPageLogininforList(logininforQuery, pageQuery);
    }

    @ApiOperation(value = "导出系统访问记录列表", nickname = "SysLogininforPostExport")
    @Log(title = "登录日志", businessType = BusinessType.EXPORT)
    @SaCheckPermission("monitor:logininfor:export")
    @PostMapping("/export")
    public void export(@RequestBody(required = false) SysLogininforQuery logininforQuery, @ApiParam(hidden = true) HttpServletResponse response) {
        List<SysLogininfor> list = logininforService.selectLogininforList(logininforQuery);
        ExcelUtil.exportExcel(list, "登录日志", SysLogininfor.class, response);
    }

    @ApiOperation(value = "删除系统访问记录", nickname = "SysLogininforPostRemove")
    @SaCheckPermission("monitor:logininfor:remove")
    @Log(title = "登录日志", businessType = BusinessType.DELETE)
    @PostMapping("/remove")
    public R<Void> remove(@ApiParam(value = "登录日志ID组", required = true, allowMultiple = true) @RequestParam Long[] infoIds) {
        return toAjax(logininforService.deleteLogininforByIds(infoIds));
    }

    @ApiOperation(value = "清空系统访问记录", nickname = "SysLogininforPostClean")
    @SaCheckPermission("monitor:logininfor:remove")
    @Log(title = "登录日志", businessType = BusinessType.CLEAN)
    @PostMapping("/clean")
    public R<Void> clean() {
        logininforService.cleanLogininfor();
        return R.ok();
    }
}
