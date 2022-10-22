package com.ruoyi.demo.service;

import com.ruoyi.demo.domain.TestDemo;
import com.ruoyi.demo.domain.vo.TestDemoVo;
import com.ruoyi.demo.domain.bo.TestDemoPageQueryBo;
import com.ruoyi.demo.domain.bo.TestDemoQueryBo;
import com.ruoyi.demo.domain.bo.TestDemoAddBo;
import com.ruoyi.demo.domain.bo.TestDemoEditBo;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.core.domain.bo.PageQuery;

import java.util.Collection;
import java.util.List;

/**
 * 测试单Service接口
 *
 * @author ruoyi
 * @date 2022-10-22
 */
public interface ITestDemoService {

    /**
     * 查询测试单
     * @param id 主键编号
     */
    TestDemoVo queryById(Long id);

    /**
     * 查询测试单分页列表
     * @param queryBo 测试单业务查询对象
     * @param pageQuery 通用分页查询对象
     */
    TableDataInfo<TestDemoVo> queryPageList(TestDemoQueryBo queryBo, PageQuery pageQuery);

    /**
     * 查询测试单列表
     * @param queryBo 测试单业务查询对象
     */
    List<TestDemoVo> queryList(TestDemoQueryBo queryBo);

    /**
     * 新增测试单
     * @param addBo 测试单业务新增对象
     */
    Boolean insertByBo(TestDemoAddBo addBo);

    /**
     * 修改测试单
     * @param editBo 测试单业务修改对象
     */
    Boolean updateByBo(TestDemoEditBo editBo);

    /**
     * 校验并批量删除测试单信息
     * @param ids 主键集合
     * @param isValid 是否检验?
     */
    Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid);
}
