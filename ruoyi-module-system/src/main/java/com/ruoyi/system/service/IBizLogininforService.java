package com.ruoyi.system.service;

import com.ruoyi.system.domain.BizLogininfor;
import com.ruoyi.system.domain.vo.BizLogininforVo;
import com.ruoyi.system.domain.bo.BizLogininforPageQueryBo;
import com.ruoyi.system.domain.bo.BizLogininforQueryBo;
import com.ruoyi.system.domain.bo.BizLogininforAddBo;
import com.ruoyi.system.domain.bo.BizLogininforEditBo;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.core.domain.bo.PageQuery;

import java.util.Collection;
import java.util.List;

/**
 * 业务用户登录记录Service接口
 *
 * @author weibocy
 * @date 2022-10-26
 */
public interface IBizLogininforService {

    /**
     * 查询业务用户登录记录
     * @param infoId 主键编号
     */
    BizLogininforVo queryById(Long infoId);

    /**
     * 查询业务用户登录记录分页列表
     * @param queryBo 业务用户登录记录业务查询对象
     * @param pageQuery 通用分页查询对象
     */
    TableDataInfo<BizLogininforVo> queryPageList(BizLogininforQueryBo queryBo, PageQuery pageQuery);

    /**
     * 查询业务用户登录记录列表
     * @param queryBo 业务用户登录记录业务查询对象
     */
    List<BizLogininforVo> queryList(BizLogininforQueryBo queryBo);

    /**
     * 新增业务用户登录记录
     * @param addBo 业务用户登录记录业务新增对象
     */
    Boolean insertByBo(BizLogininforAddBo addBo);

    /**
     * 修改业务用户登录记录
     * @param editBo 业务用户登录记录业务修改对象
     */
    Boolean updateByBo(BizLogininforEditBo editBo);

    /**
     * 校验并批量删除业务用户登录记录信息
     * @param ids 主键集合
     * @param isValid 是否检验?
     */
    Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid);
}
