package com.ruoyi.web.controller.system;

import cn.dev33.satoken.annotation.SaCheckPermission;
import cn.hutool.core.lang.tree.Tree;
import cn.hutool.core.util.ArrayUtil;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.constant.UserConstants;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.R;
import com.ruoyi.common.core.domain.entity.SysDept;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.system.domain.to.SysDeptQuery;
import com.ruoyi.system.service.ISysDeptService;
import com.ruoyi.web.model.dto.RoleDeptTreeSelectDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 部门信息
 *
 * @author weibocy
 */
@Validated
@Api(value = "部门管理", tags = {"SysDeptService"})
@RequiredArgsConstructor
@RestController
@RequestMapping("/system/dept")
public class SysDeptController extends BaseController {

    private final ISysDeptService deptService;

    /**
     * 获取部门列表
     */
    @ApiOperation(value = "获取部门列表", nickname = "SysDeptPostList")
    @SaCheckPermission("system:dept:list")
    @PostMapping("/list")
    public R<List<SysDept>> list(@RequestBody(required = false) SysDeptQuery deptQuery) {
        List<SysDept> depts = deptService.selectDeptList(deptQuery);
        return R.ok(depts);
    }

    /**
     * 查询部门列表（排除节点）
     */
    @ApiOperation(value = "查询部门列表（排除节点）", nickname = "SysDeptGetExcludeChild")
    @SaCheckPermission("system:dept:list")
    @GetMapping("/list/exclude")
    public R<List<SysDept>> excludeChild(@ApiParam(value = "部门ID", required = true) @RequestParam Long deptId) {
        List<SysDept> depts = deptService.selectDeptList(new SysDeptQuery());
        depts.removeIf(d -> d.getDeptId().equals(deptId)
            || ArrayUtil.contains(StringUtils.split(d.getAncestors(), ","), deptId + ""));
        return R.ok(depts);
    }

    /**
     * 根据部门编号获取详细信息
     */
    @ApiOperation(value = "根据部门编号获取详细信息", nickname = "SysDeptGetInfo")
    @SaCheckPermission("system:dept:query")
    @GetMapping(value = "/info")
    public R<SysDept> info(@ApiParam(value = "部门ID", required = true) @RequestParam Long deptId) {
        deptService.checkDeptDataScope(deptId);
        return R.ok(deptService.selectDeptById(deptId));
    }

    /**
     * 获取部门下拉树列表
     */
    @ApiOperation(value = "获取部门下拉树列表", nickname = "SysDeptPostTreeSelect")
    @PostMapping("/treeSelect")
    public R<List<Tree<Long>>> treeSelect(@RequestBody(required = false) SysDeptQuery deptQuery) {
        List<SysDept> depts = deptService.selectDeptList(deptQuery);
        return R.ok(deptService.buildDeptTreeSelect(depts));
    }

    /**
     * 加载对应角色部门列表树
     */
    @ApiOperation(value = "加载对应角色部门列表树", nickname = "SysDeptGetRoleDeptTreeSelect")
    @GetMapping(value = "/roleDeptTreeSelect")
    public R<RoleDeptTreeSelectDTO> roleDeptTreeSelect(@ApiParam(value = "角色ID", required = true) @RequestParam Long roleId) {
        List<SysDept> depts = deptService.selectDeptList(new SysDeptQuery());
        RoleDeptTreeSelectDTO data =  new RoleDeptTreeSelectDTO();
        data.setCheckedKeys(deptService.selectDeptListByRoleId(roleId));
        data.setDepts(deptService.buildDeptTreeSelect(depts));
        return R.ok(data);
    }

    /**
     * 新增部门
     */
    @ApiOperation(value = "新增部门", nickname = "SysDeptPostAdd")
    @SaCheckPermission("system:dept:add")
    @Log(title = "部门管理", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    public R<Void> add(@Validated @RequestBody SysDept dept) {
        if (UserConstants.NOT_UNIQUE.equals(deptService.checkDeptNameUnique(dept))) {
            return R.fail("新增部门'" + dept.getDeptName() + "'失败，部门名称已存在");
        }
        return toAjax(deptService.insertDept(dept));
    }

    /**
     * 修改部门
     */
    @ApiOperation(value = "修改部门", nickname = "SysDeptPostEdit")
    @SaCheckPermission("system:dept:edit")
    @Log(title = "部门管理", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    public R<Void> edit(@Validated @RequestBody SysDept dept) {
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
    @ApiOperation(value = "删除部门", nickname = "SysDeptPostRemove")
    @SaCheckPermission("system:dept:remove")
    @Log(title = "部门管理", businessType = BusinessType.DELETE)
    @PostMapping("/remove")
    public R<Void> remove(@ApiParam(value = "部门ID串", required = true) @RequestParam Long deptId) {
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
