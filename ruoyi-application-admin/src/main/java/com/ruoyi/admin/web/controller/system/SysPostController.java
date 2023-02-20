package com.ruoyi.admin.web.controller.system;

import cn.dev33.satoken.annotation.SaCheckPermission;
import com.ruoyi.admin.controller.AdminBaseController;
import com.ruoyi.common.annotation.AdminLog;
import com.ruoyi.common.core.domain.R;
import com.ruoyi.common.core.domain.bo.PageQuery;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessTypeEnum;
import com.ruoyi.common.enums.CommonYesOrNoEnum;
import com.ruoyi.common.utils.BeanCopyUtils;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.system.domain.SysPost;
import com.ruoyi.system.domain.bo.SysPostAddBo;
import com.ruoyi.system.domain.bo.SysPostEditBo;
import com.ruoyi.system.domain.bo.SysPostPageQueryBo;
import com.ruoyi.system.domain.bo.SysPostQueryBo;
import com.ruoyi.system.domain.vo.SysPostVo;
import com.ruoyi.system.service.ISysPostService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 岗位信息操作处理
 *
 * @author ruoyi
 * @author Lion Li
 */
@Validated
@Tag(description = "岗位信息管理", name = "SysPostService")
@RequiredArgsConstructor
@RestController
@RequestMapping("/system/post")
public class SysPostController extends AdminBaseController {

    private final ISysPostService postService;

    /**
     * 获取岗位列表
     */
    @Operation(description = "获取岗位列表", operationId = "SysPostPostList")
    @SaCheckPermission("system:post:list")
    @PostMapping("/list")
    public TableDataInfo<SysPostVo> list(@RequestBody(required = false) SysPostPageQueryBo postPageQuery) {
        // 组装分页参数
        PageQuery pageQuery = BeanCopyUtils.copy(postPageQuery, PageQuery.class);
        // 组装查询参数
        SysPostQueryBo postQuery = BeanCopyUtils.copy(postPageQuery, SysPostQueryBo.class);
        return postService.selectPagePostList(postQuery, pageQuery);
    }

    @Operation(description = "导出岗位列表", operationId = "SysPostPostExport")
    @AdminLog(title = "岗位管理", businessType = BusinessTypeEnum.EXPORT)
    @SaCheckPermission("system:post:export")
    @PostMapping("/export")
    public void export(@RequestBody(required = false) SysPostQueryBo postQuery, @Parameter(hidden = true) HttpServletResponse response) {
        List<SysPost> list = postService.selectPostList(postQuery);
        ExcelUtil.exportExcel(list, "岗位数据", SysPost.class, response);
    }

    /**
     * 根据岗位编号获取详细信息
     */
    @Operation(description = "根据岗位编号获取详细信息", operationId = "SysPostGetInfo")
    @SaCheckPermission("system:post:query")
    @GetMapping(value = "/info")
    public R<SysPostVo> info(@Parameter(description = "岗位ID", required = true) @RequestParam Long postId) {
        return R.ok(postService.selectPostById(postId));
    }

    /**
     * 新增岗位
     */
    @Operation(description = "新增岗位", operationId = "SysPostPostAdd")
    @SaCheckPermission("system:post:add")
    @AdminLog(title = "岗位管理", businessType = BusinessTypeEnum.ADD)
    @PostMapping("/add")
    public R<Void> add(@Validated @RequestBody SysPostAddBo postAddBo) {
        SysPost post = BeanCopyUtils.copy(postAddBo, SysPost.class);
        if (CommonYesOrNoEnum.NO.equals(postService.checkPostNameUnique(post))) {
            return R.fail("新增岗位'" + post.getPostName() + "'失败，岗位名称已存在");
        } else if (CommonYesOrNoEnum.NO.equals(postService.checkPostCodeUnique(post))) {
            return R.fail("新增岗位'" + post.getPostName() + "'失败，岗位编码已存在");
        }
        return toAjax(postService.insertPost(post));
    }

    /**
     * 修改岗位
     */
    @Operation(description = "修改岗位", operationId = "SysPostPostEdit")
    @SaCheckPermission("system:post:edit")
    @AdminLog(title = "岗位管理", businessType = BusinessTypeEnum.MODIFY)
    @PostMapping("/edit")
    public R<Void> edit(@Validated @RequestBody SysPostEditBo postBo) {
        SysPost post = BeanCopyUtils.copy(postBo, SysPost.class);
        if (CommonYesOrNoEnum.NO.equals(postService.checkPostNameUnique(post))) {
            return R.fail("修改岗位'" + post.getPostName() + "'失败，岗位名称已存在");
        } else if (CommonYesOrNoEnum.NO.equals(postService.checkPostCodeUnique(post))) {
            return R.fail("修改岗位'" + post.getPostName() + "'失败，岗位编码已存在");
        }
        return toAjax(postService.updatePost(post));
    }

    /**
     * 删除岗位
     */
    @Operation(description = "删除岗位", operationId = "SysPostPostRemove")
    @SaCheckPermission("system:post:remove")
    @AdminLog(title = "岗位管理", businessType = BusinessTypeEnum.DELETE)
    @PostMapping("/remove")
    public R<Void> remove(@Parameter(description = "岗位ID串", required = true) @RequestParam Long[] postIds) {
        return toAjax(postService.deletePostByIds(postIds));
    }

    /**
     * 获取岗位选择框列表
     */
    @Operation(description = "获取岗位选择框列表", operationId = "SysPostGetOptionSelect")
    @GetMapping("/optionSelect")
    public R<List<SysPostVo>> optionSelect() {
        List<SysPost> posts = postService.selectPostAll();
        return R.ok(BeanCopyUtils.copyList(posts, SysPostVo.class));
    }
}
