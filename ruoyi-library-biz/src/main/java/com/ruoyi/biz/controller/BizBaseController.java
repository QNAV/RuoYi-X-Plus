package com.ruoyi.biz.controller;

import com.ruoyi.biz.domain.model.BizLoginUser;
import com.ruoyi.biz.helper.BizLoginHelper;
import com.ruoyi.common.core.domain.R;
import com.ruoyi.common.utils.StringUtils;

/**
 * web层通用数据处理
 *
 * @author weibocy
 */
public class BizBaseController {

    /**
     * 响应返回结果
     *
     * @param rows 影响行数
     * @return 操作结果
     */
    protected R<Void> toAjax(int rows) {
        return rows > 0 ? R.ok() : R.fail();
    }

    /**
     * 响应返回结果
     *
     * @param result 结果
     * @return 操作结果
     */
    protected R<Void> toAjax(boolean result) {
        return result ? R.ok() : R.fail();
    }

    /**
     * 页面跳转
     */
    public String redirect(String url) {
        return StringUtils.format("redirect:{}", url);
    }

    /**
     * 获取用户缓存信息
     */
    public BizLoginUser getLoginUser() {
        return BizLoginHelper.getLoginUser();
    }

    /**
     * 获取登录用户id
     */
    public Long getUserId() {
        return BizLoginHelper.getUserId();
    }


    /**
     * 获取登录用户名
     */
    public String getUsername() {
        return BizLoginHelper.getUsername();
    }
}
