package com.ruoyi.system.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.http.useragent.UserAgent;
import cn.hutool.http.useragent.UserAgentUtil;
import com.ruoyi.common.constant.Constants;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.core.domain.bo.PageQuery;
import com.ruoyi.common.core.service.LogininforService;
import com.ruoyi.common.utils.ServletUtils;
import com.ruoyi.common.utils.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.ruoyi.common.utils.ip.AddressUtils;
import com.ruoyi.system.domain.SysLogininfor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import com.ruoyi.system.domain.bo.BizLogininforPageQueryBo;
import com.ruoyi.system.domain.bo.BizLogininforQueryBo;
import com.ruoyi.system.domain.bo.BizLogininforAddBo;
import com.ruoyi.system.domain.bo.BizLogininforEditBo;
import com.ruoyi.system.domain.vo.BizLogininforVo;
import com.ruoyi.system.domain.BizLogininfor;
import com.ruoyi.system.mapper.BizLogininforMapper;
import com.ruoyi.system.service.IBizLogininforService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;
import java.util.Collection;

/**
 * 业务用户登录记录Service业务层处理
 *
 * @author weibocy
 * @date 2022-10-26
 */
@RequiredArgsConstructor
@Service
@Slf4j
public class BizLogininforServiceImpl implements IBizLogininforService, LogininforService {

    /**
     * 业务用户登录记录Mapper接口
     */
    private final BizLogininforMapper baseMapper;

    /**
     * 查询业务用户登录记录
     * @param infoId 主键编号
     */
    @Override
    public BizLogininforVo queryById(Long infoId){
        return baseMapper.selectVoById(infoId);
    }

    /**
     * 查询业务用户登录记录分页列表
     * @param queryBo 业务用户登录记录业务查询对象
     * @param pageQuery 通用分页查询对象
     */
    @Override
    public TableDataInfo<BizLogininforVo> queryPageList(BizLogininforQueryBo queryBo, PageQuery pageQuery) {
        LambdaQueryWrapper<BizLogininfor> lqw = buildQueryWrapper(queryBo);
        Page<BizLogininforVo> result = baseMapper.selectVoPage(pageQuery.build(), lqw);
        return TableDataInfo.build(result);
    }

    /**
     * 查询业务用户登录记录列表
     * @param queryBo 业务用户登录记录业务查询对象
     */
    @Override
    public List<BizLogininforVo> queryList(BizLogininforQueryBo queryBo) {
        LambdaQueryWrapper<BizLogininfor> lqw = buildQueryWrapper(queryBo);
        return baseMapper.selectVoList(lqw);
    }

    /**
     * 构建业务用户登录记录查询包装器
     * @param queryBo 业务用户登录记录业务查询对象
     */
    private LambdaQueryWrapper<BizLogininfor> buildQueryWrapper(BizLogininforQueryBo queryBo) {
        LambdaQueryWrapper<BizLogininfor> lqw = Wrappers.lambdaQuery();
        lqw.like(StringUtils.isNotBlank(queryBo.getUserName()), BizLogininfor::getUserName, queryBo.getUserName());
        lqw.eq(StringUtils.isNotBlank(queryBo.getIpaddr()), BizLogininfor::getIpaddr, queryBo.getIpaddr());
        lqw.eq(StringUtils.isNotBlank(queryBo.getLoginLocation()), BizLogininfor::getLoginLocation, queryBo.getLoginLocation());
        lqw.eq(StringUtils.isNotBlank(queryBo.getBrowser()), BizLogininfor::getBrowser, queryBo.getBrowser());
        lqw.eq(StringUtils.isNotBlank(queryBo.getOs()), BizLogininfor::getOs, queryBo.getOs());
        lqw.eq(StringUtils.isNotBlank(queryBo.getStatus()), BizLogininfor::getStatus, queryBo.getStatus());
        lqw.eq(StringUtils.isNotBlank(queryBo.getMsg()), BizLogininfor::getMsg, queryBo.getMsg());
        lqw.eq(queryBo.getLoginTime() != null, BizLogininfor::getLoginTime, queryBo.getLoginTime());
        return lqw;
    }

    /**
     * 新增业务用户登录记录
     * @param addBo 业务用户登录记录业务新增对象
     */
    @Override
    public Boolean insertByBo(BizLogininforAddBo addBo) {
        BizLogininfor add = BeanUtil.toBean(addBo, BizLogininfor.class);
        validEntityBeforeSave(add);
        boolean flag = baseMapper.insert(add) > 0;
        if (flag) {
            addBo.setInfoId(add.getInfoId());
        }
        return flag;
    }

    /**
     * 修改业务用户登录记录
     * @param editBo 业务用户登录记录业务修改对象
     */
    @Override
    public Boolean updateByBo(BizLogininforEditBo editBo) {
        BizLogininfor update = BeanUtil.toBean(editBo, BizLogininfor.class);
        validEntityBeforeSave(update);
        return baseMapper.updateById(update) > 0;
    }

    /**
     * 保存前的数据校验
     * @param entity 业务用户登录记录实体对象
     */
    private void validEntityBeforeSave(BizLogininfor entity){
        //TODO 做一些数据校验,如唯一约束
    }

    /**
     * 校验并批量删除业务用户登录记录信息
     * @param ids 主键集合
     * @param isValid 是否检验?
     */
    @Override
    public Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid) {
        if(isValid){
            //TODO 做一些业务上的校验,判断是否需要校验
        }
        return baseMapper.deleteBatchIds(ids) > 0;
    }

    private String getBlock(Object msg) {
        if (msg == null) {
            msg = "";
        }
        return "[" + msg.toString() + "]";
    }

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
    public void recordLogininfor(String username, String status, String message, HttpServletRequest request, Object... args) {
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
        BizLogininforAddBo logininfor = new BizLogininforAddBo();
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
        insertByBo(logininfor);
    }
}
