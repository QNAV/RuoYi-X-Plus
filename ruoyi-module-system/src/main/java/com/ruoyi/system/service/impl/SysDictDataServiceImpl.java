package com.ruoyi.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ruoyi.common.constant.Constants;
import com.ruoyi.common.core.domain.bo.PageQuery;
import com.ruoyi.common.core.domain.entity.SysDictData;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.utils.redis.RedisUtils;
import com.ruoyi.system.domain.bo.SysDictDataQueryBo;
import com.ruoyi.common.core.domain.vo.SysDictDataVo;
import com.ruoyi.system.mapper.SysDictDataMapper;
import com.ruoyi.system.service.ISysDictDataService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 字典 业务层处理
 *
 * @author weibocy
 */
@RequiredArgsConstructor
@Service
public class SysDictDataServiceImpl implements ISysDictDataService {

    private final SysDictDataMapper baseMapper;

    @Override
    public TableDataInfo<SysDictDataVo> selectPageDictDataList(SysDictDataQueryBo dictDataQuery, PageQuery pageQuery) {
        LambdaQueryWrapper<SysDictData> lqw = new LambdaQueryWrapper<SysDictData>()
            .eq(dictDataQuery != null && StringUtils.isNotBlank(dictDataQuery.getDictType()), SysDictData::getDictType, dictDataQuery.getDictType())
            .like(dictDataQuery != null && StringUtils.isNotBlank(dictDataQuery.getDictLabel()), SysDictData::getDictLabel, dictDataQuery.getDictLabel())
            .eq(dictDataQuery != null && StringUtils.isNotBlank(dictDataQuery.getStatus()), SysDictData::getStatus, dictDataQuery.getStatus())
            .orderByAsc(SysDictData::getDictSort);
        Page<SysDictDataVo> page = baseMapper.selectVoPage(pageQuery.build(), lqw, SysDictDataVo.class);
        return TableDataInfo.build(page);
    }

    /**
     * 根据条件分页查询字典数据
     *
     * @param dictDataQuery 字典数据查询对象
     * @return 字典数据集合信息
     */
    @Override
    public List<SysDictData> selectDictDataList(SysDictDataQueryBo dictDataQuery) {
        return baseMapper.selectList(new LambdaQueryWrapper<SysDictData>()
            .eq(dictDataQuery != null && StringUtils.isNotBlank(dictDataQuery.getDictType()), SysDictData::getDictType, dictDataQuery.getDictType())
            .like(dictDataQuery != null && StringUtils.isNotBlank(dictDataQuery.getDictLabel()), SysDictData::getDictLabel, dictDataQuery.getDictLabel())
            .eq(dictDataQuery != null && StringUtils.isNotBlank(dictDataQuery.getStatus()), SysDictData::getStatus, dictDataQuery.getStatus())
            .orderByAsc(SysDictData::getDictSort));
    }

    /**
     * 根据字典类型和字典键值查询字典数据信息
     *
     * @param dictType  字典类型
     * @param dictValue 字典键值
     * @return 字典标签
     */
    @Override
    public String selectDictLabel(String dictType, String dictValue) {
        return baseMapper.selectOne(new LambdaQueryWrapper<SysDictData>()
                .select(SysDictData::getDictLabel)
                .eq(SysDictData::getDictType, dictType)
                .eq(SysDictData::getDictValue, dictValue))
            .getDictLabel();
    }

    /**
     * 根据字典数据ID查询信息
     *
     * @param dictCode 字典数据ID
     * @return 字典数据
     */
    @Override
    public SysDictDataVo selectDictDataById(Long dictCode) {
        return baseMapper.selectVoById(dictCode, SysDictDataVo.class);
    }

    /**
     * 批量删除字典数据信息
     *
     * @param dictCodes 需要删除的字典数据ID
     */
    @Override
    public void deleteDictDataByIds(Long[] dictCodes) {
        for (Long dictCode : dictCodes) {
            SysDictDataVo data = selectDictDataById(dictCode);
            baseMapper.deleteById(dictCode);
            List<SysDictDataVo> dictDatas = baseMapper.selectDictDataByType(data.getDictType());
            RedisUtils.setCacheObject(getCacheKey(data.getDictType()), dictDatas);
        }
    }

    /**
     * 新增保存字典数据信息
     *
     * @param data 字典数据信息
     * @return 结果
     */
    @Override
    public int insertDictData(SysDictData data) {
        int row = baseMapper.insert(data);
        if (row > 0) {
            List<SysDictDataVo> dictDatas = baseMapper.selectDictDataByType(data.getDictType());
            RedisUtils.setCacheObject(getCacheKey(data.getDictType()), dictDatas);
        }
        return row;
    }

    /**
     * 修改保存字典数据信息
     *
     * @param data 字典数据信息
     * @return 结果
     */
    @Override
    public int updateDictData(SysDictData data) {
        int row = baseMapper.updateById(data);
        if (row > 0) {
            List<SysDictDataVo> dictDatas = baseMapper.selectDictDataByType(data.getDictType());
            RedisUtils.setCacheObject(getCacheKey(data.getDictType()), dictDatas);
        }
        return row;
    }

    /**
     * 设置cache key
     *
     * @param configKey 参数键
     * @return 缓存键key
     */
    String getCacheKey(String configKey) {
        return Constants.SYS_DICT_KEY + configKey;
    }
}
