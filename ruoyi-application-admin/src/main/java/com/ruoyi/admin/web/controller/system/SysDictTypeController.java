package com.ruoyi.admin.web.controller.system;

import cn.dev33.satoken.annotation.SaCheckPermission;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.constant.UserConstants;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.R;
import com.ruoyi.common.core.domain.bo.PageQuery;
import com.ruoyi.common.core.domain.entity.SysDictType;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.BeanCopyUtils;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.system.domain.bo.SysDictTypeAddBo;
import com.ruoyi.system.domain.bo.SysDictTypeEditBo;
import com.ruoyi.system.domain.bo.SysDictTypePageQueryBo;
import com.ruoyi.system.domain.bo.SysDictTypeQueryBo;
import com.ruoyi.common.core.domain.vo.SysDictTypeVo;
import com.ruoyi.system.service.ISysDictTypeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 数据字典信息
 *
 * @author weibocy
 */
@Validated
@Api(value = "数据字典信息管理", tags = {"SysDictTypeService"})
@RequiredArgsConstructor
@RestController
@RequestMapping("/system/dict/type")
public class SysDictTypeController extends BaseController {

    private final ISysDictTypeService dictTypeService;

    @ApiOperation(value = "查询字典类型列表", nickname = "SysDictTypePostList")
    @SaCheckPermission("system:dict:list")
    @PostMapping("/list")
    public TableDataInfo<SysDictTypeVo> list(@RequestBody(required = false) SysDictTypePageQueryBo dictTypePageQuery) {
        // 组装分页参数
        PageQuery pageQuery = BeanCopyUtils.copy(dictTypePageQuery, PageQuery.class);
        // 组装查询参数
        SysDictTypeQueryBo dictTypeQuery = BeanCopyUtils.copy(dictTypePageQuery, SysDictTypeQueryBo.class);
        return dictTypeService.selectPageDictTypeList(dictTypeQuery, pageQuery);
    }

    @ApiOperation(value = "导出字典类型列表", nickname = "SysDictTypePostExport")
    @Log(title = "字典类型", businessType = BusinessType.EXPORT)
    @SaCheckPermission("system:dict:export")
    @PostMapping("/export")
    public void export(@RequestBody(required = false) SysDictTypeQueryBo dictTypeQuery, @ApiParam(hidden = true) HttpServletResponse response) {
        List<SysDictType> list = dictTypeService.selectDictTypeList(dictTypeQuery);
        ExcelUtil.exportExcel(list, "字典类型", SysDictType.class, response);
    }

    /**
     * 查询字典类型详细
     */
    @ApiOperation(value = "查询字典类型详细", nickname = "SysDictTypeGetInfo")
    @SaCheckPermission("system:dict:query")
    @GetMapping(value = "/info")
    public R<SysDictTypeVo> info(@ApiParam(value = "字典ID", required = true) @RequestParam Long dictId) {
        return R.ok(dictTypeService.selectDictTypeById(dictId));
    }

    /**
     * 新增字典类型
     */
    @ApiOperation(value = "新增字典类型", nickname = "SysDictTypePostAdd")
    @SaCheckPermission("system:dict:add")
    @Log(title = "字典类型", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    public R<Void> add(@Validated @RequestBody SysDictTypeAddBo dictBo) {
        SysDictType dict = BeanCopyUtils.copy(dictBo, SysDictType.class);
        if (UserConstants.NOT_UNIQUE.equals(dictTypeService.checkDictTypeUnique(dict))) {
            return R.fail("新增字典'" + dict.getDictName() + "'失败，字典类型已存在");
        }
        return toAjax(dictTypeService.insertDictType(dict));
    }

    /**
     * 修改字典类型
     */
    @ApiOperation(value = "修改字典类型", nickname = "SysDictTypePostEdit")
    @SaCheckPermission("system:dict:edit")
    @Log(title = "字典类型", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    public R<Void> edit(@Validated @RequestBody SysDictTypeEditBo dictBo) {
        SysDictType dict = BeanCopyUtils.copy(dictBo, SysDictType.class);
        if (UserConstants.NOT_UNIQUE.equals(dictTypeService.checkDictTypeUnique(dict))) {
            return R.fail("修改字典'" + dict.getDictName() + "'失败，字典类型已存在");
        }
        return toAjax(dictTypeService.updateDictType(dict));
    }

    /**
     * 删除字典类型
     */
    @ApiOperation(value = "删除字典类型", nickname = "SysDictTypePostRemove")
    @SaCheckPermission("system:dict:remove")
    @Log(title = "字典类型", businessType = BusinessType.DELETE)
    @PostMapping("/remove")
    public R<Void> remove(@ApiParam(value = "字典ID串", required = true, allowMultiple = true) @RequestParam Long[] dictIds) {
        dictTypeService.deleteDictTypeByIds(dictIds);
        return R.ok();
    }

    /**
     * 刷新字典缓存
     */
    @ApiOperation(value = "刷新字典缓存", nickname = "SysDictTypePostRefreshCache")
    @SaCheckPermission("system:dict:remove")
    @Log(title = "字典类型", businessType = BusinessType.CLEAN)
    @PostMapping("/refreshCache")
    public R<Void> refreshCache() {
        dictTypeService.resetDictCache();
        return R.ok();
    }

    /**
     * 获取字典选择框列表
     */
    @ApiOperation("获取字典选择框列表")
    @GetMapping("/optionSelect")
    public R<List<SysDictTypeVo>> optionSelect() {
        List<SysDictType> dictTypes = dictTypeService.selectDictTypeAll();
        return R.ok(BeanCopyUtils.copyList(dictTypes, SysDictTypeVo.class));
    }
}
