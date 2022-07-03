package com.ruoyi.web.controller.system;

import cn.dev33.satoken.annotation.SaCheckPermission;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.R;
import com.ruoyi.common.core.domain.PageQuery;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.system.domain.SysNotice;
import com.ruoyi.system.domain.to.SysNoticeQuery;
import com.ruoyi.system.service.ISysNoticeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * 公告 信息操作处理
 *
 * @author weibocy
 */
@Validated
@Api(value = "公告信息管理", tags = {"SysNoticeService"})
@RequiredArgsConstructor
@RestController
@RequestMapping("/system/notice")
public class SysNoticeController extends BaseController {

    private final ISysNoticeService noticeService;

    /**
     * 获取通知公告列表
     */
    @ApiOperation(value = "获取通知公告列表", nickname = "SysNoticePostList")
    @SaCheckPermission("system:notice:list")
    @PostMapping("/list")
    public TableDataInfo<SysNotice> list(@RequestBody(required = false) SysNoticeQuery noticeQuery,
                                         @ApiParam(value = "当前页数", defaultValue = "1") @RequestParam(required = false) Integer pageNum,
                                         @ApiParam(value = "分页大小", defaultValue = "10") @RequestParam(required = false) Integer pageSize,
                                         @ApiParam("排序列") @RequestParam(required = false) String orderByColumn,
                                         @ApiParam(value = "排序的方向", example = "asc,desc") @RequestParam(required = false) String isAsc) {
        PageQuery pageQuery = new PageQuery();
        pageQuery.setPageNum(pageNum);
        pageQuery.setPageSize(pageSize);
        pageQuery.setOrderByColumn(orderByColumn);
        pageQuery.setIsAsc(isAsc);
        return noticeService.selectPageNoticeList(noticeQuery, pageQuery);
    }

    /**
     * 根据通知公告编号获取详细信息
     */
    @ApiOperation(value = "根据通知公告编号获取详细信息", nickname = "SysNoticeGetInfo")
    @SaCheckPermission("system:notice:query")
    @GetMapping(value = "/info")
    public R<SysNotice> info(@ApiParam(value = "公告ID", required = true) @RequestParam Long noticeId) {
        return R.ok(noticeService.selectNoticeById(noticeId));
    }

    /**
     * 新增通知公告
     */
    @ApiOperation(value = "新增通知公告", nickname = "SysNoticePostAdd")
    @SaCheckPermission("system:notice:add")
    @Log(title = "通知公告", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    public R<Void> add(@Validated @RequestBody SysNotice notice) {
        return toAjax(noticeService.insertNotice(notice));
    }

    /**
     * 修改通知公告
     */
    @ApiOperation(value = "修改通知公告", nickname = "SysNoticePostEdit")
    @SaCheckPermission("system:notice:edit")
    @Log(title = "通知公告", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    public R<Void> edit(@Validated @RequestBody SysNotice notice) {
        return toAjax(noticeService.updateNotice(notice));
    }

    /**
     * 删除通知公告
     */
    @ApiOperation(value = "删除通知公告", nickname = "SysNoticePostRemove")
    @SaCheckPermission("system:notice:remove")
    @Log(title = "通知公告", businessType = BusinessType.DELETE)
    @PostMapping("/remove")
    public R<Void> remove(@ApiParam(value = "公告ID串", required = true, allowMultiple = true) @RequestParam Long[] noticeIds) {
        return toAjax(noticeService.deleteNoticeByIds(noticeIds));
    }
}
