package com.ruoyi.generator.controller;

import cn.dev33.satoken.annotation.SaCheckPermission;
import cn.hutool.core.convert.Convert;
import cn.hutool.core.io.IoUtil;
import com.ruoyi.common.annotation.AdminLog;
import com.ruoyi.common.core.domain.bo.PageQuery;
import com.ruoyi.common.core.domain.R;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessTypeEnum;
import com.ruoyi.common.utils.BeanCopyUtils;
import com.ruoyi.generator.domain.GenTable;
import com.ruoyi.generator.domain.GenTableColumn;
import com.ruoyi.generator.domain.to.GenTablePageQuery;
import com.ruoyi.generator.domain.vo.GenInfoVo;
import com.ruoyi.generator.domain.to.GenTableQuery;
import com.ruoyi.generator.service.IGenTableService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@Tag(description = "代码生成管理", name = "GenService")
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
    @Operation(description = "查询代码生成列表", operationId = "GenPostList")
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
    @Operation(description = "获取代码生成业务信息", operationId = "GenGetInfo")
    @SaCheckPermission("tool:gen:query")
    @GetMapping(value = "/info")
    public R<GenInfoVo> info(@Parameter(description = "生成表编号", required = true) @RequestParam Long tableId) {
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
    @Operation(description = "查询数据库列表", operationId = "GenPostDbList")
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
    @Operation(description = "查询数据表字段列表", operationId = "GenGetColumnList")
    @SaCheckPermission("tool:gen:list")
    @GetMapping(value = "/column/list")
    public TableDataInfo<GenTableColumn> columnList(@Parameter(description = "生成业务表编号", required = true) @RequestParam Long tableId) {
        List<GenTableColumn> list = genTableService.selectGenTableColumnListByTableId(tableId);
        return TableDataInfo.build(list);
    }

    /**
     * 导入表结构（保存）
     */
    @Operation(description = "导入表结构（保存）", operationId = "GenPostImportTable")
    @SaCheckPermission("tool:gen:import")
    @AdminLog(title = "代码生成", businessType = BusinessTypeEnum.IMPORT)
    @PostMapping("/importTable")
    public R<Void> importTable(@Parameter(description = "业务生成表名称组", required = true) @RequestParam String[] tables) {
        String[] tableNames = Convert.toStrArray(tables);
        // 查询表信息
        List<GenTable> tableList = genTableService.selectDbTableListByNames(tableNames);
        genTableService.importGenTable(tableList);
        return R.ok();
    }

    /**
     * 修改保存代码生成业务
     */
    @Operation(description = "修改保存代码生成业务", operationId = "GenPostEdit")
    @SaCheckPermission("tool:gen:edit")
    @AdminLog(title = "代码生成", businessType = BusinessTypeEnum.MODIFY)
    @PostMapping("/edit")
    public R<Void> edit(@Validated @RequestBody GenTable genTable) {
        genTableService.validateEdit(genTable);
        genTableService.updateGenTable(genTable);
        return R.ok();
    }

    /**
     * 删除代码生成
     */
    @Operation(description = "删除代码生成", operationId = "GenPostRemove")
    @SaCheckPermission("tool:gen:remove")
    @AdminLog(title = "代码生成", businessType = BusinessTypeEnum.DELETE)
    @PostMapping("/remove")
    public R<Void> remove(@Parameter(description = "代码生成表编号组", required = true) @RequestParam Long[] tableIds) {
        genTableService.deleteGenTableByIds(tableIds);
        return R.ok();
    }

    /**
     * 预览代码
     */
    @Operation(description = "预览代码", operationId = "GenGetPreview")
    @SaCheckPermission("tool:gen:preview")
    @GetMapping("/preview")
    public R<Map<String, String>> preview(@Parameter(description = "代码生成表编号", required = true) @RequestParam Long tableId) {
        Map<String, String> dataMap = genTableService.previewCode(tableId);
        return R.ok(dataMap);
    }

    /**
     * 生成代码（下载方式）
     */
    @Operation(description = "生成代码（下载方式）", operationId = "GenGetDownload")
    @SaCheckPermission("tool:gen:code")
    @AdminLog(title = "代码生成", businessType = BusinessTypeEnum.GENCODE)
    @GetMapping("/download")
    public void download(@Parameter(hidden = true) HttpServletResponse response, @Parameter(description = "业务生成表名称", required = true) @RequestParam String tableName) throws IOException {
        byte[] data = genTableService.downloadCode(tableName);
        genCode(response, data);
    }

    /**
     * 生成代码（自定义路径）
     */
    @Operation(description = "生成代码（自定义路径）", operationId = "GenGetGenCode")
    @SaCheckPermission("tool:gen:code")
    @AdminLog(title = "代码生成", businessType = BusinessTypeEnum.GENCODE)
    @GetMapping("/genCode")
    public R<Void> genCode(@Parameter(description = "业务生成表名称", required = true) @RequestParam String tableName) {
        genTableService.generatorCode(tableName);
        return R.ok();
    }

    /**
     * 同步数据库
     */
    @Operation(description = "同步数据库", operationId = "GenGetSynchDb")
    @SaCheckPermission("tool:gen:edit")
    @AdminLog(title = "代码生成", businessType = BusinessTypeEnum.MODIFY)
    @GetMapping("/synchDb")
    public R<Void> synchDb(@Parameter(description = "业务生成表名称", required = true) @RequestParam String tableName) {
        genTableService.synchDb(tableName);
        return R.ok();
    }

    /**
     * 批量生成代码
     */
    @Operation(description = "批量生成代码", operationId = "GenGetBatchGenCode")
    @SaCheckPermission("tool:gen:code")
    @AdminLog(title = "代码生成", businessType = BusinessTypeEnum.GENCODE)
    @GetMapping("/batchGenCode")
    public void batchGenCode(@Parameter(hidden = true) HttpServletResponse response, @Parameter(description = "业务生成表名称，多个表用,分隔", required = true) @RequestParam  String tables) throws IOException {
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
