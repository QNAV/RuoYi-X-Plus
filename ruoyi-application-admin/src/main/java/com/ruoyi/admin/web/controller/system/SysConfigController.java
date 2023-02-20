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
import com.ruoyi.system.domain.SysConfig;
import com.ruoyi.system.domain.bo.SysConfigAddBo;
import com.ruoyi.system.domain.bo.SysConfigEditBo;
import com.ruoyi.system.domain.bo.SysConfigPageQueryBo;
import com.ruoyi.system.domain.bo.SysConfigQueryBo;
import com.ruoyi.system.domain.vo.SysConfigVo;
import com.ruoyi.system.service.ISysConfigService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 参数配置 信息操作处理
 *
 * @author ruoyi
 * @author Lion Li
 */
@Validated
@Tag(description = "参数配置管理", name = "SysConfigService")
@RequiredArgsConstructor
@RestController
@RequestMapping("/system/config")
public class SysConfigController extends AdminBaseController {

    private final ISysConfigService configService;

    /**
     * 获取参数配置列表
     */
    @Operation(description = "获取参数配置列表", operationId = "SysConfigPostList")
    @SaCheckPermission("system:config:list")
    @PostMapping("/list")
    public TableDataInfo<SysConfigVo> list(@RequestBody(required = false) SysConfigPageQueryBo configPageQuery) {
        // 分页参数组装
        PageQuery pageQuery =  BeanCopyUtils.copy(configPageQuery, PageQuery.class);
        // 查询参数组装
        SysConfigQueryBo configQuery = BeanCopyUtils.copy(configPageQuery, SysConfigQueryBo.class);
        return configService.selectPageConfigList(configQuery, pageQuery);
    }

    /**
     * 导出参数配置列表
     * @param configQuery 查询对象
     * @param response
     */
    @Operation(description = "导出参数配置列表", operationId = "SysConfigPostExport")
    @AdminLog(title = "参数管理", businessType = BusinessTypeEnum.EXPORT)
    @SaCheckPermission("system:config:export")
    @PostMapping("/export")
    public void export(@RequestBody(required = false) SysConfigQueryBo configQuery, @Parameter(hidden = true) HttpServletResponse response) {
        List<SysConfig> list = configService.selectConfigList(configQuery);
        ExcelUtil.exportExcel(list, "参数数据", SysConfig.class, response);
    }

    /**
     * 根据参数编号获取详细信息
     *
     * @param configId 参数ID
     */
    @Operation(description = "根据参数编号获取详细信息", operationId = "SysConfigGetInfo")
    @SaCheckPermission("system:config:query")
    @GetMapping(value = "/info")
    public R<SysConfigVo> info(@Parameter(description = "参数ID", required = true) @RequestParam Long configId) {
        return R.ok(configService.selectConfigById(configId));
    }

    /**
     * 根据参数键名查询参数值
     *
     * @param configKey 参数Key
     */
    @Operation(description = "根据参数键名查询参数值", operationId = "SysConfigGetConfigKey")
    @GetMapping(value = "/configKey")
    public R<Void> configKey(@Parameter(description = "参数Key", required = true) @RequestParam String configKey) {
        return R.ok(configService.selectConfigByKey(configKey));
    }

    /**
     * 新增参数配置
     */
    @Operation(description = "新增参数配置", operationId = "SysConfigPostAdd")
    @SaCheckPermission("system:config:add")
    @AdminLog(title = "参数管理", businessType = BusinessTypeEnum.ADD)
    @PostMapping("/add")
    public R<Void> add(@Validated @RequestBody SysConfigAddBo configBo) {
        SysConfig config = BeanCopyUtils.copy(configBo, SysConfig.class);
        if (CommonYesOrNoEnum.NO.equals(configService.checkConfigKeyUnique(config))) {
            return R.fail("新增参数'" + config.getConfigName() + "'失败，参数键名已存在");
        }
        configService.insertConfig(config);
        return R.ok();
    }

    /**
     * 修改参数配置
     */
    @Operation(description = "修改参数配置", operationId = "SysConfigPostEdit")
    @SaCheckPermission("system:config:edit")
    @AdminLog(title = "参数管理", businessType = BusinessTypeEnum.MODIFY)
    @PostMapping("/edit")
    public R<Void> edit(@Validated @RequestBody SysConfigEditBo configBo) {
        SysConfig config = BeanCopyUtils.copy(configBo, SysConfig.class);
        if (CommonYesOrNoEnum.NO.equals(configService.checkConfigKeyUnique(config))) {
            return R.fail("修改参数'" + configBo.getConfigName() + "'失败，参数键名已存在");
        }
        configService.updateConfig(config);
        return R.ok();
    }

    /**
     * 根据参数键名修改参数配置
     */
    @Operation(description = "根据参数键名修改参数配置", operationId = "SysConfigPostUpdateByKey")
    @SaCheckPermission("system:config:edit")
    @AdminLog(title = "参数管理", businessType = BusinessTypeEnum.MODIFY)
    @PostMapping("/updateByKey")
    public R<Void> updateByKey(@RequestBody SysConfigEditBo configBo) {
        SysConfig config = BeanCopyUtils.copy(configBo, SysConfig.class);
        configService.updateConfig(config);
        return R.ok();
    }

    /**
     * 删除参数配置
     *
     * @param configIds 参数ID串
     */
    @Operation(description = "删除参数配置", operationId = "SysConfigPostRemove")
    @SaCheckPermission("system:config:remove")
    @AdminLog(title = "参数管理", businessType = BusinessTypeEnum.DELETE)
    @PostMapping("/remove")
    public R<Void> remove(@Parameter(description = "参数ID串", required = true) @RequestParam Long[] configIds) {
        configService.deleteConfigByIds(configIds);
        return R.ok();
    }

    /**
     * 刷新参数缓存
     */
    @Operation(description = "刷新参数缓存", operationId = "SysConfigPostRefreshCache")
    @SaCheckPermission("system:config:remove")
    @AdminLog(title = "参数管理", businessType = BusinessTypeEnum.CLEAR)
    @PostMapping("/refreshCache")
    public R<Void> refreshCache() {
        configService.resetConfigCache();
        return R.ok();
    }
}
