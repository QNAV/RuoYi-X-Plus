package com.ruoyi.admin.web.controller.system;

import cn.dev33.satoken.annotation.SaCheckPermission;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.constant.UserConstants;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.R;
import com.ruoyi.common.core.domain.bo.PageQuery;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.BeanCopyUtils;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.system.domain.SysPost;
import com.ruoyi.system.domain.bo.SysPostAddBo;
import com.ruoyi.system.domain.bo.SysPostEditBo;
import com.ruoyi.system.domain.bo.SysPostPageQueryBo;
import com.ruoyi.system.domain.bo.SysPostQueryBo;
import com.ruoyi.system.domain.vo.SysPostVo;
import com.ruoyi.system.service.ISysPostService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 岗位信息操作处理
 *
 * @author weibocy
 */
@Validated
@Api(value = "岗位信息管理", tags = {"SysPostService"})
@RequiredArgsConstructor
@RestController
@RequestMapping("/system/post")
public class SysPostController extends BaseController {

    private final ISysPostService postService;

    /**
     * 获取岗位列表
     */
    @ApiOperation(value = "获取岗位列表", nickname = "SysPostPostList")
    @SaCheckPermission("system:post:list")
    @PostMapping("/list")
    public TableDataInfo<SysPostVo> list(@RequestBody(required = false) SysPostPageQueryBo postPageQuery) {
        // 组装分页参数
        PageQuery pageQuery = BeanCopyUtils.copy(postPageQuery, PageQuery.class);
        // 组装查询参数
        SysPostQueryBo postQuery = BeanCopyUtils.copy(postPageQuery, SysPostQueryBo.class);
        return postService.selectPagePostList(postQuery, pageQuery);
    }

    @ApiOperation(value = "导出岗位列表", nickname = "SysPostPostExport")
    @Log(title = "岗位管理", businessType = BusinessType.EXPORT)
    @SaCheckPermission("system:post:export")
    @PostMapping("/export")
    public void export(@RequestBody(required = false) SysPostQueryBo postQuery, @ApiParam(hidden = true) HttpServletResponse response) {
        List<SysPost> list = postService.selectPostList(postQuery);
        ExcelUtil.exportExcel(list, "岗位数据", SysPost.class, response);
    }

    /**
     * 根据岗位编号获取详细信息
     */
    @ApiOperation(value = "根据岗位编号获取详细信息", nickname = "SysPostGetInfo")
    @SaCheckPermission("system:post:query")
    @GetMapping(value = "/info")
    public R<SysPostVo> info(@ApiParam(value = "岗位ID", required = true) @RequestParam Long postId) {
        return R.ok(postService.selectPostById(postId));
    }

    /**
     * 新增岗位
     */
    @ApiOperation(value = "新增岗位", nickname = "SysPostPostAdd")
    @SaCheckPermission("system:post:add")
    @Log(title = "岗位管理", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    public R<Void> add(@Validated @RequestBody SysPostAddBo postAddBo) {
        SysPost post = BeanCopyUtils.copy(postAddBo, SysPost.class);
        if (UserConstants.NOT_UNIQUE.equals(postService.checkPostNameUnique(post))) {
            return R.fail("新增岗位'" + post.getPostName() + "'失败，岗位名称已存在");
        } else if (UserConstants.NOT_UNIQUE.equals(postService.checkPostCodeUnique(post))) {
            return R.fail("新增岗位'" + post.getPostName() + "'失败，岗位编码已存在");
        }
        return toAjax(postService.insertPost(post));
    }

    /**
     * 修改岗位
     */
    @ApiOperation(value = "修改岗位", nickname = "SysPostPostEdit")
    @SaCheckPermission("system:post:edit")
    @Log(title = "岗位管理", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    public R<Void> edit(@Validated @RequestBody SysPostEditBo postBo) {
        SysPost post = BeanCopyUtils.copy(postBo, SysPost.class);
        if (UserConstants.NOT_UNIQUE.equals(postService.checkPostNameUnique(post))) {
            return R.fail("修改岗位'" + post.getPostName() + "'失败，岗位名称已存在");
        } else if (UserConstants.NOT_UNIQUE.equals(postService.checkPostCodeUnique(post))) {
            return R.fail("修改岗位'" + post.getPostName() + "'失败，岗位编码已存在");
        }
        return toAjax(postService.updatePost(post));
    }

    /**
     * 删除岗位
     */
    @ApiOperation(value = "删除岗位", nickname = "SysPostPostRemove")
    @SaCheckPermission("system:post:remove")
    @Log(title = "岗位管理", businessType = BusinessType.DELETE)
    @PostMapping("/remove")
    public R<Void> remove(@ApiParam(value = "岗位ID串", required = true, allowMultiple = true) @RequestParam Long[] postIds) {
        return toAjax(postService.deletePostByIds(postIds));
    }

    /**
     * 获取岗位选择框列表
     */
    @ApiOperation(value = "获取岗位选择框列表", nickname = "SysPostGetOptionSelect")
    @GetMapping("/optionSelect")
    public R<List<SysPostVo>> optionSelect() {
        List<SysPost> posts = postService.selectPostAll();
        return R.ok(BeanCopyUtils.copyList(posts, SysPostVo.class));
    }
}
