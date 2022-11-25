package com.ruoyi.demo.controller;

import java.util.List;
import java.util.Arrays;

import com.ruoyi.common.utils.BeanCopyUtils;
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
import com.ruoyi.common.core.validate.AddGroup;
import com.ruoyi.common.core.validate.EditGroup;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.demo.domain.vo.TestDemoVo;
import com.ruoyi.demo.domain.bo.TestDemoAddBo;
import com.ruoyi.demo.domain.bo.TestDemoEditBo;
import com.ruoyi.demo.domain.bo.TestDemoPageQueryBo;
import com.ruoyi.demo.domain.bo.TestDemoQueryBo;
import com.ruoyi.demo.service.ITestDemoService;
import com.ruoyi.common.core.page.TableDataInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiOperation;

/**
 * 测试单 后台管理控制器
 * !!!不推荐模块内写控制器
 * @author ruoyi
 * @date 2022-10-22
 */
@Validated
@Api(value = "测试单管理", tags = {"TestDemoService"})
@RequiredArgsConstructor
@RestController
@RequestMapping("/demo/demo")
public class TestDemoController {

    /**
     * 测试单服务
     */
    private final ITestDemoService iTestDemoService;

    /**
     * 查询测试单列表
     */
    @ApiOperation(value = "查询测试单列表", nickname = "TestDemoPostList")
    @SaCheckPermission("demo:demo:list")
    @PostMapping("/list")
    public TableDataInfo<TestDemoVo> list(@RequestBody(required = false) TestDemoPageQueryBo bo) {
        // 分页参数组装
        PageQuery pageQuery = BeanCopyUtils.copy(bo, PageQuery.class);
        // 查询参数组装
        TestDemoQueryBo queryBo = BeanCopyUtils.copy(bo, TestDemoQueryBo.class);
        return iTestDemoService.queryPageList(queryBo, pageQuery);
    }

    /**
     * 导出测试单列表
     */
    @ApiOperation(value = "导出测试单列表", nickname = "TestDemoPostExport")
    @SaCheckPermission("demo:demo:export")
    @Log(title = "测试单", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(@RequestBody(required = false) TestDemoQueryBo bo, @ApiParam(hidden = true) HttpServletResponse response) {
        List<TestDemoVo> list = iTestDemoService.queryList(bo);
        ExcelUtil.exportExcel(list, "测试单", TestDemoVo.class, response);
    }

    /**
     * 获取测试单详细信息
     */
    @ApiOperation(value = "获取测试单详细信息", nickname = "TestDemoGetInfo")
    @SaCheckPermission("demo:demo:query")
    @GetMapping(value = "/info")
    public R<TestDemoVo> getInfo(@ApiParam(value = "主键", required = true)
                                 @NotNull(message = "主键不能为空")
                                 @RequestParam(value = "主键", required = true) Long id) {
        return R.ok(iTestDemoService.queryById(id));
    }

    /**
     * 新增测试单
     */
    @ApiOperation(value = "新增测试单", nickname = "TestDemoPostAdd")
    @SaCheckPermission("demo:demo:add")
    @Log(title = "测试单", businessType = BusinessType.INSERT)
    @RepeatSubmit()
    @PostMapping("/add")
    public R<Void> add(@Validated @RequestBody TestDemoAddBo bo) {
        return toAjax(iTestDemoService.insertByBo(bo) ? 1 : 0);
    }

    /**
     * 修改测试单
     */
    @ApiOperation(value = "修改测试单", nickname = "TestDemoPostEdit")
    @SaCheckPermission("demo:demo:edit")
    @Log(title = "测试单", businessType = BusinessType.UPDATE)
    @RepeatSubmit()
    @PostMapping("/edit")
    public R<Void> edit(@Validated @RequestBody TestDemoEditBo bo) {
        return toAjax(iTestDemoService.updateByBo(bo) ? 1 : 0);
    }

    /**
     * 删除测试单
     */
    @ApiOperation(value = "删除测试单", nickname = "TestDemoPostRemove")
    @SaCheckPermission("demo:demo:remove")
    @Log(title = "测试单", businessType = BusinessType.DELETE)
    @PostMapping("/remove")
    public R<Void> remove(@ApiParam(value = "主键串", required = true, allowMultiple = true)
                          @NotEmpty(message = "主键不能为空")
                          @RequestParam(name = "公告ID串", required = true) Long[] ids) {
        return toAjax(iTestDemoService.deleteWithValidByIds(Arrays.asList(ids), true) ? 1 : 0);
    }

    /**
     * 响应返回结果
     *
     * @param rows 影响行数
     * @return 操作结果
     */
    protected R<Void> toAjax(int rows) {
        return rows > 0 ? R.ok() : R.fail();
    }
}
