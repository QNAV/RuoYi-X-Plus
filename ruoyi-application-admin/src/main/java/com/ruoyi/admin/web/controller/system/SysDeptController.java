package com.ruoyi.admin.web.controller.system;

import cn.dev33.satoken.annotation.SaCheckPermission;
import cn.hutool.core.lang.tree.Tree;
import cn.hutool.core.util.ArrayUtil;
import com.ruoyi.admin.controller.AdminBaseController;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.constant.UserConstants;
import com.ruoyi.common.core.domain.R;
import com.ruoyi.common.core.domain.entity.SysDept;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.BeanCopyUtils;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.system.domain.bo.SysDeptAddBo;
import com.ruoyi.system.domain.bo.SysDeptEditBo;
import com.ruoyi.system.domain.bo.SysDeptQueryBo;
import com.ruoyi.common.core.domain.vo.SysDeptVo;
import com.ruoyi.system.service.ISysDeptService;
import com.ruoyi.admin.web.model.vo.RoleDeptTreeSelectVo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 部门信息
 *
 * @author ruoyi
 * @author Lion Li
 */
@Validated
@Tag(description = "部门管理", name = "SysDeptService")
@RequiredArgsConstructor
@RestController
@RequestMapping("/system/dept")
public class SysDeptController extends AdminBaseController {

    private final ISysDeptService deptService;

    /**
     * 获取部门列表
     */
    @Operation(description = "获取部门列表", summary = "SysDeptPostList")
    @SaCheckPermission("system:dept:list")
    @PostMapping("/list")
    public R<List<SysDeptVo>> list(@RequestBody(required = false) SysDeptQueryBo deptQuery) {
        List<SysDeptVo> depts = deptService.selectDeptList(deptQuery);
        return R.ok(depts);
    }

    /**
     * 查询部门列表（排除节点）
     */
    @Operation(description = "查询部门列表（排除节点）", summary = "SysDeptGetExcludeChild")
    @SaCheckPermission("system:dept:list")
    @GetMapping("/list/exclude")
    public R<List<SysDeptVo>> excludeChild(@Parameter(description = "部门ID", required = true) @RequestParam Long deptId) {
        List<SysDeptVo> depts = deptService.selectDeptList(new SysDeptQueryBo());
        depts.removeIf(d -> d.getDeptId().equals(deptId)
            || ArrayUtil.contains(StringUtils.split(d.getAncestors(), ","), deptId + ""));
        return R.ok(depts);
    }

    /**
     * 根据部门编号获取详细信息
     */
    @Operation(description = "根据部门编号获取详细信息", summary = "SysDeptGetInfo")
    @SaCheckPermission("system:dept:query")
    @GetMapping(value = "/info")
    public R<SysDeptVo> info(@Parameter(description = "部门ID", required = true) @RequestParam Long deptId) {
        deptService.checkDeptDataScope(deptId);
        return R.ok(deptService.selectDeptById(deptId));
    }

    /**
     * 新增部门
     */
    @Operation(description = "新增部门", summary = "SysDeptPostAdd")
    @SaCheckPermission("system:dept:add")
    @Log(title = "部门管理", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    public R<Void> add(@Validated @RequestBody SysDeptAddBo deptBo) {
        SysDept dept = BeanCopyUtils.copy(deptBo, SysDept.class);
        if (UserConstants.NOT_UNIQUE.equals(deptService.checkDeptNameUnique(dept))) {
            return R.fail("新增部门'" + dept.getDeptName() + "'失败，部门名称已存在");
        }
        return toAjax(deptService.insertDept(dept));
    }

    /**
     * 修改部门
     */
    @Operation(description = "修改部门", summary = "SysDeptPostEdit")
    @SaCheckPermission("system:dept:edit")
    @Log(title = "部门管理", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    public R<Void> edit(@Validated @RequestBody SysDeptEditBo deptBo) {
        SysDept dept = BeanCopyUtils.copy(deptBo, SysDept.class);
        Long deptId = dept.getDeptId();
        deptService.checkDeptDataScope(deptId);
        if (UserConstants.NOT_UNIQUE.equals(deptService.checkDeptNameUnique(dept))) {
            return R.fail("修改部门'" + dept.getDeptName() + "'失败，部门名称已存在");
        } else if (dept.getParentId().equals(deptId)) {
            return R.fail("修改部门'" + dept.getDeptName() + "'失败，上级部门不能是自己");
        } else if (StringUtils.equals(UserConstants.DEPT_DISABLE, dept.getStatus())
            && deptService.selectNormalChildrenDeptById(deptId) > 0) {
            return R.fail("该部门包含未停用的子部门！");
        }
        return toAjax(deptService.updateDept(dept));
    }

    /**
     * 删除部门
     */
    @Operation(description = "删除部门", summary = "SysDeptPostRemove")
    @SaCheckPermission("system:dept:remove")
    @Log(title = "部门管理", businessType = BusinessType.DELETE)
    @PostMapping("/remove")
    public R<Void> remove(@Parameter(description = "部门ID串", required = true) @RequestParam Long deptId) {
        if (deptService.hasChildByDeptId(deptId)) {
            return R.fail("存在下级部门,不允许删除");
        }
        if (deptService.checkDeptExistUser(deptId)) {
            return R.fail("部门存在用户,不允许删除");
        }
        deptService.checkDeptDataScope(deptId);
        return toAjax(deptService.deleteDeptById(deptId));
    }
}
