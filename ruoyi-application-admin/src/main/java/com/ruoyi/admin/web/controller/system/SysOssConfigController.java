package com.ruoyi.admin.web.controller.system;

import cn.dev33.satoken.annotation.SaCheckPermission;
import com.ruoyi.admin.controller.AdminBaseController;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.annotation.RepeatSubmit;
import com.ruoyi.common.core.domain.R;
import com.ruoyi.common.core.domain.bo.PageQuery;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.BeanCopyUtils;
import com.ruoyi.system.domain.bo.SysOssConfigAddBo;
import com.ruoyi.system.domain.bo.SysOssConfigEditBo;
import com.ruoyi.system.domain.bo.SysOssConfigPageQueryBo;
import com.ruoyi.system.domain.bo.SysOssConfigQueryBo;
import com.ruoyi.system.domain.vo.SysOssConfigVo;
import com.ruoyi.system.service.ISysOssConfigService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;

/**
 * 对象存储配置Controller
 *
 * @author ruoyi
 * @author Lion Li
 * @author 孤舟烟雨
 * @date 2021-08-13
 */
@Validated
@Tag(description = "对象存储配置管理", name = "SysOssConfigService")
@RequiredArgsConstructor
@RestController
@RequestMapping("/system/oss/config")
public class SysOssConfigController extends AdminBaseController {

    private final ISysOssConfigService iSysOssConfigService;

    /**
     * 查询对象存储配置列表
     */
    @Operation(description = "查询对象存储配置列表", operationId = "SysOssConfigPostList")
    @SaCheckPermission("system:oss:list")
    @PostMapping("/list")
    public TableDataInfo<SysOssConfigVo> list(@RequestBody(required = false) @Validated SysOssConfigPageQueryBo ossConfigPageQuery) {
        // 分页参数组装
        PageQuery pageQuery = BeanCopyUtils.copy(ossConfigPageQuery, PageQuery.class);
        // 查询参数组装
        SysOssConfigQueryBo query = BeanCopyUtils.copy(ossConfigPageQuery, SysOssConfigQueryBo.class);
        return iSysOssConfigService.queryPageList(query, pageQuery);
    }

    /**
     * 获取对象存储配置详细信息
     */
    @Operation(description = "获取对象存储配置详细信息", operationId = "SysOssConfigGetInfo")
    @SaCheckPermission("system:oss:query")
    @GetMapping("/info")
    public R<SysOssConfigVo> info(@Parameter(description = "OSS配置ID", required = true) @RequestParam Long ossConfigId) {
        return R.ok(iSysOssConfigService.queryById(ossConfigId));
    }

    /**
     * 新增对象存储配置
     */
    @Operation(description = "新增对象存储配置", operationId = "SysOssConfigPostAdd")
    @SaCheckPermission("system:oss:add")
    @Log(title = "对象存储配置", businessType = BusinessType.INSERT)
    @RepeatSubmit()
    @PostMapping("/add")
    public R<Void> add(@Validated @RequestBody SysOssConfigAddBo addBo) {
        SysOssConfigEditBo bo = BeanCopyUtils.copy(addBo, SysOssConfigEditBo.class);
        return toAjax(iSysOssConfigService.insertByBo(bo));
    }

    /**
     * 修改对象存储配置
     */
    @Operation(description = "修改对象存储配置", operationId = "SysOssConfigPostEdit")
    @SaCheckPermission("system:oss:edit")
    @Log(title = "对象存储配置", businessType = BusinessType.UPDATE)
    @RepeatSubmit()
    @PostMapping("/edit")
    public R<Void> edit(@Validated @RequestBody SysOssConfigEditBo bo) {
        return toAjax(iSysOssConfigService.updateByBo(bo));
    }

    /**
     * 删除对象存储配置
     */
    @Operation(description = "删除对象存储配置", operationId = "SysOssConfigPostRemove")
    @SaCheckPermission("system:oss:remove")
    @Log(title = "对象存储配置", businessType = BusinessType.DELETE)
    @PostMapping("/remove")
    public R<Void> remove(@Parameter(description = "OSS配置ID组", required = true) @RequestParam Long[] ossConfigIds) {
        return toAjax(iSysOssConfigService.deleteWithValidByIds(Arrays.asList(ossConfigIds), true));
    }

    /**
     * 状态修改
     */
    @Operation(description = "状态修改", operationId = "SysOssConfigPostChangeStatus")
    @SaCheckPermission("system:oss:edit")
    @Log(title = "对象存储状态修改", businessType = BusinessType.UPDATE)
    @PostMapping("/changeStatus")
    public R<Void> changeStatus(@RequestBody @Validated SysOssConfigEditBo bo) {
        return toAjax(iSysOssConfigService.updateOssConfigStatus(bo));
    }
}
