package com.ruoyi.system.service;

import com.ruoyi.common.core.domain.bo.PageQuery;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.system.domain.SysOperLog;
import com.ruoyi.system.domain.bo.SysOperLogQueryBo;
import com.ruoyi.system.domain.vo.SysOperLogVo;

import java.util.List;

/**
 * 操作日志 服务层
 *
 * @author ruoyi
 * @author Lion Li
 */
public interface ISysOperLogService {

    TableDataInfo<SysOperLogVo> selectPageOperLogList(SysOperLogQueryBo operLogQuery, PageQuery pageQuery);

    /**
     * 新增操作日志
     *
     * @param operLog 操作日志对象
     */
    void insertOperlog(SysOperLog operLog);

    /**
     * 查询系统操作日志集合
     *
     * @param operLogQuery 操作日志查询对象
     * @return 操作日志集合
     */
    List<SysOperLog> selectOperLogList(SysOperLogQueryBo operLogQuery);

    /**
     * 批量删除系统操作日志
     *
     * @param operIds 需要删除的操作日志ID
     * @return 结果
     */
    int deleteOperLogByIds(Long[] operIds);

    /**
     * 查询操作日志详细
     *
     * @param operId 操作ID
     * @return 操作日志对象
     */
    SysOperLogVo selectOperLogById(Long operId);

    /**
     * 清空操作日志
     */
    void cleanOperLog();
}
