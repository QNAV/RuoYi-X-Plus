package com.ruoyi.system.service;

import com.ruoyi.common.core.domain.bo.PageQuery;
import com.ruoyi.common.core.domain.entity.SysDictData;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.system.domain.bo.SysDictDataQueryBo;
import com.ruoyi.common.core.domain.vo.SysDictDataVo;

import java.util.List;

/**
 * 字典 业务层
 *
 * @author weibocy
 */
public interface ISysDictDataService {


    TableDataInfo<SysDictDataVo> selectPageDictDataList(SysDictDataQueryBo dictDataQuery, PageQuery pageQuery);

    /**
     * 根据条件分页查询字典数据
     *
     * @param dictDataQuery 字典数据查询对象
     * @return 字典数据集合信息
     */
    List<SysDictData> selectDictDataList(SysDictDataQueryBo dictDataQuery);

    /**
     * 根据字典类型和字典键值查询字典数据信息
     *
     * @param dictType  字典类型
     * @param dictValue 字典键值
     * @return 字典标签
     */
    String selectDictLabel(String dictType, String dictValue);

    /**
     * 根据字典数据ID查询信息
     *
     * @param dictCode 字典数据ID
     * @return 字典数据
     */
    SysDictDataVo selectDictDataById(Long dictCode);

    /**
     * 批量删除字典数据信息
     *
     * @param dictCodes 需要删除的字典数据ID
     */
    void deleteDictDataByIds(Long[] dictCodes);

    /**
     * 新增保存字典数据信息
     *
     * @param dictData 字典数据信息
     * @return 结果
     */
    int insertDictData(SysDictData dictData);

    /**
     * 修改保存字典数据信息
     *
     * @param dictData 字典数据信息
     * @return 结果
     */
    int updateDictData(SysDictData dictData);
}
