package com.ruoyi.admin.web.controller.system;

import java.util.List;
import java.util.Arrays;

import com.ruoyi.admin.controller.AdminBaseController;
import com.ruoyi.common.utils.BeanCopyUtils;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import javax.servlet.http.HttpServletResponse;
import javax.validation.constraints.*;
import cn.dev33.satoken.annotation.SaCheckPermission;
import org.springframework.web.bind.annotation.*;
import org.springframework.validation.annotation.Validated;
import com.ruoyi.common.annotation.RepeatSubmit;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.domain.bo.PageQuery;
import com.ruoyi.common.core.domain.R;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.system.domain.vo.BizLogininforVo;
import com.ruoyi.system.domain.bo.BizLogininforPageQueryBo;
import com.ruoyi.system.domain.bo.BizLogininforQueryBo;
import com.ruoyi.system.domain.bo.BizLogininforAddBo;
import com.ruoyi.system.domain.bo.BizLogininforEditBo;
import com.ruoyi.system.service.IBizLogininforService;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 业务用户登录记录 后台管理控制器
 *
 * @author weibocy
 * @date 2022-10-26
 */
@Validated
@Tag(description = "业务用户登录记录管理", name = "BizLogininforService")
@RequiredArgsConstructor
@RestController
@RequestMapping("/system/logininfor")
public class BizLogininforController extends AdminBaseController {

    /**
     * 业务用户登录记录服务
     */
    private final IBizLogininforService iBizLogininforService;

    /**
     * 查询业务用户登录记录列表
     */
    @Operation(description = "查询业务用户登录记录列表", operationId = "BizLogininforPostList")
    @SaCheckPermission("system:logininfor:list")
    @PostMapping("/list")
    public TableDataInfo<BizLogininforVo> list(@RequestBody(required = false) BizLogininforPageQueryBo bo) {
        // 分页参数组装
        PageQuery pageQuery = BeanCopyUtils.copy(bo, PageQuery.class);
        // 查询参数组装
        BizLogininforQueryBo queryBo = BeanCopyUtils.copy(bo, BizLogininforQueryBo.class);
        return iBizLogininforService.queryPageList(queryBo, pageQuery);
    }

    /**
     * 导出业务用户登录记录列表
     */
    @Operation(description = "导出业务用户登录记录列表", operationId = "BizLogininforPostExport")
    @SaCheckPermission("system:logininfor:export")
    @Log(title = "业务用户登录记录", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(@RequestBody(required = false) BizLogininforQueryBo bo, @Parameter(hidden = true) HttpServletResponse response) {
        List<BizLogininforVo> list = iBizLogininforService.queryList(bo);
        ExcelUtil.exportExcel(list, "业务用户登录记录", BizLogininforVo.class, response);
    }

    /**
     * 获取业务用户登录记录详细信息
     */
    @Operation(description = "获取业务用户登录记录详细信息", operationId = "BizLogininforGetInfo")
    @SaCheckPermission("system:logininfor:query")
    @GetMapping(value = "/info")
    public R<BizLogininforVo> getInfo(@Parameter(description = "主键", required = true)
                                      @NotNull(message = "主键不能为空")
                                      @RequestParam(required = true) Long infoId) {
        return R.ok(iBizLogininforService.queryById(infoId));
    }

    /**
     * 新增业务用户登录记录
     */
    @Operation(description = "新增业务用户登录记录", operationId = "BizLogininforPostAdd")
    @SaCheckPermission("system:logininfor:add")
    @Log(title = "业务用户登录记录", businessType = BusinessType.INSERT)
    @RepeatSubmit()
    @PostMapping("/add")
    public R<Void> add(@Validated @RequestBody BizLogininforAddBo bo) {
        return toAjax(iBizLogininforService.insertByBo(bo));
    }

    /**
     * 修改业务用户登录记录
     */
    @Operation(description = "修改业务用户登录记录", operationId = "BizLogininforPostEdit")
    @SaCheckPermission("system:logininfor:edit")
    @Log(title = "业务用户登录记录", businessType = BusinessType.UPDATE)
    @RepeatSubmit()
    @PostMapping("/edit")
    public R<Void> edit(@Validated @RequestBody BizLogininforEditBo bo) {
        return toAjax(iBizLogininforService.updateByBo(bo));
    }

    /**
     * 删除业务用户登录记录
     */
    @Operation(description = "删除业务用户登录记录", operationId = "BizLogininforPostRemove")
    @SaCheckPermission("system:logininfor:remove")
    @Log(title = "业务用户登录记录", businessType = BusinessType.DELETE)
    @PostMapping("/remove")
    public R<Void> remove(@Parameter(description = "主键串", required = true)
                          @NotEmpty(message = "主键不能为空")
                          @RequestParam(required = true) Long[] infoIds) {
        return toAjax(iBizLogininforService.deleteWithValidByIds(Arrays.asList(infoIds), true));
    }
}
