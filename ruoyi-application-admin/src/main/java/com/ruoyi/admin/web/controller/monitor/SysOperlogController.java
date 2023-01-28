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
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@Tag(description = "操作日志记录管理", name = "SysOperlogService")
@RequiredArgsConstructor
@RestController
@RequestMapping("/monitor/operlog")
public class SysOperlogController extends AdminBaseController {

    private final ISysOperLogService operLogService;

    /**
     * 获取操作日志记录列表
     * @param operLogPageQuery 查询对象
     * @return
     */
    @Operation(description = "查询操作日志记录列表", summary = "SysOperLogPostList")
    @SaCheckPermission("monitor:operlog:list")
    @PostMapping("/list")
    public TableDataInfo<SysOperLogVo> list(@RequestBody(required = false) SysOperLogPageQueryBo operLogPageQuery) {
        // 分页参数组装
        PageQuery pageQuery =  BeanCopyUtils.copy(operLogPageQuery, PageQuery.class);
        // 查询参数组装
        SysOperLogQueryBo operLogQuery = BeanCopyUtils.copy(operLogPageQuery, SysOperLogQueryBo.class);
        return operLogService.selectPageOperLogList(operLogQuery, pageQuery);
    }

    /**
     * 导出操作日志记录列表
     * @param operLogQuery 查询对象
     */
    @Operation(description = "导出操作日志记录列表", summary = "SysOperLogPostExport")
    @Log(title = "操作日志", businessType = BusinessType.EXPORT)
    @SaCheckPermission("monitor:operlog:export")
    @PostMapping("/export")
    public void export(@RequestBody(required = false) SysOperLogQueryBo operLogQuery, @Parameter(hidden = true) HttpServletResponse response) {
        List<SysOperLog> list = operLogService.selectOperLogList(operLogQuery);
        ExcelUtil.exportExcel(list, "操作日志", SysOperLog.class, response);
    }

    /**
     * 批量删除操作日志记录
     * @param operIds 日志ids
     * @return
     */
    @Operation(description = "删除操作日志记录", summary = "SysOperLogPostRemove")
    @Log(title = "操作日志", businessType = BusinessType.DELETE)
    @SaCheckPermission("monitor:operlog:remove")
    @PostMapping("/remove")
    public R<Void> remove(@Parameter(description = "操作日志ID组", required = true) @RequestParam Long[] operIds) {
        return toAjax(operLogService.deleteOperLogByIds(operIds));
    }

    /**
     * 清理操作日志记录
     * @return
     */
    @Operation(description = "清空操作日志记录", summary = "SysOperLogPostClean")
    @Log(title = "操作日志", businessType = BusinessType.CLEAN)
    @SaCheckPermission("monitor:operlog:remove")
    @PostMapping("/clean")
    public R<Void> clean() {
        operLogService.cleanOperLog();
        return R.ok();
    }
}
