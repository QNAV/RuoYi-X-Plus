package com.ruoyi.system.service;

import com.ruoyi.common.core.domain.entity.BizUser;
import com.ruoyi.common.core.domain.vo.BizUserVo;
import com.ruoyi.system.domain.bo.BizUserPageQueryBo;
import com.ruoyi.system.domain.bo.BizUserQueryBo;
import com.ruoyi.system.domain.bo.BizUserAddBo;
import com.ruoyi.system.domain.bo.BizUserEditBo;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.core.domain.bo.PageQuery;

import java.util.Collection;
import java.util.List;

/**
 * 业务用户信息Service接口
 *
 * @author weibocy
 * @date 2022-10-25
 */
public interface IBizUserService {

    /**
     * 查询业务用户信息
     * @param userId 主键编号
     */
    BizUserVo queryById(Long userId);

    /**
     * 查询业务用户信息分页列表
     * @param queryBo 业务用户信息业务查询对象
     * @param pageQuery 通用分页查询对象
     */
    TableDataInfo<BizUserVo> queryPageList(BizUserQueryBo queryBo, PageQuery pageQuery);

    /**
     * 查询业务用户信息列表
     * @param queryBo 业务用户信息业务查询对象
     */
    List<BizUserVo> queryList(BizUserQueryBo queryBo);

    /**
     * 新增业务用户信息
     * @param addBo 业务用户信息业务新增对象
     */
    Boolean insertByBo(BizUserAddBo addBo);

    /**
     * 修改业务用户信息
     * @param editBo 业务用户信息业务修改对象
     */
    Boolean updateByBo(BizUserEditBo editBo);

    /**
     * 校验并批量删除业务用户信息信息
     * @param ids 主键集合
     * @param isValid 是否检验?
     */
    Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid);


    /**
     * 根据用户名查询业务用户信息
     * @param username 用户名
     * @return
     */
    BizUser selectUserByUserName(String username);

    /**
     * 根据手机号查询业务用户信息
     * @param phoneNumber 手机号
     * @return
     */
    BizUser selectUserByPhoneNumber(String phoneNumber);
}
