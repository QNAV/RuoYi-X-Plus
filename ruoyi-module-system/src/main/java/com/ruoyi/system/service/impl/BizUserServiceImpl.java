package com.ruoyi.system.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.ruoyi.common.core.domain.entity.BizUser;
import com.ruoyi.common.core.domain.vo.BizUserVo;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.core.domain.bo.PageQuery;
import com.ruoyi.common.utils.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.ruoyi.system.domain.bo.BizUserQueryBo;
import com.ruoyi.system.domain.bo.BizUserAddBo;
import com.ruoyi.system.domain.bo.BizUserEditBo;
import com.ruoyi.system.mapper.BizUserMapper;
import com.ruoyi.system.service.IBizUserService;

import java.util.List;
import java.util.Map;
import java.util.Collection;

/**
 * 业务用户信息Service业务层处理
 *
 * @author weibocy
 * @date 2022-10-25
 */
@RequiredArgsConstructor
@Service
public class BizUserServiceImpl implements IBizUserService {

    /**
     * 业务用户信息Mapper接口
     */
    private final BizUserMapper baseMapper;

    /**
     * 查询业务用户信息
     * @param userId 主键编号
     */
    @Override
    public BizUserVo queryById(Long userId){
        return baseMapper.selectVoById(userId);
    }

    /**
     * 查询业务用户信息分页列表
     * @param queryBo 业务用户信息业务查询对象
     * @param pageQuery 通用分页查询对象
     */
    @Override
    public TableDataInfo<BizUserVo> queryPageList(BizUserQueryBo queryBo, PageQuery pageQuery) {
        LambdaQueryWrapper<BizUser> lqw = buildQueryWrapper(queryBo);
        Page<BizUserVo> result = baseMapper.selectVoPage(pageQuery.build(), lqw);
        return TableDataInfo.build(result);
    }

    /**
     * 查询业务用户信息列表
     * @param queryBo 业务用户信息业务查询对象
     */
    @Override
    public List<BizUserVo> queryList(BizUserQueryBo queryBo) {
        LambdaQueryWrapper<BizUser> lqw = buildQueryWrapper(queryBo);
        return baseMapper.selectVoList(lqw);
    }

    /**
     * 构建业务用户信息查询包装器(后台专用)
     * @param queryBo 业务用户信息业务查询对象
     */
    private LambdaQueryWrapper<BizUser> buildQueryWrapper(BizUserQueryBo queryBo) {
        LambdaQueryWrapper<BizUser> lqw = Wrappers.lambdaQuery();
        lqw.eq(StringUtils.isNotBlank(queryBo.getAppid()), BizUser::getAppid, queryBo.getAppid());
        lqw.like(StringUtils.isNotBlank(queryBo.getOpenid()), BizUser::getOpenid, queryBo.getOpenid());
        lqw.like(StringUtils.isNotBlank(queryBo.getUserName()), BizUser::getUserName, queryBo.getUserName());
        lqw.like(StringUtils.isNotBlank(queryBo.getNickName()), BizUser::getNickName, queryBo.getNickName());
        lqw.like(StringUtils.isNotBlank(queryBo.getEmail()), BizUser::getEmail, queryBo.getEmail());
        lqw.like(StringUtils.isNotBlank(queryBo.getPhoneNumber()), BizUser::getPhoneNumber, queryBo.getPhoneNumber());
        lqw.eq(queryBo.getStatus() != null, BizUser::getStatus, queryBo.getStatus());
        return lqw;
    }


    /**
     * 构建业务用户信息查询包装器（业务端专用）
     * @param queryBo 业务用户信息业务查询对象
     */
    private LambdaQueryWrapper<BizUser> buildQueryWrapperByBiz(BizUserQueryBo queryBo) {
        LambdaQueryWrapper<BizUser> lqw = Wrappers.lambdaQuery();
        lqw.eq(StringUtils.isNotBlank(queryBo.getAppid()), BizUser::getAppid, queryBo.getAppid());
        lqw.eq(StringUtils.isNotBlank(queryBo.getOpenid()), BizUser::getOpenid, queryBo.getOpenid());
        lqw.eq(StringUtils.isNotBlank(queryBo.getUserName()), BizUser::getUserName, queryBo.getUserName());
        lqw.eq(StringUtils.isNotBlank(queryBo.getNickName()), BizUser::getNickName, queryBo.getNickName());
        lqw.eq(StringUtils.isNotBlank(queryBo.getEmail()), BizUser::getEmail, queryBo.getEmail());
        lqw.eq(StringUtils.isNotBlank(queryBo.getPhoneNumber()), BizUser::getPhoneNumber, queryBo.getPhoneNumber());
        lqw.eq(queryBo.getStatus() != null, BizUser::getStatus, queryBo.getStatus());
        return lqw;
    }

    /**
     * 新增业务用户信息
     * @param addBo 业务用户信息业务新增对象
     */
    @Override
    public Boolean insertByBo(BizUserAddBo addBo) {
        BizUser add = BeanUtil.toBean(addBo, BizUser.class);
        validEntityBeforeSave(add);
        boolean flag = baseMapper.insert(add) > 0;
        return flag;
    }

    /**
     * 修改业务用户信息
     * @param editBo 业务用户信息业务修改对象
     */
    @Override
    public Boolean updateByBo(BizUserEditBo editBo) {
        BizUser update = BeanUtil.toBean(editBo, BizUser.class);
        validEntityBeforeSave(update);
        return baseMapper.updateById(update) > 0;
    }

    /**
     * 保存前的数据校验
     * @param entity 业务用户信息实体对象
     */
    private void validEntityBeforeSave(BizUser entity){
        //TODO 做一些数据校验,如唯一约束
    }

    /**
     * 校验并批量删除业务用户信息信息
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

    /**
     * 根据用户名查询业务用户信息
     * @param username 用户名
     * @return
     */
    @Override
    public BizUser selectUserByUserName(String username) {
        BizUserQueryBo queryBo = new BizUserQueryBo();
        queryBo.setUserName(username);
        LambdaQueryWrapper<BizUser> lqw = buildQueryWrapperByBiz(queryBo);
        return baseMapper.selectOne(lqw);
    }

    /**
     * 根据手机号查询业务用户信息
     * @param phoneNumber 手机号
     * @return
     */
    @Override
    public BizUser selectUserByPhoneNumber(String phoneNumber) {
        BizUserQueryBo queryBo = new BizUserQueryBo();
        queryBo.setPhoneNumber(phoneNumber);
        LambdaQueryWrapper<BizUser> lqw = buildQueryWrapperByBiz(queryBo);
        // 兼容特殊情况(一个用户多个app一起用)
        lqw.last("limit 1");
        return baseMapper.selectOne(lqw);
    }

    /**
     * 根据手机号和appid查询业务用户信息
     * @param phoneNumber 手机号
     * @param appid appid
     * @return
     */
    @Override
    public BizUser selectUserByPhoneNumberAndAppid(String phoneNumber, String appid) {
        BizUserQueryBo queryBo = new BizUserQueryBo();
        queryBo.setPhoneNumber(phoneNumber);
        queryBo.setAppid(appid);
        LambdaQueryWrapper<BizUser> lqw = buildQueryWrapperByBiz(queryBo);
        return baseMapper.selectOne(lqw);
    }


    /**
     * 根据openid查询业务用户信息
     * @param openid openid
     * @return
     */
    @Override
    public BizUser selectUserByOpenid(String openid) {
        BizUserQueryBo queryBo = new BizUserQueryBo();
        queryBo.setOpenid(openid);
        LambdaQueryWrapper<BizUser> lqw = buildQueryWrapperByBiz(queryBo);
        return baseMapper.selectOne(lqw);
    }


    /**
     * 绑定微信登录信息
     *
     * @param appid appid
     * @param userId 用户编号
     * @param openid openid
     * @param unionid unionid
     */
    @Override
    public Boolean bindWeixin(String appid, Long userId, String openid, String unionid) {
        BizUserEditBo bo = new BizUserEditBo();
        bo.setAppid(appid);
        bo.setUserId(userId);
        bo.setOpenid(openid);
        bo.setUnionid(unionid);
        return updateByBo(bo);
    }


}
