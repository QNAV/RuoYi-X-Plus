package com.ruoyi.sms.service;

import com.ruoyi.sms.domain.bo.SysSmsConfigEditBo;

import java.util.Collection;

/**
 * 短信存储配置Service接口
 *
 * @author Lion Li
 * @author 孤舟烟雨
 */
public interface ISysSmsConfigService {

    /**
     * 初始化OSS配置
     */
    void init();

    /**
     * 新增消息配置信息
     * @param bo
     * @return
     */
    Boolean insertByBo(SysSmsConfigEditBo bo);

    /**
     * 根据编辑业务对象修改短信配置
     *
     * @param bo 短信配置编辑业务对象
     * @return
     */
    Boolean updateByBo(SysSmsConfigEditBo bo);

    /**
     * 校验并删除数据
     *
     * @param ids     主键集合
     * @param isValid 是否校验,true-删除前校验,false-不校验
     * @return
     */
    Boolean deleteWithValidByIds(Collection<Long> ids);

    /**
     * 启用停用状态
     */
    int updateSmsConfigStatus(SysSmsConfigEditBo bo);

}
