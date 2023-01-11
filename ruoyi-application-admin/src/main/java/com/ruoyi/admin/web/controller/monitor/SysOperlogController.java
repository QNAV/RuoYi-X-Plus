package com.ruoyi.admin.web.controller.monitor;

import cn.dev33.satoken.annotation.SaCheckPermission;
import com.ruoyi.admin.controller.AdminBaseController;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.domain.R;
import com.ruoyi.common.core.domain.bo.PageQuery;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.BeanCopyUtils;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.system.domain.SysOperLog;
import com.ruoyi.system.domain.bo.SysOperLogPageQueryBo;
import com.ruoyi.system.domain.bo.SysOperLogQueryBo;
import com.ruoyi.system.domain.vo.SysOperLogVo;
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
 * @author ruoyi
 * @author Lion Li
 */
@Validated
@Api(value = "操作日志记录管理", tags = {"SysOperlogService"})
@RequiredArgsConstructor
@RestController
@RequestMapping("/monitor/operlog")
public class SysOperlogController extends AdminBaseController {

    private final ISysOperLogService operLogService;

    @ApiOperation(value = "查询操作日志记录列表", nickname = "SysOperLogPostList")
    @SaCheckPermission("monitor:operlog:list")
    @PostMapping("/list")
    public TableDataInfo<SysOperLogVo> list(@RequestBody(required = false) SysOperLogPageQueryBo operLogPageQuery) {
        // 分页参数组装
        PageQuery pageQuery =  BeanCopyUtils.copy(operLogPageQuery, PageQuery.class);
        // 查询参数组装
        SysOperLogQueryBo operLogQuery = BeanCopyUtils.copy(operLogPageQuery, SysOperLogQueryBo.class);
        return operLogService.selectPageOperLogList(operLogQuery, pageQuery);
    }

    @ApiOperation(value = "导出操作日志记录列表", nickname = "SysOperLogPostExport")
    @Log(title = "操作日志", businessType = BusinessType.EXPORT)
    @SaCheckPermission("monitor:operlog:export")
    @PostMapping("/export")
    public void export(@RequestBody(required = false) SysOperLogQueryBo operLogQuery, @ApiParam(hidden = true) HttpServletResponse response) {
        List<SysOperLog> list = operLogService.selectOperLogList(operLogQuery);
        ExcelUtil.exportExcel(list, "操作日志", SysOperLog.class, response);
    }

    @ApiOperation(value = "删除操作日志记录", nickname = "SysOperLogPostRemove")
    @Log(title = "操作日志", businessType = BusinessType.DELETE)
    @SaCheckPermission("monitor:operlog:remove")
    @PostMapping("/remove")
    public R<Void> remove(@ApiParam(value = "操作日志ID组", required = true, allowMultiple = true) @RequestParam Long[] operIds) {
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
