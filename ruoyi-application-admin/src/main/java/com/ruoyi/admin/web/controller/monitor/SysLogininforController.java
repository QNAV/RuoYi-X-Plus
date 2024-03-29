package com.ruoyi.admin.web.controller.monitor;

import cn.dev33.satoken.annotation.SaCheckPermission;
import com.ruoyi.admin.controller.AdminBaseController;
import com.ruoyi.common.annotation.AdminLog;
import com.ruoyi.common.constant.CacheConstants;
import com.ruoyi.common.core.domain.R;
import com.ruoyi.common.core.domain.bo.PageQuery;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessTypeEnum;
import com.ruoyi.common.utils.BeanCopyUtils;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.utils.redis.RedisUtils;
import com.ruoyi.system.domain.SysLogininfor;
import com.ruoyi.system.domain.bo.SysLogininforPageQueryBo;
import com.ruoyi.system.domain.bo.SysLogininforQueryBo;
import com.ruoyi.system.domain.vo.SysLogininforVo;
import com.ruoyi.system.service.ISysLogininforService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 系统访问记录
 *
 * @author ruoyi
 * @author Lion Li
 */
@Validated
@Tag(description = "系统访问记录管理", name = "SysLogininforService")
@RequiredArgsConstructor
@RestController
@RequestMapping("/monitor/logininfor")
public class SysLogininforController extends AdminBaseController {

    private final ISysLogininforService logininforService;

    /**
     * 获取系统访问记录列表
     * @param logininforPageQuery 查询对象
     * @return
     */
    @Operation(description = "查询系统访问记录列表", operationId = "SysLogininforPostList")
    @SaCheckPermission("monitor:logininfor:list")
    @PostMapping("/list")
    public TableDataInfo<SysLogininforVo> list(@RequestBody(required = false) SysLogininforPageQueryBo logininforPageQuery) {
        // 分页参数组装
        PageQuery pageQuery =  BeanCopyUtils.copy(logininforPageQuery, PageQuery.class);
        // 查询参数组装
        SysLogininforQueryBo logininforQuery = BeanCopyUtils.copy(logininforPageQuery, SysLogininforQueryBo.class);
        return logininforService.selectPageLogininforList(logininforQuery, pageQuery);
    }

    /**
     * 导出系统访问记录列表
     * @param logininforQuery 查询对象
     * @param response
     */
    @Operation(description = "导出系统访问记录列表", operationId = "SysLogininforPostExport")
    @AdminLog(title = "登录日志", businessType = BusinessTypeEnum.EXPORT)
    @SaCheckPermission("monitor:logininfor:export")
    @PostMapping("/export")
    public void export(@RequestBody(required = false) SysLogininforQueryBo logininforQuery, @Parameter(hidden = true) HttpServletResponse response) {
        List<SysLogininfor> list = logininforService.selectLogininforList(logininforQuery);
        ExcelUtil.exportExcel(list, "登录日志", SysLogininfor.class, response);
    }

    /**
     * 批量删除登录日志
     * @param infoIds 日志ids
     * @return
     */
    @Operation(description = "删除系统访问记录", operationId = "SysLogininforPostRemove")
    @SaCheckPermission("monitor:logininfor:remove")
    @AdminLog(title = "登录日志", businessType = BusinessTypeEnum.DELETE)
    @PostMapping("/remove")
    public R<Void> remove(@Parameter(description = "登录日志ID组", required = true) @RequestParam Long[] infoIds) {
        return toAjax(logininforService.deleteLogininforByIds(infoIds));
    }

    /**
     * 清理系统访问记录
     * @return
     */
    @Operation(description = "清空系统访问记录", operationId = "SysLogininforPostClean")
    @SaCheckPermission("monitor:logininfor:remove")
    @AdminLog(title = "登录日志", businessType = BusinessTypeEnum.CLEAR)
    @PostMapping("/clean")
    public R<Void> clean() {
        logininforService.cleanLogininfor();
        return R.ok();
    }

    /**
     * 账户解锁
     * @param userName 用户名
     * @return
     */
    @SaCheckPermission("monitor:logininfor:unlock")
    @AdminLog(title = "账户解锁", businessType = BusinessTypeEnum.OTHER)
    @GetMapping("/unlock/{userName}")
    public R<Void> unlock(@PathVariable("userName") String userName) {
        String loginName = CacheConstants.PWD_ERR_CNT_KEY + userName;
        if (RedisUtils.hasKey(loginName)) {
            RedisUtils.deleteObject(loginName);
        }
        return R.ok();
    }
}
