package com.ruoyi.system.mapper;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.ruoyi.common.core.domain.entity.SysDictData;
import com.ruoyi.common.core.mapper.BaseMapperPlus;
import com.ruoyi.common.core.domain.vo.SysDictDataVo;
import com.ruoyi.common.enums.CommonNormalDisableEnum;

import java.util.List;

/**
 * 字典表 数据层
 *
 * @author ruoyi
 * @author Lion Li
 */
public interface SysDictDataMapper extends BaseMapperPlus<SysDictDataMapper, SysDictData, SysDictData> {

    default List<SysDictDataVo> selectDictDataByType(String dictType) {
        return selectVoList(
            new LambdaQueryWrapper<SysDictData>()
                .eq(SysDictData::getStatus, CommonNormalDisableEnum.NORMAL)
                .eq(SysDictData::getDictType, dictType)
                .orderByAsc(SysDictData::getDictSort), SysDictDataVo.class);
    }
}
