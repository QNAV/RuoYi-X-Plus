package com.ruoyi.web.controller.system;


import cn.dev33.satoken.annotation.SaCheckPermission;
import cn.hutool.core.convert.Convert;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.http.HttpException;
import cn.hutool.http.HttpUtil;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.bo.PageQuery;
import com.ruoyi.common.core.domain.R;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.core.validate.QueryGroup;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.exception.ServiceException;
import com.ruoyi.common.utils.BeanCopyUtils;
import com.ruoyi.common.utils.file.FileUtils;
import com.ruoyi.system.domain.SysOss;
import com.ruoyi.system.domain.bo.SysOssPageQueryBo;
import com.ruoyi.system.domain.bo.SysOssQueryBo;
import com.ruoyi.system.domain.vo.SysOssVo;
import com.ruoyi.system.service.ISysOssService;
import com.ruoyi.web.model.vo.OssUploadVo;
import io.swagger.annotations.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

/**
 * 文件上传 控制层
 *
 * @author weibocy
 */
@Validated
@Api(value = "对象存储管理", tags = {"SysOssService"})
@RequiredArgsConstructor
@RestController
@RequestMapping("/system/oss")
public class SysOssController extends BaseController {

    private final ISysOssService iSysOssService;

    /**
     * 查询OSS对象存储列表
     */
    @ApiOperation(value = "查询OSS对象存储列表", nickname = "SysOssPostList")
    @SaCheckPermission("system:oss:list")
    @PostMapping("/list")
    public TableDataInfo<SysOssVo> list(@RequestBody(required = false) @Validated(QueryGroup.class) SysOssPageQueryBo ossPageQuery) {
        // 分页参数组装
        PageQuery pageQuery = BeanCopyUtils.copy(ossPageQuery, PageQuery.class);
        // 查询参数组装
        SysOssQueryBo query = BeanCopyUtils.copy(ossPageQuery, SysOssQueryBo.class);
        return iSysOssService.queryPageList(query, pageQuery);
    }

    /**
     * 查询OSS对象基于id串
     */
    @ApiOperation(value = "查询OSS对象基于ID", nickname = "SysOssGetListByIds")
    @SaCheckPermission("system:oss:list")
    @GetMapping("/listByIds")
    public R<List<SysOssVo>> listByIds(@ApiParam(value = "OSS对象ID串", required = true) @RequestParam Long[] ossIds) {
        List<SysOssVo> list = iSysOssService.listByIds(Arrays.asList(ossIds));
        return R.ok(list);
    }

    /**
     * 上传OSS对象存储
     */
    @ApiOperation(value = "上传OSS对象存储", nickname = "SysOssPostUpload")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "file", value = "文件", paramType = "query", dataTypeClass = File.class, required = true)
    })
    @SaCheckPermission("system:oss:upload")
    @Log(title = "OSS对象存储", businessType = BusinessType.INSERT)
    @PostMapping("/upload")
    public R<OssUploadVo> upload(@RequestPart("file") MultipartFile file) {
        if (ObjectUtil.isNull(file)) {
            throw new ServiceException("上传文件不能为空");
        }
        SysOss oss = iSysOssService.upload(file);
        OssUploadVo data = new OssUploadVo();
        data.setUrl(oss.getUrl());
        data.setFileName(oss.getOriginalName());
        data.setOssId(oss.getOssId().toString());
        return R.ok(data);
    }

    @ApiOperation(value = "下载OSS对象存储", nickname = "SysOssGetDownload")
    @SaCheckPermission("system:oss:download")
    @GetMapping("/download")
    public void download(@ApiParam(value = "OSS对象ID", required = true) @RequestParam Long ossId, @ApiParam(hidden = true) HttpServletResponse response) throws IOException {
        SysOss sysOss = iSysOssService.getById(ossId);
        if (ObjectUtil.isNull(sysOss)) {
            throw new ServiceException("文件数据不存在!");
        }
        response.reset();
        FileUtils.setAttachmentResponseHeader(response, sysOss.getOriginalName());
        response.setContentType(MediaType.APPLICATION_OCTET_STREAM_VALUE + "; charset=UTF-8");
        long data;
        try {
            data = HttpUtil.download(sysOss.getUrl(), response.getOutputStream(), false);
        } catch (HttpException e) {
            if (e.getMessage().contains("403")) {
                throw new ServiceException("无读取权限, 请在对应的OSS开启'公有读'权限!");
            } else {
                throw new ServiceException(e.getMessage());
            }
        }
        response.setContentLength(Convert.toInt(data));
    }

    /**
     * 删除OSS对象存储
     */
    @ApiOperation(value = "删除OSS对象存储", nickname = "SysOssPostRemove")
    @SaCheckPermission("system:oss:remove")
    @Log(title = "OSS对象存储", businessType = BusinessType.DELETE)
    @PostMapping("/remove")
    public R<Void> remove(@ApiParam(value = "OSS对象ID组", required = true, allowMultiple = true) @RequestParam Long[] ossIds) {
        return toAjax(iSysOssService.deleteWithValidByIds(Arrays.asList(ossIds), true) ? 1 : 0);
    }

}
