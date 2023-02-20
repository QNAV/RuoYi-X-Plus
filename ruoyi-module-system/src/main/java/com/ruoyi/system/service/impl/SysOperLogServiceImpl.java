package com.ruoyi.system.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.ArrayUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ruoyi.common.core.domain.bo.PageQuery;
import com.ruoyi.common.core.domain.event.AdminOperLogEvent;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.utils.ip.AddressUtils;
import com.ruoyi.system.domain.SysOperLog;
import com.ruoyi.system.domain.bo.SysOperLogQueryBo;
import com.ruoyi.system.domain.vo.SysOperLogVo;
import com.ruoyi.system.mapper.SysOperLogMapper;
import com.ruoyi.system.service.ISysOperLogService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * 操作日志 服务层处理
 *
 * @author ruoyi
 * @author Lion Li
 */
@RequiredArgsConstructor
@Service
public class SysOperLogServiceImpl implements ISysOperLogService {

    private final SysOperLogMapper baseMapper;

    /**
     * 后台操作日志记录
     *
     * @param operLogEvent 操作日志事件
     */
    @Async
    @EventListener
    public void recordOper(AdminOperLogEvent operLogEvent) {
        SysOperLog operLog = BeanUtil.toBean(operLogEvent, SysOperLog.class);
        // 远程查询操作地点
        operLog.setOperLocation(AddressUtils.getRealAddressByIP(operLog.getOperIp()));
        insertOperlog(operLog);
    }

    @Override
    public TableDataInfo<SysOperLogVo> selectPageOperLogList(SysOperLogQueryBo operLogQuery, PageQuery pageQuery) {
        LambdaQueryWrapper<SysOperLog> lqw = new LambdaQueryWrapper<SysOperLog>()
            .like(operLogQuery != null && StringUtils.isNotBlank(operLogQuery.getTitle()), SysOperLog::getTitle, operLogQuery.getTitle())
            .eq(operLogQuery != null && operLogQuery.getBusinessType() != null,
                SysOperLog::getBusinessType, operLogQuery.getBusinessType())
            .func(f -> {
                if (operLogQuery != null && ArrayUtil.isNotEmpty(operLogQuery.getBusinessTypes())) {
                    f.in(SysOperLog::getBusinessType, Arrays.asList(operLogQuery.getBusinessTypes()));
                }
            })
            .eq(operLogQuery != null && operLogQuery.getStatus() != null,
                SysOperLog::getStatus, operLogQuery.getStatus())
            .like(operLogQuery != null && StringUtils.isNotBlank(operLogQuery.getOperName()), SysOperLog::getOperName, operLogQuery.getOperName())
            .between(operLogQuery != null && operLogQuery.getBeginTime() != null && operLogQuery.getEndTime() != null,
                SysOperLog::getOperTime, operLogQuery.getBeginTime(), operLogQuery.getEndTime());
        if (StringUtils.isBlank(pageQuery.getOrderByColumn())) {
            pageQuery.setOrderByColumn("oper_id");
            pageQuery.setIsAsc("desc");
        }
        Page<SysOperLogVo> page = baseMapper.selectVoPage(pageQuery.build(), lqw, SysOperLogVo.class);
        return TableDataInfo.build(page);
    }

    /**
     * 新增操作日志
     *
     * @param operLog 操作日志对象
     */
    @Override
    public void insertOperlog(SysOperLog operLog) {
        operLog.setOperTime(new Date());
        baseMapper.insert(operLog);
    }

    /**
     * 查询系统操作日志集合
     *
     * @param operLogQuery 操作日志查询对象
     * @return 操作日志集合
     */
    @Override
    public List<SysOperLog> selectOperLogList(SysOperLogQueryBo operLogQuery) {
        return baseMapper.selectList(new LambdaQueryWrapper<SysOperLog>()
            .like(operLogQuery != null && StringUtils.isNotBlank(operLogQuery.getTitle()), SysOperLog::getTitle, operLogQuery.getTitle())
            .eq(operLogQuery != null && operLogQuery.getBusinessType() != null,
                SysOperLog::getBusinessType, operLogQuery.getBusinessType())
            .func(f -> {
                if (operLogQuery != null && ArrayUtil.isNotEmpty(operLogQuery.getBusinessTypes())) {
                    f.in(SysOperLog::getBusinessType, Arrays.asList(operLogQuery.getBusinessTypes()));
                }
            })
            .eq(operLogQuery != null && operLogQuery.getStatus() != null,
                SysOperLog::getStatus, operLogQuery.getStatus())
            .like(operLogQuery != null && StringUtils.isNotBlank(operLogQuery.getOperName()), SysOperLog::getOperName, operLogQuery.getOperName())
            .between(operLogQuery != null && operLogQuery.getBeginTime() != null && operLogQuery.getEndTime() != null,
                SysOperLog::getOperTime, operLogQuery.getBeginTime(), operLogQuery.getEndTime())
            .orderByDesc(SysOperLog::getOperId));
    }

    /**
     * 批量删除系统操作日志
     *
     * @param operIds 需要删除的操作日志ID
     * @return 结果
     */
    @Override
    public int deleteOperLogByIds(Long[] operIds) {
        return baseMapper.deleteBatchIds(Arrays.asList(operIds));
    }

    /**
     * 查询操作日志详细
     *
     * @param operId 操作ID
     * @return 操作日志对象
     */
    @Override
    public SysOperLogVo selectOperLogById(Long operId) {
        return baseMapper.selectVoById(operId, SysOperLogVo.class);
    }

    /**
     * 清空操作日志
     */
    @Override
    public void cleanOperLog() {
        baseMapper.delete(new LambdaQueryWrapper<>());
    }
}
