package com.ruoyi.admin.web.controller.system;


import cn.dev33.satoken.annotation.SaCheckPermission;
import cn.hutool.core.util.ObjectUtil;
import com.ruoyi.admin.controller.AdminBaseController;
import com.ruoyi.common.annotation.AdminLog;
import com.ruoyi.common.core.domain.bo.PageQuery;
import com.ruoyi.common.core.domain.R;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessTypeEnum;
import com.ruoyi.common.exception.ServiceException;
import com.ruoyi.common.utils.BeanCopyUtils;
import com.ruoyi.system.domain.bo.SysOssPageQueryBo;
import com.ruoyi.system.domain.bo.SysOssQueryBo;
import com.ruoyi.system.domain.vo.SysOssVo;
import com.ruoyi.system.service.ISysOssService;
import com.ruoyi.admin.web.model.vo.OssUploadVo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

/**
 * 文件上传 控制层
 *
 * @author ruoyi
 * @author Lion Li
 */
@Validated
@Tag(description = "对象存储管理", name = "SysOssService")
@RequiredArgsConstructor
@RestController
@RequestMapping("/system/oss")
public class SysOssController extends AdminBaseController {

    private final ISysOssService iSysOssService;

    /**
     * 查询OSS对象存储列表
     */
    @Operation(description = "查询OSS对象存储列表", operationId = "SysOssPostList")
    @SaCheckPermission("system:oss:list")
    @PostMapping("/list")
    public TableDataInfo<SysOssVo> list(@RequestBody(required = false) @Validated SysOssPageQueryBo ossPageQuery) {
        // 分页参数组装
        PageQuery pageQuery = BeanCopyUtils.copy(ossPageQuery, PageQuery.class);
        // 查询参数组装
        SysOssQueryBo query = BeanCopyUtils.copy(ossPageQuery, SysOssQueryBo.class);
        return iSysOssService.queryPageList(query, pageQuery);
    }

    /**
     * 查询OSS对象基于id串
     */
    @Operation(description = "查询OSS对象基于ID", operationId = "SysOssGetListByIds")
    @SaCheckPermission("system:oss:list")
    @GetMapping("/listByIds")
    public R<List<SysOssVo>> listByIds(@Parameter(description = "OSS对象ID串", required = true) @RequestParam Long[] ossIds) {
        List<SysOssVo> list = iSysOssService.listByIds(Arrays.asList(ossIds));
        return R.ok(list);
    }

    /**
     * 上传OSS对象存储
     */
    @Operation(description = "上传OSS对象存储", operationId = "SysOssPostUpload")
    @SaCheckPermission("system:oss:upload")
    @AdminLog(title = "OSS对象存储", businessType = BusinessTypeEnum.ADD)
    @PostMapping("/upload")
    public R<OssUploadVo> upload(@RequestPart("file") MultipartFile file) {
        if (ObjectUtil.isNull(file)) {
            throw new ServiceException("上传文件不能为空");
        }
        SysOssVo oss = iSysOssService.upload(file);
        OssUploadVo data = new OssUploadVo();
        data.setUrl(oss.getUrl());
        data.setFileName(oss.getOriginalName());
        data.setOssId(oss.getOssId().toString());
        return R.ok(data);
    }

    @Operation(description = "下载OSS对象存储", operationId = "SysOssGetDownload")
    @SaCheckPermission("system:oss:download")
    @GetMapping("/download")
    public void download(@Parameter(description = "OSS对象ID", required = true) @RequestParam Long ossId, @Parameter(hidden = true) HttpServletResponse response) throws IOException {
        iSysOssService.download(ossId,response);
    }

    /**
     * 删除OSS对象存储
     */
    @Operation(description = "删除OSS对象存储", operationId = "SysOssPostRemove")
    @SaCheckPermission("system:oss:remove")
    @AdminLog(title = "OSS对象存储", businessType = BusinessTypeEnum.DELETE)
    @PostMapping("/remove")
    public R<Void> remove(@Parameter(description = "OSS对象ID组", required = true) @RequestParam Long[] ossIds) {
        return toAjax(iSysOssService.deleteWithValidByIds(Arrays.asList(ossIds), true));
    }

}
