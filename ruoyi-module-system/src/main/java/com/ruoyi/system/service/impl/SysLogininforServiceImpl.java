package com.ruoyi.system.service.impl;

import cn.hutool.http.useragent.UserAgent;
import cn.hutool.http.useragent.UserAgentUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ruoyi.common.constant.Constants;
import com.ruoyi.common.core.domain.bo.PageQuery;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.core.service.LogininforService;
import com.ruoyi.common.utils.ServletUtils;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.utils.ip.AddressUtils;
import com.ruoyi.system.domain.SysLogininfor;
import com.ruoyi.system.domain.bo.SysLogininforQueryBo;
import com.ruoyi.system.domain.vo.SysLogininforVo;
import com.ruoyi.system.mapper.SysLogininforMapper;
import com.ruoyi.system.service.ISysLogininforService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * 系统访问日志情况信息 服务层处理
 *
 * @author weibocy
 */
@RequiredArgsConstructor
@Slf4j
@Service
public class SysLogininforServiceImpl implements ISysLogininforService, LogininforService {

    private final SysLogininforMapper baseMapper;

    /**
     * 记录登录信息
     *
     * @param username 用户名
     * @param status   状态
     * @param message  消息
     * @param args     列表
     */
    @Async
    @Override
    public void recordLogininfor(final String username, final String status, final String message,
                                 HttpServletRequest request, final Object... args) {
        final UserAgent userAgent = UserAgentUtil.parse(request.getHeader("User-Agent"));
        final String ip = ServletUtils.getClientIP(request);

        String address = AddressUtils.getRealAddressByIP(ip);
        StringBuilder s = new StringBuilder();
        s.append(getBlock(ip));
        s.append(address);
        s.append(getBlock(username));
        s.append(getBlock(status));
        s.append(getBlock(message));
        // 打印信息到日志
        log.info(s.toString(), args);
        // 获取客户端操作系统
        String os = userAgent.getOs().getName();
        // 获取客户端浏览器
        String browser = userAgent.getBrowser().getName();
        // 封装对象
        SysLogininfor logininfor = new SysLogininfor();
        logininfor.setUserName(username);
        logininfor.setIpaddr(ip);
        logininfor.setLoginLocation(address);
        logininfor.setBrowser(browser);
        logininfor.setOs(os);
        logininfor.setMsg(message);
        // 日志状态
        if (StringUtils.equalsAny(status, Constants.LOGIN_SUCCESS, Constants.LOGOUT, Constants.REGISTER)) {
            logininfor.setStatus(Constants.SUCCESS);
        } else if (Constants.LOGIN_FAIL.equals(status)) {
            logininfor.setStatus(Constants.FAIL);
        }
        // 插入数据
        insertLogininfor(logininfor);
    }

    private String getBlock(Object msg) {
        if (msg == null) {
            msg = "";
        }
        return "[" + msg.toString() + "]";
    }

    @Override
    public TableDataInfo<SysLogininforVo> selectPageLogininforList(SysLogininforQueryBo logininforQuery, PageQuery pageQuery) {
        LambdaQueryWrapper<SysLogininfor> lqw = new LambdaQueryWrapper<SysLogininfor>()
            .like(logininforQuery != null && StringUtils.isNotBlank(logininforQuery.getIpaddr()), SysLogininfor::getIpaddr, logininforQuery.getIpaddr())
            .eq(logininforQuery != null && StringUtils.isNotBlank(logininforQuery.getStatus()), SysLogininfor::getStatus, logininforQuery.getStatus())
            .like(logininforQuery != null && StringUtils.isNotBlank(logininforQuery.getUserName()), SysLogininfor::getUserName, logininforQuery.getUserName())
            .between(logininforQuery != null && logininforQuery.getBeginTime() != null && logininforQuery.getEndTime() != null,
                SysLogininfor::getLoginTime, logininforQuery.getBeginTime(), logininforQuery.getEndTime());
        if (StringUtils.isBlank(pageQuery.getOrderByColumn())) {
            pageQuery.setOrderByColumn("info_id");
            pageQuery.setIsAsc("desc");
        }
        Page<SysLogininforVo> page = baseMapper.selectVoPage(pageQuery.build(), lqw, SysLogininforVo.class);
        return TableDataInfo.build(page);
    }

    /**
     * 新增系统登录日志
     *
     * @param logininfor 访问日志对象
     */
    @Override
    public void insertLogininfor(SysLogininfor logininfor) {
        logininfor.setLoginTime(new Date());
        baseMapper.insert(logininfor);
    }

    /**
     * 查询系统登录日志集合
     *
     * @param logininforQuery 访问日志查询对象
     * @return 登录记录集合
     */
    @Override
    public List<SysLogininfor> selectLogininforList(SysLogininforQueryBo logininforQuery) {
        return baseMapper.selectList(new LambdaQueryWrapper<SysLogininfor>()
            .like(logininforQuery != null && StringUtils.isNotBlank(logininforQuery.getIpaddr()), SysLogininfor::getIpaddr, logininforQuery.getIpaddr())
            .eq(logininforQuery != null && StringUtils.isNotBlank(logininforQuery.getStatus()), SysLogininfor::getStatus, logininforQuery.getStatus())
            .like(logininforQuery != null && StringUtils.isNotBlank(logininforQuery.getUserName()), SysLogininfor::getUserName, logininforQuery.getUserName())
            .between(logininforQuery != null && logininforQuery.getBeginTime() != null && logininforQuery.getEndTime() != null,
                SysLogininfor::getLoginTime, logininforQuery.getBeginTime(), logininforQuery.getEndTime())
            .orderByDesc(SysLogininfor::getInfoId));
    }

    /**
     * 批量删除系统登录日志
     *
     * @param infoIds 需要删除的登录日志ID
     * @return 结果
     */
    @Override
    public int deleteLogininforByIds(Long[] infoIds) {
        return baseMapper.deleteBatchIds(Arrays.asList(infoIds));
    }

    /**
     * 清空系统登录日志
     */
    @Override
    public void cleanLogininfor() {
        baseMapper.delete(new LambdaQueryWrapper<>());
    }
}
