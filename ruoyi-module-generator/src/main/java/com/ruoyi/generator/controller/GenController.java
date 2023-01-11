package com.ruoyi.generator.controller;

import cn.dev33.satoken.annotation.SaCheckPermission;
import cn.hutool.core.convert.Convert;
import cn.hutool.core.io.IoUtil;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.domain.bo.PageQuery;
import com.ruoyi.common.core.domain.R;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.BeanCopyUtils;
import com.ruoyi.generator.domain.GenTable;
import com.ruoyi.generator.domain.GenTableColumn;
import com.ruoyi.generator.domain.to.GenTablePageQuery;
import com.ruoyi.generator.domain.vo.GenInfoVo;
import com.ruoyi.generator.domain.to.GenTableQuery;
import com.ruoyi.generator.service.IGenTableService;
import io.swagger.annotations.*;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * 代码生成 操作处理
 * !!!不推荐模块内写控制器
 * @author ruoyi
 * @author Lion Li
 * @author weibocy
 */
@Validated
@Api(value = "代码生成管理", tags = {"GenService"})
@RequiredArgsConstructor
@RestController
@RequestMapping("/tool/gen")
public class GenController {

    private final IGenTableService genTableService;

    /**
     * 查询代码生成列表
     *     // 每个接口应该这样声明header，但是现在umi生成插件还不能识别类型为header的，先保存记录日后方便使用
     *     @ApiImplicitParams({
     *         @ApiImplicitParam(name = "datasource", value = "数据源", paramType = "header", dataType = "string", example = "master", required = true)
     *     })
     */
    @ApiOperation(value = "查询代码生成列表", nickname = "GenPostList")
    @SaCheckPermission("tool:gen:list")
    @PostMapping("/list")
    public TableDataInfo<GenTable> genList(@RequestBody(required = false) GenTablePageQuery genTablePageQuery) {
        // 组装分页参数
        PageQuery pageQuery = BeanCopyUtils.copy(genTablePageQuery, PageQuery.class);
        // 组装查询参数
        GenTableQuery genTableQuery = BeanCopyUtils.copy(genTablePageQuery, GenTableQuery.class);
        return genTableService.selectPageGenTableList(genTableQuery, pageQuery);
    }

    /**
     * 获取代码生成业务信息
     */
    @ApiOperation(value = "获取代码生成业务信息", nickname = "GenGetInfo")
    @SaCheckPermission("tool:gen:query")
    @GetMapping(value = "/info")
    public R<GenInfoVo> info(@ApiParam(value = "生成表编号", required = true) @RequestParam Long tableId) {
        GenTable table = genTableService.selectGenTableById(tableId);
        List<GenTable> tables = genTableService.selectGenTableAll();
        List<GenTableColumn> list = genTableService.selectGenTableColumnListByTableId(tableId);
        GenInfoVo data = new GenInfoVo();
        data.setInfo(table);
        data.setRows(list);
        data.setTables(tables);
        return R.ok(data);
    }

    /**
     * 查询数据库列表
     */
    @ApiOperation(value = "查询数据库列表", nickname = "GenPostDbList")
    @SaCheckPermission("tool:gen:list")
    @PostMapping("/db/list")
    public TableDataInfo<GenTable> dbList(@RequestBody(required = false) GenTablePageQuery genTablePageQuery) {
        // 组装分页参数
        PageQuery pageQuery = BeanCopyUtils.copy(genTablePageQuery, PageQuery.class);
        // 组装查询参数
        GenTableQuery genTableQuery = BeanCopyUtils.copy(genTablePageQuery, GenTableQuery.class);
        return genTableService.selectPageDbTableList(genTableQuery, pageQuery);
    }

    /**
     * 查询数据表字段列表
     */
    @ApiOperation(value = "查询数据表字段列表", nickname = "GenGetColumnList")
    @SaCheckPermission("tool:gen:list")
    @GetMapping(value = "/column/list")
    public TableDataInfo<GenTableColumn> columnList(@ApiParam(value = "生成业务表编号", required = true) @RequestParam Long tableId) {
        List<GenTableColumn> list = genTableService.selectGenTableColumnListByTableId(tableId);
        return TableDataInfo.build(list);
    }

    /**
     * 导入表结构（保存）
     */
    @ApiOperation(value = "导入表结构（保存）", nickname = "GenPostImportTable")
    @SaCheckPermission("tool:gen:import")
    @Log(title = "代码生成", businessType = BusinessType.IMPORT)
    @PostMapping("/importTable")
    public R<Void> importTable(@ApiParam(value = "业务生成表名称组", required = true, allowMultiple = true) @RequestParam String[] tables) {
        String[] tableNames = Convert.toStrArray(tables);
        // 查询表信息
        List<GenTable> tableList = genTableService.selectDbTableListByNames(tableNames);
        genTableService.importGenTable(tableList);
        return R.ok();
    }

    /**
     * 修改保存代码生成业务
     */
    @ApiOperation(value = "修改保存代码生成业务", nickname = "GenPostEdit")
    @SaCheckPermission("tool:gen:edit")
    @Log(title = "代码生成", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    public R<Void> edit(@Validated @RequestBody GenTable genTable) {
        genTableService.validateEdit(genTable);
        genTableService.updateGenTable(genTable);
        return R.ok();
    }

    /**
     * 删除代码生成
     */
    @ApiOperation(value = "删除代码生成", nickname = "GenPostRemove")
    @SaCheckPermission("tool:gen:remove")
    @Log(title = "代码生成", businessType = BusinessType.DELETE)
    @PostMapping("/remove")
    public R<Void> remove(@ApiParam(value = "代码生成表编号组", required = true, allowMultiple = true) @RequestParam Long[] tableIds) {
        genTableService.deleteGenTableByIds(tableIds);
        return R.ok();
    }

    /**
     * 预览代码
     */
    @ApiOperation(value = "预览代码", nickname = "GenGetPreview")
    @SaCheckPermission("tool:gen:preview")
    @GetMapping("/preview")
    public R<Map<String, String>> preview(@ApiParam(value = "代码生成表编号", required = true) @RequestParam Long tableId) throws IOException {
        Map<String, String> dataMap = genTableService.previewCode(tableId);
        return R.ok(dataMap);
    }

    /**
     * 生成代码（下载方式）
     */
    @ApiOperation(value = "生成代码（下载方式）", nickname = "GenGetDownload")
    @SaCheckPermission("tool:gen:code")
    @Log(title = "代码生成", businessType = BusinessType.GENCODE)
    @GetMapping("/download")
    public void download(@ApiParam(hidden = true) HttpServletResponse response, @ApiParam(value = "业务生成表名称", required = true) @RequestParam String tableName) throws IOException {
        byte[] data = genTableService.downloadCode(tableName);
        genCode(response, data);
    }

    /**
     * 生成代码（自定义路径）
     */
    @ApiOperation(value = "生成代码（自定义路径）", nickname = "GenGetGenCode")
    @SaCheckPermission("tool:gen:code")
    @Log(title = "代码生成", businessType = BusinessType.GENCODE)
    @GetMapping("/genCode")
    public R<Void> genCode(@ApiParam(value = "业务生成表名称", required = true) @RequestParam String tableName) {
        genTableService.generatorCode(tableName);
        return R.ok();
    }

    /**
     * 同步数据库
     */
    @ApiOperation(value = "同步数据库", nickname = "GenGetSynchDb")
    @SaCheckPermission("tool:gen:edit")
    @Log(title = "代码生成", businessType = BusinessType.UPDATE)
    @GetMapping("/synchDb")
    public R<Void> synchDb(@ApiParam(value = "业务生成表名称", required = true) @RequestParam String tableName) {
        genTableService.synchDb(tableName);
        return R.ok();
    }

    /**
     * 批量生成代码
     */
    @ApiOperation(value = "批量生成代码", nickname = "GenGetBatchGenCode")
    @SaCheckPermission("tool:gen:code")
    @Log(title = "代码生成", businessType = BusinessType.GENCODE)
    @GetMapping("/batchGenCode")
    public void batchGenCode(@ApiParam(hidden = true) HttpServletResponse response, @ApiParam(value = "业务生成表名称，多个表用,分隔", required = true) @RequestParam  String tables) throws IOException {
        String[] tableNames = Convert.toStrArray(tables);
        byte[] data = genTableService.downloadCode(tableNames);
        genCode(response, data);
    }

    /**
     * 生成zip文件
     */
    private void genCode(HttpServletResponse response, byte[] data) throws IOException {
        response.reset();
        response.addHeader("Access-Control-Allow-Origin", "*");
        response.addHeader("Access-Control-Expose-Headers", "Content-Disposition");
        response.setHeader("Content-Disposition", "attachment; filename=\"ruoyi.zip\"");
        response.addHeader("Content-Length", "" + data.length);
        response.setContentType("application/octet-stream; charset=UTF-8");
        IoUtil.write(response.getOutputStream(), false, data);
    }
}
