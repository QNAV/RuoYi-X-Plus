package com.ruoyi.demo.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.core.domain.bo.PageQuery;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.ruoyi.common.utils.StringUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.ruoyi.demo.domain.bo.TestDemoPageQueryBo;
import com.ruoyi.demo.domain.bo.TestDemoQueryBo;
import com.ruoyi.demo.domain.bo.TestDemoAddBo;
import com.ruoyi.demo.domain.bo.TestDemoEditBo;
import com.ruoyi.demo.domain.vo.TestDemoVo;
import com.ruoyi.demo.domain.TestDemo;
import com.ruoyi.demo.mapper.TestDemoMapper;
import com.ruoyi.demo.service.ITestDemoService;

import java.util.List;
import java.util.Map;
import java.util.Collection;

/**
 * 测试单Service业务层处理
 *
 * @author ruoyi
 * @date 2022-10-22
 */
@RequiredArgsConstructor
@Service
public class TestDemoServiceImpl implements ITestDemoService {

    /**
     * 测试单Mapper接口
     */
    private final TestDemoMapper baseMapper;

    /**
     * 查询测试单
     * @param id 主键编号
     */
    @Override
    public TestDemoVo queryById(Long id){
        return baseMapper.selectVoById(id);
    }

    /**
     * 查询测试单分页列表
     * @param queryBo 测试单业务查询对象
     * @param pageQuery 通用分页查询对象
     */
    @Override
    public TableDataInfo<TestDemoVo> queryPageList(TestDemoQueryBo queryBo, PageQuery pageQuery) {
        LambdaQueryWrapper<TestDemo> lqw = buildQueryWrapper(queryBo);
        Page<TestDemoVo> result = baseMapper.selectVoPage(pageQuery.build(), lqw);
        return TableDataInfo.build(result);
    }

    /**
     * 查询测试单列表
     * @param queryBo 测试单业务查询对象
     */
    @Override
    public List<TestDemoVo> queryList(TestDemoQueryBo queryBo) {
        LambdaQueryWrapper<TestDemo> lqw = buildQueryWrapper(queryBo);
        return baseMapper.selectVoList(lqw);
    }

    /**
     * 构建测试单查询包装器
     * @param queryBo 测试单业务查询对象
     */
    private LambdaQueryWrapper<TestDemo> buildQueryWrapper(TestDemoQueryBo queryBo) {
        LambdaQueryWrapper<TestDemo> lqw = Wrappers.lambdaQuery();
        lqw.eq(queryBo.getDeptId() != null, TestDemo::getDeptId, queryBo.getDeptId());
        lqw.eq(queryBo.getUserId() != null, TestDemo::getUserId, queryBo.getUserId());
        lqw.eq(queryBo.getOrderNum() != null, TestDemo::getOrderNum, queryBo.getOrderNum());
        lqw.eq(StringUtils.isNotBlank(queryBo.getTestKey()), TestDemo::getTestKey, queryBo.getTestKey());
        lqw.eq(StringUtils.isNotBlank(queryBo.getValue()), TestDemo::getValue, queryBo.getValue());
        return lqw;
    }

    /**
     * 新增测试单
     * @param addBo 测试单业务新增对象
     */
    @Override
    public Boolean insertByBo(TestDemoAddBo addBo) {
        TestDemo add = BeanUtil.toBean(addBo, TestDemo.class);
        validEntityBeforeSave(add);
        boolean flag = baseMapper.insert(add) > 0;
        if (flag) {
            addBo.setId(add.getId());
        }
        return flag;
    }

    /**
     * 修改测试单
     * @param editBo 测试单业务修改对象
     */
    @Override
    public Boolean updateByBo(TestDemoEditBo editBo) {
        TestDemo update = BeanUtil.toBean(editBo, TestDemo.class);
        validEntityBeforeSave(update);
        return baseMapper.updateById(update) > 0;
    }

    /**
     * 保存前的数据校验
     * @param entity 测试单实体对象
     */
    private void validEntityBeforeSave(TestDemo entity){
        //TODO 做一些数据校验,如唯一约束
    }

    /**
     * 校验并批量删除测试单信息
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
}
