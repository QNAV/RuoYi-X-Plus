package com.ruoyi.admin.web.controller.system;

import cn.dev33.satoken.annotation.SaCheckPermission;
import com.ruoyi.admin.controller.AdminBaseController;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.annotation.RepeatSubmit;
import com.ruoyi.common.core.domain.R;
import com.ruoyi.common.core.domain.bo.PageQuery;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.core.validate.AddGroup;
import com.ruoyi.common.core.validate.EditGroup;
import com.ruoyi.common.core.validate.QueryGroup;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.BeanCopyUtils;
import com.ruoyi.system.domain.bo.SysOssConfigAddBo;
import com.ruoyi.system.domain.bo.SysOssConfigEditBo;
import com.ruoyi.system.domain.bo.SysOssConfigPageQueryBo;
import com.ruoyi.system.domain.bo.SysOssConfigQueryBo;
import com.ruoyi.system.domain.vo.SysOssConfigVo;
import com.ruoyi.system.service.ISysOssConfigService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;

/**
 * 对象存储配置Controller
 *
 * @author weibocy
 */
@Validated
@Api(value = "对象存储配置管理", tags = {"SysOssConfigService"})
@RequiredArgsConstructor
@RestController
@RequestMapping("/system/oss/config")
public class SysOssConfigController extends AdminBaseController {

    private final ISysOssConfigService iSysOssConfigService;

    /**
     * 查询对象存储配置列表
     */
    @ApiOperation(value = "查询对象存储配置列表", nickname = "SysOssConfigPostList")
    @SaCheckPermission("system:oss:list")
    @PostMapping("/list")
    public TableDataInfo<SysOssConfigVo> list(@RequestBody(required = false) @Validated(QueryGroup.class) SysOssConfigPageQueryBo ossConfigPageQuery) {
        // 分页参数组装
        PageQuery pageQuery = BeanCopyUtils.copy(ossConfigPageQuery, PageQuery.class);
        // 查询参数组装
        SysOssConfigQueryBo query = BeanCopyUtils.copy(ossConfigPageQuery, SysOssConfigQueryBo.class);
        return iSysOssConfigService.queryPageList(query, pageQuery);
    }

    /**
     * 获取对象存储配置详细信息
     */
    @ApiOperation(value = "获取对象存储配置详细信息", nickname = "SysOssConfigGetInfo")
    @SaCheckPermission("system:oss:query")
    @GetMapping("/info")
    public R<SysOssConfigVo> info(@ApiParam(value = "OSS配置ID", required = true) @RequestParam Long ossConfigId) {
        return R.ok(iSysOssConfigService.queryById(ossConfigId));
    }

    /**
     * 新增对象存储配置
     */
    @ApiOperation(value = "新增对象存储配置", nickname = "SysOssConfigPostAdd")
    @SaCheckPermission("system:oss:add")
    @Log(title = "对象存储配置", businessType = BusinessType.INSERT)
    @RepeatSubmit()
    @PostMapping("/add")
    public R<Void> add(@Validated(AddGroup.class) @RequestBody SysOssConfigAddBo addBo) {
        SysOssConfigEditBo bo = BeanCopyUtils.copy(addBo, SysOssConfigEditBo.class);
        return toAjax(iSysOssConfigService.insertByBo(bo) ? 1 : 0);
    }

    /**
     * 修改对象存储配置
     */
    @ApiOperation(value = "修改对象存储配置", nickname = "SysOssConfigPostEdit")
    @SaCheckPermission("system:oss:edit")
    @Log(title = "对象存储配置", businessType = BusinessType.UPDATE)
    @RepeatSubmit()
    @PostMapping("/edit")
    public R<Void> edit(@Validated(EditGroup.class) @RequestBody SysOssConfigEditBo bo) {
        return toAjax(iSysOssConfigService.updateByBo(bo) ? 1 : 0);
    }

    /**
     * 删除对象存储配置
     */
    @ApiOperation(value = "删除对象存储配置", nickname = "SysOssConfigPostRemove")
    @SaCheckPermission("system:oss:remove")
    @Log(title = "对象存储配置", businessType = BusinessType.DELETE)
    @PostMapping("/remove")
    public R<Void> remove(@ApiParam(value = "OSS配置ID组", required = true, allowMultiple = true) @RequestParam Long[] ossConfigIds) {
        return toAjax(iSysOssConfigService.deleteWithValidByIds(Arrays.asList(ossConfigIds), true) ? 1 : 0);
    }

    /**
     * 状态修改
     */
    @ApiOperation(value = "状态修改", nickname = "SysOssConfigPostChangeStatus")
    @SaCheckPermission("system:oss:edit")
    @Log(title = "对象存储状态修改", businessType = BusinessType.UPDATE)
    @PostMapping("/changeStatus")
    public R<Void> changeStatus(@RequestBody @Validated SysOssConfigEditBo bo) {
        return toAjax(iSysOssConfigService.updateOssConfigStatus(bo));
    }
}
