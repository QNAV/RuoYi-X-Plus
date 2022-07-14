package com.ruoyi.demo.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ruoyi.common.core.domain.bo.PageQuery;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.demo.domain.TestDemo;
import com.ruoyi.demo.domain.bo.TestDemoBo;
import com.ruoyi.demo.domain.to.TestDemoQuery;
import com.ruoyi.demo.domain.vo.TestDemoVo;
import com.ruoyi.demo.mapper.TestDemoMapper;
import com.ruoyi.demo.service.ITestDemoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

/**
 * 测试单表Service业务层处理
 *
 * @author weibocy
 * @date 2021-07-26
 */
@RequiredArgsConstructor
@Service
public class TestDemoServiceImpl implements ITestDemoService {

    private final TestDemoMapper baseMapper;

    @Override
    public TestDemoVo queryById(Long id) {
        return baseMapper.selectVoById(id);
    }

    @Override
    public TableDataInfo<TestDemoVo> queryPageList(TestDemoQuery query, PageQuery pageQuery) {
        LambdaQueryWrapper<TestDemo> lqw = buildQueryWrapper(query);
        Page<TestDemoVo> result = baseMapper.selectVoPage(pageQuery.build(), lqw);
        return TableDataInfo.build(result);
    }

    /**
     * 自定义分页查询
     */
    @Override
    public TableDataInfo<TestDemoVo> customPageList(TestDemoQuery query, PageQuery pageQuery) {
        LambdaQueryWrapper<TestDemo> lqw = buildQueryWrapper(query);
        Page<TestDemoVo> result = baseMapper.customPageList(pageQuery.build(), lqw);
        return TableDataInfo.build(result);
    }

    @Override
    public List<TestDemoVo> queryList(TestDemoQuery query) {
        return baseMapper.selectVoList(buildQueryWrapper(query));
    }

    private LambdaQueryWrapper<TestDemo> buildQueryWrapper(TestDemoQuery query) {
        LambdaQueryWrapper<TestDemo> lqw = Wrappers.lambdaQuery();
        lqw.like(StringUtils.isNotBlank(query.getTestKey()), TestDemo::getTestKey, query.getTestKey());
        lqw.eq(StringUtils.isNotBlank(query.getValue()), TestDemo::getValue, query.getValue());
        lqw.between(query.getBeginCreateTime() != null && query.getEndCreateTime() != null,
            TestDemo::getCreateTime, query.getBeginCreateTime(), query.getEndCreateTime());
        return lqw;
    }

    @Override
    public Boolean insertByBo(TestDemoBo bo) {
        TestDemo add = BeanUtil.toBean(bo, TestDemo.class);
        validEntityBeforeSave(add);
        boolean flag = baseMapper.insert(add) > 0;
        if (flag) {
            bo.setId(add.getId());
        }
        return flag;
    }

    @Override
    public Boolean updateByBo(TestDemoBo bo) {
        TestDemo update = BeanUtil.toBean(bo, TestDemo.class);
        validEntityBeforeSave(update);
        return baseMapper.updateById(update) > 0;
    }

    /**
     * 保存前的数据校验
     *
     * @param entity 实体类数据
     */
    private void validEntityBeforeSave(TestDemo entity) {
        //TODO 做一些数据校验,如唯一约束
    }

    @Override
    public Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid) {
        if (isValid) {
            //TODO 做一些业务上的校验,判断是否需要校验
        }
        return baseMapper.deleteBatchIds(ids) > 0;
    }

    @Override
    public Boolean saveBatch(List<TestDemo> list) {
        return baseMapper.insertBatch(list);
    }
}
