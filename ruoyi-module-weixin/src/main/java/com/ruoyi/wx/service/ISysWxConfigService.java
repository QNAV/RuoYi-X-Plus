package com.ruoyi.wx.service;


import com.ruoyi.wx.domain.bo.SysWxConfigEditBo;

import java.util.Collection;

/**
 * WX配置服务层
 */
public interface ISysWxConfigService {

    /**
     * 初始化WX配置
     */
    void init();

    /**
     * 新增配置信息
     * @param bo 编辑业务对象
     * @return
     */
    Boolean insertByBo(SysWxConfigEditBo bo);

    /**
     * 修改配置
     *
     * @param bo 编辑业务对象
     * @return
     */
    Boolean updateByBo(SysWxConfigEditBo bo);

    /**
     * 校验并删除数据
     *
     * @param ids     主键集合
     * @return
     */
    Boolean deleteWithValidByIds(Collection<Long> ids);
}
