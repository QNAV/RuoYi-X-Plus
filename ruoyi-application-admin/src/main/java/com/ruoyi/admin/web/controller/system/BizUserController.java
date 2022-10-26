package com.ruoyi.admin.web.controller.system;

import java.util.List;
import java.util.Arrays;

import com.ruoyi.common.core.domain.vo.BizUserVo;
import com.ruoyi.common.utils.BeanCopyUtils;
import lombok.RequiredArgsConstructor;
import javax.servlet.http.HttpServletResponse;
import javax.validation.constraints.*;
import cn.dev33.satoken.annotation.SaCheckPermission;
import org.springframework.web.bind.annotation.*;
import org.springframework.validation.annotation.Validated;
import com.ruoyi.common.annotation.RepeatSubmit;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.bo.PageQuery;
import com.ruoyi.common.core.domain.R;
import com.ruoyi.common.core.validate.AddGroup;
import com.ruoyi.common.core.validate.EditGroup;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.system.domain.bo.BizUserPageQueryBo;
import com.ruoyi.system.domain.bo.BizUserQueryBo;
import com.ruoyi.system.domain.bo.BizUserAddBo;
import com.ruoyi.system.domain.bo.BizUserEditBo;
import com.ruoyi.system.service.IBizUserService;
import com.ruoyi.common.core.page.TableDataInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiOperation;

/**
 * 业务用户信息 后台管理控制器
 *
 * @author weibocy
 * @date 2022-10-25
 */
@Validated
@Api(value = "业务用户信息管理", tags = {"BizUserService"})
@RequiredArgsConstructor
@RestController
@RequestMapping("/biz/user")
public class BizUserController extends BaseController {

    /**
     * 业务用户信息服务
     */
    private final IBizUserService iBizUserService;

    /**
     * 查询业务用户信息列表
     */
    @ApiOperation(value = "查询业务用户信息列表", nickname = "BizUserPostList")
    @SaCheckPermission("system:user:list")
    @PostMapping("/list")
    public TableDataInfo<BizUserVo> list(@RequestBody(required = false) BizUserPageQueryBo bo) {
        // 分页参数组装
        PageQuery pageQuery = BeanCopyUtils.copy(bo, PageQuery.class);
        // 查询参数组装
        BizUserQueryBo queryBo = BeanCopyUtils.copy(bo, BizUserQueryBo.class);
        return iBizUserService.queryPageList(queryBo, pageQuery);
    }

    /**
     * 导出业务用户信息列表
     */
    @ApiOperation(value = "导出业务用户信息列表", nickname = "BizUserPostExport")
    @SaCheckPermission("system:user:export")
    @Log(title = "业务用户信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(@RequestBody(required = false) BizUserQueryBo bo, @ApiParam(hidden = true) HttpServletResponse response) {
        List<BizUserVo> list = iBizUserService.queryList(bo);
        ExcelUtil.exportExcel(list, "业务用户信息", BizUserVo.class, response);
    }

    /**
     * 获取业务用户信息详细信息
     */
    @ApiOperation(value = "获取业务用户信息详细信息", nickname = "BizUserGetInfo")
    @SaCheckPermission("system:user:query")
    @GetMapping(value = "/info")
    public R<BizUserVo> getInfo(@ApiParam(value = "主键", required = true)
                                @NotNull(message = "主键不能为空")
                                @RequestParam(value = "主键", required = true) Long userId) {
        return R.ok(iBizUserService.queryById(userId));
    }

    /**
     * 新增业务用户信息
     */
    @ApiOperation(value = "新增业务用户信息", nickname = "BizUserPostAdd")
    @SaCheckPermission("system:user:add")
    @Log(title = "业务用户信息", businessType = BusinessType.INSERT)
    @RepeatSubmit()
    @PostMapping("/add")
    public R<Void> add(@Validated(AddGroup.class) @RequestBody BizUserAddBo bo) {
        return toAjax(iBizUserService.insertByBo(bo) ? 1 : 0);
    }

    /**
     * 修改业务用户信息
     */
    @ApiOperation(value = "修改业务用户信息", nickname = "BizUserPostEdit")
    @SaCheckPermission("system:user:edit")
    @Log(title = "业务用户信息", businessType = BusinessType.UPDATE)
    @RepeatSubmit()
    @PostMapping("/edit")
    public R<Void> edit(@Validated(EditGroup.class) @RequestBody BizUserEditBo bo) {
        return toAjax(iBizUserService.updateByBo(bo) ? 1 : 0);
    }

    /**
     * 删除业务用户信息
     */
    @ApiOperation(value = "删除业务用户信息", nickname = "BizUserPostRemove")
    @SaCheckPermission("system:user:remove")
    @Log(title = "业务用户信息", businessType = BusinessType.DELETE)
    @PostMapping("/remove")
    public R<Void> remove(@ApiParam(value = "主键串", required = true, allowMultiple = true)
                          @NotEmpty(message = "主键不能为空")
                          @RequestParam(name = "公告ID串", required = true) Long[] userIds) {
        return toAjax(iBizUserService.deleteWithValidByIds(Arrays.asList(userIds), true) ? 1 : 0);
    }
}