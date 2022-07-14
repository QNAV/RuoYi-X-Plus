package com.ruoyi.demo.service;

import com.ruoyi.common.core.domain.bo.PageQuery;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.demo.domain.TestDemo;
import com.ruoyi.demo.domain.bo.TestDemoBo;
import com.ruoyi.demo.domain.to.TestDemoQuery;
import com.ruoyi.demo.domain.vo.TestDemoVo;

import java.util.Collection;
import java.util.List;

/**
 * 测试单表Service接口
 *
 * @author weibocy
 * @date 2021-07-26
 */
public interface ITestDemoService {

    /**
     * 查询单个
     *
     * @return
     */
    TestDemoVo queryById(Long id);

    /**
     * 查询列表
     */
    TableDataInfo<TestDemoVo> queryPageList(TestDemoQuery query, PageQuery pageQuery);

    /**
     * 自定义分页查询
     */
    TableDataInfo<TestDemoVo> customPageList(TestDemoQuery query, PageQuery pageQuery);

    /**
     * 查询列表
     */
    List<TestDemoVo> queryList(TestDemoQuery query);

    /**
     * 根据新增业务对象插入测试单表
     *
     * @param bo 测试单表新增业务对象
     * @return
     */
    Boolean insertByBo(TestDemoBo bo);

    /**
     * 根据编辑业务对象修改测试单表
     *
     * @param bo 测试单表编辑业务对象
     * @return
     */
    Boolean updateByBo(TestDemoBo bo);

    /**
     * 校验并删除数据
     *
     * @param ids     主键集合
     * @param isValid 是否校验,true-删除前校验,false-不校验
     * @return
     */
    Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid);

    /**
     * 批量保存
     */
    Boolean saveBatch(List<TestDemo> list);
}
