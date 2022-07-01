package com.ruoyi.web.controller.system;

import cn.dev33.satoken.annotation.SaCheckPermission;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.annotation.RepeatSubmit;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.R;
import com.ruoyi.common.core.domain.PageQuery;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.core.validate.AddGroup;
import com.ruoyi.common.core.validate.EditGroup;
import com.ruoyi.common.core.validate.QueryGroup;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.system.domain.bo.SysOssConfigBo;
import com.ruoyi.system.domain.to.SysOssConfigQuery;
import com.ruoyi.system.domain.vo.SysOssConfigVo;
import com.ruoyi.system.service.ISysOssConfigService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
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
public class SysOssConfigController extends BaseController {

    private final ISysOssConfigService iSysOssConfigService;

    /**
     * 查询对象存储配置列表
     */
    @ApiOperation(value = "查询对象存储配置列表", nickname = "SysOssConfigPostList")
    @SaCheckPermission("system:oss:list")
    @PostMapping("/list")
    public TableDataInfo<SysOssConfigVo> list(@RequestBody @Validated(QueryGroup.class) SysOssConfigQuery query,
                                              @ApiParam(value = "当前页数", defaultValue = "1") @RequestParam Integer pageNum,
                                              @ApiParam(value = "分页大小", defaultValue = "10") @RequestParam Integer pageSize,
                                              @ApiParam("排序列") @RequestParam String orderByColumn,
                                              @ApiParam(value = "排序的方向", example = "asc,desc") @RequestParam String isAsc) {
        PageQuery pageQuery = new PageQuery();
        pageQuery.setPageNum(pageNum);
        pageQuery.setPageSize(pageSize);
        pageQuery.setOrderByColumn(orderByColumn);
        pageQuery.setIsAsc(isAsc);
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
    public R<Void> add(@Validated(AddGroup.class) @RequestBody SysOssConfigBo bo) {
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
    public R<Void> edit(@Validated(EditGroup.class) @RequestBody SysOssConfigBo bo) {
        return toAjax(iSysOssConfigService.updateByBo(bo) ? 1 : 0);
    }

    /**
     * 删除对象存储配置
     */
    @ApiOperation(value = "删除对象存储配置", nickname = "SysOssConfigPostRemove")
    @SaCheckPermission("system:oss:remove")
    @Log(title = "对象存储配置", businessType = BusinessType.DELETE)
    @PostMapping("/remove")
    public R<Void> remove(@ApiParam(value = "OSS配置ID串", required = true) @RequestParam Long[] ossConfigIds) {
        return toAjax(iSysOssConfigService.deleteWithValidByIds(Arrays.asList(ossConfigIds), true) ? 1 : 0);
    }

    /**
     * 状态修改
     */
    @ApiOperation(value = "状态修改", nickname = "SysOssConfigPostChangeStatus")
    @SaCheckPermission("system:oss:edit")
    @Log(title = "对象存储状态修改", businessType = BusinessType.UPDATE)
    @PostMapping("/changeStatus")
    public R<Void> changeStatus(@RequestBody @Validated SysOssConfigBo bo) {
        return toAjax(iSysOssConfigService.updateOssConfigStatus(bo));
    }
}
