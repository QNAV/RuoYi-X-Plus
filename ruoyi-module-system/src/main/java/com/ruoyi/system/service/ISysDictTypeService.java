package com.ruoyi.system.service;

import com.ruoyi.common.core.domain.bo.PageQuery;
import com.ruoyi.common.core.domain.entity.SysDictType;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.CommonYesOrNoEnum;
import com.ruoyi.system.domain.bo.SysDictTypeQueryBo;
import com.ruoyi.common.core.domain.vo.SysDictDataVo;
import com.ruoyi.common.core.domain.vo.SysDictTypeVo;

import java.util.List;

/**
 * 字典 业务层
 *
 * @author ruoyi
 * @author Lion Li
 */
public interface ISysDictTypeService {


    TableDataInfo<SysDictTypeVo> selectPageDictTypeList(SysDictTypeQueryBo dictTypeQuery, PageQuery pageQuery);

    /**
     * 根据条件分页查询字典类型
     *
     * @param dictTypeQuery 字典类型查询对象
     * @return 字典类型集合信息
     */
    List<SysDictType> selectDictTypeList(SysDictTypeQueryBo dictTypeQuery);

    /**
     * 根据所有字典类型
     *
     * @return 字典类型集合信息
     */
    List<SysDictType> selectDictTypeAll();

    /**
     * 根据字典类型查询字典数据
     *
     * @param dictType 字典类型
     * @return 字典数据集合信息
     */
    List<SysDictDataVo> selectDictDataByType(String dictType);

    /**
     * 根据字典类型ID查询信息
     *
     * @param dictId 字典类型ID
     * @return 字典类型
     */
    SysDictTypeVo selectDictTypeById(Long dictId);

    /**
     * 根据字典类型查询信息
     *
     * @param dictType 字典类型
     * @return 字典类型
     */
    SysDictType selectDictTypeByType(String dictType);

    /**
     * 批量删除字典信息
     *
     * @param dictIds 需要删除的字典ID
     */
    void deleteDictTypeByIds(Long[] dictIds);

    /**
     * 加载字典缓存数据
     */
    void loadingDictCache();

    /**
     * 清空字典缓存数据
     */
    void clearDictCache();

    /**
     * 重置字典缓存数据
     */
    void resetDictCache();

    /**
     * 新增保存字典类型信息
     *
     * @param dictType 字典类型信息
     * @return 结果
     */
    List<SysDictDataVo> insertDictType(SysDictType dictType);

    /**
     * 修改保存字典类型信息
     *
     * @param dictType 字典类型信息
     * @return 结果
     */
    List<SysDictDataVo> updateDictType(SysDictType dictType);

    /**
     * 校验字典类型称是否唯一
     *
     * @param dictType 字典类型
     * @return 结果
     */
    CommonYesOrNoEnum checkDictTypeUnique(SysDictType dictType);
}
