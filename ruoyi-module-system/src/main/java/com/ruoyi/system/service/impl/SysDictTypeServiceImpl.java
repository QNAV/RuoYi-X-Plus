package com.ruoyi.system.service.impl;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ruoyi.common.constant.Constants;
import com.ruoyi.common.constant.UserConstants;
import com.ruoyi.common.core.domain.bo.PageQuery;
import com.ruoyi.common.core.domain.entity.SysDictData;
import com.ruoyi.common.core.domain.entity.SysDictType;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.core.service.DictService;
import com.ruoyi.common.exception.ServiceException;
import com.ruoyi.common.utils.BeanCopyUtils;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.utils.redis.RedisUtils;
import com.ruoyi.system.domain.bo.SysDictTypeQueryBo;
import com.ruoyi.common.core.domain.vo.SysDictDataVo;
import com.ruoyi.common.core.domain.vo.SysDictTypeVo;
import com.ruoyi.system.mapper.SysDictDataMapper;
import com.ruoyi.system.mapper.SysDictTypeMapper;
import com.ruoyi.system.service.ISysDictTypeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

/**
 * 字典 业务层处理
 *
 * @author weibocy
 */
@RequiredArgsConstructor
@Service
public class SysDictTypeServiceImpl implements ISysDictTypeService, DictService {

    private final SysDictTypeMapper baseMapper;
    private final SysDictDataMapper dictDataMapper;

    @Override
    public TableDataInfo<SysDictTypeVo> selectPageDictTypeList(SysDictTypeQueryBo dictTypeQuery, PageQuery pageQuery) {
        LambdaQueryWrapper<SysDictType> lqw = new LambdaQueryWrapper<SysDictType>()
            .like(dictTypeQuery != null && StringUtils.isNotBlank(dictTypeQuery.getDictName()), SysDictType::getDictName, dictTypeQuery.getDictName())
            .eq(dictTypeQuery != null && StringUtils.isNotBlank(dictTypeQuery.getStatus()), SysDictType::getStatus, dictTypeQuery.getStatus())
            .like(dictTypeQuery != null && StringUtils.isNotBlank(dictTypeQuery.getDictType()), SysDictType::getDictType, dictTypeQuery.getDictType())
            .between(dictTypeQuery != null && dictTypeQuery.getBeginTime() != null && dictTypeQuery.getEndTime() != null,
                SysDictType::getCreateTime, dictTypeQuery.getBeginTime(), dictTypeQuery.getEndTime());
        Page<SysDictTypeVo> page = baseMapper.selectVoPage(pageQuery.build(), lqw, SysDictTypeVo.class);
        return TableDataInfo.build(page);
    }

    /**
     * 根据条件分页查询字典类型
     *
     * @param dictTypeQuery 字典类型查询对象
     * @return 字典类型集合信息
     */
    @Override
    public List<SysDictType> selectDictTypeList(SysDictTypeQueryBo dictTypeQuery) {
        return baseMapper.selectList(new LambdaQueryWrapper<SysDictType>()
            .like(dictTypeQuery != null && StringUtils.isNotBlank(dictTypeQuery.getDictName()), SysDictType::getDictName, dictTypeQuery.getDictName())
            .eq(dictTypeQuery != null && StringUtils.isNotBlank(dictTypeQuery.getStatus()), SysDictType::getStatus, dictTypeQuery.getStatus())
            .like(dictTypeQuery != null && StringUtils.isNotBlank(dictTypeQuery.getDictType()), SysDictType::getDictType, dictTypeQuery.getDictType())
            .between(dictTypeQuery != null && dictTypeQuery.getBeginTime() != null && dictTypeQuery.getEndTime() != null,
                SysDictType::getCreateTime, dictTypeQuery.getBeginTime(), dictTypeQuery.getEndTime()));
    }

    /**
     * 根据所有字典类型
     *
     * @return 字典类型集合信息
     */
    @Override
    public List<SysDictType> selectDictTypeAll() {
        return baseMapper.selectList();
    }

    /**
     * 根据字典类型查询字典数据
     *
     * @param dictType 字典类型
     * @return 字典数据集合信息
     */
    @Override
    public List<SysDictDataVo> selectDictDataByType(String dictType) {
        List<SysDictData> dictDatas = RedisUtils.getCacheObject(getCacheKey(dictType));
        List<SysDictDataVo> dictDataVos = new ArrayList<>();
        if (CollUtil.isNotEmpty(dictDatas)) {
            dictDataVos = BeanCopyUtils.copyList(dictDatas, SysDictDataVo.class);
            return dictDataVos;
        }
        dictDataVos = dictDataMapper.selectDictDataByType(dictType);
        if (CollUtil.isNotEmpty(dictDatas)) {
            RedisUtils.setCacheObject(getCacheKey(dictType), dictDatas);
            return dictDataVos;
        }
        return null;
    }

    /**
     * 根据字典类型ID查询信息
     *
     * @param dictId 字典类型ID
     * @return 字典类型
     */
    @Override
    public SysDictTypeVo selectDictTypeById(Long dictId) {
        return baseMapper.selectVoById(dictId, SysDictTypeVo.class);
    }

    /**
     * 根据字典类型查询信息
     *
     * @param dictType 字典类型
     * @return 字典类型
     */
    @Override
    public SysDictType selectDictTypeByType(String dictType) {
        return baseMapper.selectById(new LambdaQueryWrapper<SysDictType>().eq(SysDictType::getDictType, dictType));
    }

    /**
     * 批量删除字典类型信息
     *
     * @param dictIds 需要删除的字典ID
     */
    @Override
    public void deleteDictTypeByIds(Long[] dictIds) {
        for (Long dictId : dictIds) {
            SysDictTypeVo dictType = selectDictTypeById(dictId);
            if (dictDataMapper.exists(new LambdaQueryWrapper<SysDictData>()
                .eq(SysDictData::getDictType, dictType.getDictType()))) {
                throw new ServiceException(String.format("%1$s已分配,不能删除", dictType.getDictName()));
            }
            RedisUtils.deleteObject(getCacheKey(dictType.getDictType()));
        }
        baseMapper.deleteBatchIds(Arrays.asList(dictIds));
    }

    /**
     * 加载字典缓存数据
     */
    @Override
    public void loadingDictCache() {
        List<SysDictData> dictDataList = dictDataMapper.selectList(
            new LambdaQueryWrapper<SysDictData>().eq(SysDictData::getStatus, UserConstants.DICT_NORMAL));
        Map<String, List<SysDictData>> dictDataMap = dictDataList.stream().collect(Collectors.groupingBy(SysDictData::getDictType));
        dictDataMap.forEach((k,v) -> {
            String dictKey = getCacheKey(k);
            List<SysDictData> dictList = v.stream()
                .sorted(Comparator.comparing(SysDictData::getDictSort))
                .collect(Collectors.toList());
            RedisUtils.setCacheObject(dictKey, dictList);
        });
    }

    /**
     * 清空字典缓存数据
     */
    @Override
    public void clearDictCache() {
        Collection<String> keys = RedisUtils.keys(Constants.SYS_DICT_KEY + "*");
        RedisUtils.deleteObject(keys);
    }

    /**
     * 重置字典缓存数据
     */
    @Override
    public void resetDictCache() {
        clearDictCache();
        loadingDictCache();
    }

    /**
     * 新增保存字典类型信息
     *
     * @param dict 字典类型信息
     * @return 结果
     */
    @Override
    public int insertDictType(SysDictType dict) {
        int row = baseMapper.insert(dict);
        if (row > 0) {
            RedisUtils.setCacheObject(getCacheKey(dict.getDictType()), null);
        }
        return row;
    }

    /**
     * 修改保存字典类型信息
     *
     * @param dict 字典类型信息
     * @return 结果
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public int updateDictType(SysDictType dict) {
        SysDictType oldDict = baseMapper.selectById(dict.getDictId());
        dictDataMapper.update(null, new LambdaUpdateWrapper<SysDictData>()
            .set(SysDictData::getDictType, dict.getDictType())
            .eq(SysDictData::getDictType, oldDict.getDictType()));
        int row = baseMapper.updateById(dict);
        if (row > 0) {
            List<SysDictDataVo> dictDatas = dictDataMapper.selectDictDataByType(dict.getDictType());
            RedisUtils.setCacheObject(getCacheKey(dict.getDictType()), dictDatas);
        }
        return row;
    }

    /**
     * 校验字典类型称是否唯一
     *
     * @param dict 字典类型
     * @return 结果
     */
    @Override
    public String checkDictTypeUnique(SysDictType dict) {
        boolean exist = baseMapper.exists(new LambdaQueryWrapper<SysDictType>()
            .eq(SysDictType::getDictType, dict.getDictType())
            .ne(ObjectUtil.isNotNull(dict.getDictId()), SysDictType::getDictId, dict.getDictId()));
        if (exist) {
            return UserConstants.NOT_UNIQUE;
        }
        return UserConstants.UNIQUE;
    }

    /**
     * 根据字典类型和字典值获取字典标签
     *
     * @param dictType  字典类型
     * @param dictValue 字典值
     * @param separator 分隔符
     * @return 字典标签
     */
    @Override
    public String getDictLabel(String dictType, String dictValue, String separator) {
        StringBuilder propertyString = new StringBuilder();
        List<SysDictDataVo> datas = selectDictDataByType(dictType);

        if (StringUtils.containsAny(dictValue, separator) && CollUtil.isNotEmpty(datas)) {
            for (SysDictDataVo dict : datas) {
                for (String value : dictValue.split(separator)) {
                    if (value.equals(dict.getDictValue())) {
                        propertyString.append(dict.getDictLabel() + separator);
                        break;
                    }
                }
            }
        } else {
            for (SysDictDataVo dict : datas) {
                if (dictValue.equals(dict.getDictValue())) {
                    return dict.getDictLabel();
                }
            }
        }
        return StringUtils.stripEnd(propertyString.toString(), separator);
    }

    /**
     * 根据字典类型和字典标签获取字典值
     *
     * @param dictType  字典类型
     * @param dictLabel 字典标签
     * @param separator 分隔符
     * @return 字典值
     */
    @Override
    public String getDictValue(String dictType, String dictLabel, String separator) {
        StringBuilder propertyString = new StringBuilder();
        List<SysDictDataVo> datas = selectDictDataByType(dictType);

        if (StringUtils.containsAny(dictLabel, separator) && CollUtil.isNotEmpty(datas)) {
            for (SysDictDataVo dict : datas) {
                for (String label : dictLabel.split(separator)) {
                    if (label.equals(dict.getDictLabel())) {
                        propertyString.append(dict.getDictValue() + separator);
                        break;
                    }
                }
            }
        } else {
            for (SysDictDataVo dict : datas) {
                if (dictLabel.equals(dict.getDictLabel())) {
                    return dict.getDictValue();
                }
            }
        }
        return StringUtils.stripEnd(propertyString.toString(), separator);
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
