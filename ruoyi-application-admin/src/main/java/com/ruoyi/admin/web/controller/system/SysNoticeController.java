package com.ruoyi.admin.web.controller.system;

import cn.dev33.satoken.annotation.SaCheckPermission;
import com.ruoyi.admin.controller.AdminBaseController;
import com.ruoyi.common.annotation.Log;
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
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * 公告 信息操作处理
 *
 * @author ruoyi
 * @author Lion Li
 */
@Validated
@Tag(description = "公告信息管理", name = "SysNoticeService")
@RequiredArgsConstructor
@RestController
@RequestMapping("/system/notice")
public class SysNoticeController extends AdminBaseController {

    private final ISysNoticeService noticeService;

    /**
     * 获取通知公告列表
     */
    @Operation(description = "获取通知公告列表", operationId = "SysNoticePostList")
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
    @Operation(description = "根据通知公告编号获取详细信息", operationId = "SysNoticeGetInfo")
    @SaCheckPermission("system:notice:query")
    @GetMapping(value = "/info")
    public R<SysNoticeVo> info(@Parameter(description = "公告ID", required = true) @RequestParam(required = true) Long noticeId) {
        return R.ok(noticeService.selectNoticeById(noticeId));
    }

    /**
     * 新增通知公告
     */
    @Operation(description = "新增通知公告", operationId = "SysNoticePostAdd")
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
    @Operation(description = "修改通知公告", operationId = "SysNoticePostEdit")
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
    @Operation(description = "删除通知公告", operationId = "SysNoticePostRemove")
    @SaCheckPermission("system:notice:remove")
    @Log(title = "通知公告", businessType = BusinessType.DELETE)
    @PostMapping("/remove")
    public R<Void> remove(@Parameter(description = "公告ID串", required = true) @RequestParam(required = true) Long[] noticeIds) {
        return toAjax(noticeService.deleteNoticeByIds(noticeIds));
    }
}
