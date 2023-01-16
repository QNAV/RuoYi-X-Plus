package com.ruoyi.admin.web.controller.system;

import cn.dev33.satoken.annotation.SaCheckPermission;
import cn.hutool.core.util.ObjectUtil;
import com.ruoyi.admin.controller.AdminBaseController;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.domain.R;
import com.ruoyi.common.core.domain.bo.PageQuery;
import com.ruoyi.common.core.domain.entity.SysDictData;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.BeanCopyUtils;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.system.domain.bo.SysDictDataAddBo;
import com.ruoyi.system.domain.bo.SysDictDataEditBo;
import com.ruoyi.system.domain.bo.SysDictDataPageQueryBo;
import com.ruoyi.system.domain.bo.SysDictDataQueryBo;
import com.ruoyi.common.core.domain.vo.SysDictDataVo;
import com.ruoyi.system.service.ISysDictDataService;
import com.ruoyi.system.service.ISysDictTypeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

/**
 * 数据字典信息
 *
 * @author ruoyi
 * @author Lion Li
 */
@Validated
@Tag(description = "数据字典信息管理", name = "SysDictDataService")
@RequiredArgsConstructor
@RestController
@RequestMapping("/system/dict/data")
public class SysDictDataController extends AdminBaseController {

    private final ISysDictDataService dictDataService;
    private final ISysDictTypeService dictTypeService;

    @Operation(description = "查询字典数据列表", summary = "SysDictDataPostList")
    @SaCheckPermission("system:dict:list")
    @PostMapping("/list")
    public TableDataInfo<SysDictDataVo> list(@RequestBody(required = false) SysDictDataPageQueryBo dictDataPageQuery) {
        // 分页参数组装
        PageQuery pageQuery =  BeanCopyUtils.copy(dictDataPageQuery, PageQuery.class);
        // 查询参数组装
        SysDictDataQueryBo dictDataQuery =  BeanCopyUtils.copy(dictDataPageQuery, SysDictDataQueryBo.class);
        return dictDataService.selectPageDictDataList(dictDataQuery, pageQuery);
    }

    @Operation(description = "导出字典数据列表", summary = "SysDictDataPostExport")
    @Log(title = "字典数据", businessType = BusinessType.EXPORT)
    @SaCheckPermission("system:dict:export")
    @PostMapping("/export")
    public void export(@RequestBody(required = false) SysDictDataQueryBo dictDataQuery, @Parameter(hidden = true) HttpServletResponse response) {
        List<SysDictData> list = dictDataService.selectDictDataList(dictDataQuery);
        ExcelUtil.exportExcel(list, "字典数据", SysDictData.class, response);
    }

    /**
     * 查询字典数据详细
     */
    @Operation(description = "查询字典数据详细", summary = "SysDictDataGetInfo")
    @SaCheckPermission("system:dict:query")
    @GetMapping(value = "/info")
    public R<SysDictDataVo> info(@Parameter(description = "字典code", required = true) @RequestParam Long dictCode) {
        return R.ok(dictDataService.selectDictDataById(dictCode));
    }

    /**
     * 根据字典类型查询字典数据信息
     */
    @Operation(description = "根据字典类型查询字典数据信息", summary = "SysDictDataGetType")
    @GetMapping(value = "/type")
    public R<List<SysDictDataVo>> dictType(@Parameter(description = "字典类型",required = true) @RequestParam String dictType) {
        List<SysDictDataVo> data = dictTypeService.selectDictDataByType(dictType);
        if (ObjectUtil.isNull(data)) {
            data = new ArrayList<>();
        }
        return R.ok(data);
    }

    /**
     * 新增字典类型
     */
    @Operation(description = "新增字典类型", summary = "SysDictDataPostAdd")
    @SaCheckPermission("system:dict:add")
    @Log(title = "字典数据", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    public R<Void> add(@Validated @RequestBody SysDictDataAddBo dictBo) {
        SysDictData dict = BeanCopyUtils.copy(dictBo, SysDictData.class);
        return toAjax(dictDataService.insertDictData(dict));
    }

    /**
     * 修改保存字典类型
     */
    @Operation(description = "修改保存字典类型", summary = "SysDictDataPostEdit")
    @SaCheckPermission("system:dict:edit")
    @Log(title = "字典数据", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    public R<Void> edit(@Validated @RequestBody SysDictDataEditBo dictBo) {
        SysDictData dict = BeanCopyUtils.copy(dictBo, SysDictData.class);
        return toAjax(dictDataService.updateDictData(dict));
    }

    /**
     * 删除字典类型
     */
    @Operation(description = "删除字典类型", summary = "SysDictDataPostRemove")
    @SaCheckPermission("system:dict:remove")
    @Log(title = "字典类型", businessType = BusinessType.DELETE)
    @PostMapping("/remove")
    public R<Void> remove(@Parameter(description = "字典code串", required = true) @RequestParam Long[] dictCodes) {
        dictDataService.deleteDictDataByIds(dictCodes);
        return R.ok();
    }
}
