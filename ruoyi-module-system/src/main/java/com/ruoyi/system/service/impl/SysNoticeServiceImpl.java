package com.ruoyi.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ruoyi.common.core.domain.bo.PageQuery;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.system.domain.SysNotice;
import com.ruoyi.system.domain.bo.SysNoticeQueryBo;
import com.ruoyi.system.domain.vo.SysNoticeVo;
import com.ruoyi.system.mapper.SysNoticeMapper;
import com.ruoyi.system.service.ISysNoticeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

/**
 * 公告 服务层实现
 *
 * @author weibocy
 */
@RequiredArgsConstructor
@Service
public class SysNoticeServiceImpl implements ISysNoticeService {

    private final SysNoticeMapper baseMapper;

    @Override
    public TableDataInfo<SysNoticeVo> selectPageNoticeList(SysNoticeQueryBo noticeQuery, PageQuery pageQuery) {
        LambdaQueryWrapper<SysNotice> lqw = new LambdaQueryWrapper<SysNotice>()
            .like(noticeQuery != null && StringUtils.isNotBlank(noticeQuery.getNoticeTitle()), SysNotice::getNoticeTitle, noticeQuery.getNoticeTitle())
            .eq(noticeQuery != null && StringUtils.isNotBlank(noticeQuery.getNoticeType()), SysNotice::getNoticeType, noticeQuery.getNoticeType())
            .like(noticeQuery != null && StringUtils.isNotBlank(noticeQuery.getCreateBy()), SysNotice::getCreateBy, noticeQuery.getCreateBy());
        Page<SysNoticeVo> page = baseMapper.selectVoPage(pageQuery.build(), lqw, SysNoticeVo.class);
        return TableDataInfo.build(page);
    }

    /**
     * 查询公告信息
     *
     * @param noticeId 公告ID
     * @return 公告信息
     */
    @Override
    public SysNoticeVo selectNoticeById(Long noticeId) {
        return baseMapper.selectVoById(noticeId, SysNoticeVo.class);
    }

    /**
     * 查询公告列表
     *
     * @param noticeQuery 公告查询对象
     * @return 公告集合
     */
    @Override
    public List<SysNotice> selectNoticeList(SysNoticeQueryBo noticeQuery) {
        return baseMapper.selectList(new LambdaQueryWrapper<SysNotice>()
            .like(StringUtils.isNotBlank(noticeQuery.getNoticeTitle()), SysNotice::getNoticeTitle, noticeQuery.getNoticeTitle())
            .eq(StringUtils.isNotBlank(noticeQuery.getNoticeType()), SysNotice::getNoticeType, noticeQuery.getNoticeType())
            .like(StringUtils.isNotBlank(noticeQuery.getCreateBy()), SysNotice::getCreateBy, noticeQuery.getCreateBy()));
    }

    /**
     * 新增公告
     *
     * @param notice 公告信息
     * @return 结果
     */
    @Override
    public int insertNotice(SysNotice notice) {
        return baseMapper.insert(notice);
    }

    /**
     * 修改公告
     *
     * @param notice 公告信息
     * @return 结果
     */
    @Override
    public int updateNotice(SysNotice notice) {
        return baseMapper.updateById(notice);
    }

    /**
     * 删除公告对象
     *
     * @param noticeId 公告ID
     * @return 结果
     */
    @Override
    public int deleteNoticeById(Long noticeId) {
        return baseMapper.deleteById(noticeId);
    }

    /**
     * 批量删除公告信息
     *
     * @param noticeIds 需要删除的公告ID
     * @return 结果
     */
    @Override
    public int deleteNoticeByIds(Long[] noticeIds) {
        return baseMapper.deleteBatchIds(Arrays.asList(noticeIds));
    }
}
