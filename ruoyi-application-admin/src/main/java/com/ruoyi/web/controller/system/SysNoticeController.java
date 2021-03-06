package com.ruoyi.web.controller.system;

import cn.dev33.satoken.annotation.SaCheckPermission;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.R;
import com.ruoyi.common.core.domain.bo.PageQuery;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.BeanCopyUtils;
import com.ruoyi.system.domain.SysNotice;
import com.ruoyi.system.domain.bo.SysNoticeAddBo;
import com.ruoyi.system.domain.bo.SysNoticeEditBo;
import com.ruoyi.system.domain.bo.SysNoticePageQueryBo;
import com.ruoyi.system.domain.bo.SysNoticeQueryBo;
import com.ruoyi.system.domain.vo.SysNoticeVo;
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
    public TableDataInfo<SysNoticeVo> list(@RequestBody(required = false) SysNoticePageQueryBo noticePageQuery) {
        // 组装分页参数
        PageQuery pageQuery = BeanCopyUtils.copy(noticePageQuery, PageQuery.class);
        // 组装查询参数
        SysNoticeQueryBo noticeQuery = BeanCopyUtils.copy(noticePageQuery, SysNoticeQueryBo.class);
        return noticeService.selectPageNoticeList(noticeQuery, pageQuery);
    }

    /**
     * 根据通知公告编号获取详细信息
     */
    @ApiOperation(value = "根据通知公告编号获取详细信息", nickname = "SysNoticeGetInfo")
    @SaCheckPermission("system:notice:query")
    @GetMapping(value = "/info")
    public R<SysNoticeVo> info(@ApiParam(value = "公告ID", required = true) @RequestParam Long noticeId) {
        return R.ok(noticeService.selectNoticeById(noticeId));
    }

    /**
     * 新增通知公告
     */
    @ApiOperation(value = "新增通知公告", nickname = "SysNoticePostAdd")
    @SaCheckPermission("system:notice:add")
    @Log(title = "通知公告", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    public R<Void> add(@Validated @RequestBody SysNoticeAddBo noticeBo) {
        SysNotice notice = BeanCopyUtils.copy(noticeBo, SysNotice.class);
        return toAjax(noticeService.insertNotice(notice));
    }

    /**
     * 修改通知公告
     */
    @ApiOperation(value = "修改通知公告", nickname = "SysNoticePostEdit")
    @SaCheckPermission("system:notice:edit")
    @Log(title = "通知公告", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    public R<Void> edit(@Validated @RequestBody SysNoticeEditBo noticeBo) {
        SysNotice notice = BeanCopyUtils.copy(noticeBo, SysNotice.class);
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
