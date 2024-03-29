package com.ruoyi.system.service.impl;

import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ruoyi.common.core.domain.bo.PageQuery;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.CommonYesOrNoEnum;
import com.ruoyi.common.exception.ServiceException;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.system.domain.SysPost;
import com.ruoyi.system.domain.SysUserPost;
import com.ruoyi.system.domain.bo.SysPostQueryBo;
import com.ruoyi.system.domain.vo.SysPostVo;
import com.ruoyi.system.mapper.SysPostMapper;
import com.ruoyi.system.mapper.SysUserPostMapper;
import com.ruoyi.system.service.ISysPostService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

/**
 * 岗位信息 服务层处理
 *
 * @author ruoyi
 * @author Lion Li
 */
@RequiredArgsConstructor
@Service
public class SysPostServiceImpl implements ISysPostService {

    private final SysPostMapper baseMapper;
    private final SysUserPostMapper userPostMapper;

    @Override
    public TableDataInfo<SysPostVo> selectPagePostList(SysPostQueryBo postQuery, PageQuery pageQuery) {
        if (ObjectUtil.isNull(postQuery)){
            postQuery = new SysPostQueryBo();
        }
        if (ObjectUtil.isNull(pageQuery)){
            pageQuery = new PageQuery();
        }
        LambdaQueryWrapper<SysPost> lqw = new LambdaQueryWrapper<SysPost>()
            .like(StringUtils.isNotBlank(postQuery.getPostCode()), SysPost::getPostCode, postQuery.getPostCode())
            .eq(postQuery.getStatus() != null, SysPost::getStatus, postQuery.getStatus())
            .like(StringUtils.isNotBlank(postQuery.getPostName()), SysPost::getPostName, postQuery.getPostName());
        Page<SysPostVo> page = baseMapper.selectVoPage(pageQuery.build(), lqw, SysPostVo.class);
        return TableDataInfo.build(page);
    }

    /**
     * 查询岗位信息集合
     *
     * @param postQuery 岗位信息查询对象
     * @return 岗位信息集合
     */
    @Override
    public List<SysPost> selectPostList(SysPostQueryBo postQuery) {
        if (ObjectUtil.isNull(postQuery)){
            postQuery = new SysPostQueryBo();
        }
        return baseMapper.selectList(new LambdaQueryWrapper<SysPost>()
            .like(StringUtils.isNotBlank(postQuery.getPostCode()), SysPost::getPostCode, postQuery.getPostCode())
            .eq(postQuery.getStatus() != null, SysPost::getStatus, postQuery.getStatus())
            .like(StringUtils.isNotBlank(postQuery.getPostName()), SysPost::getPostName, postQuery.getPostName()));
    }

    /**
     * 查询所有岗位
     *
     * @return 岗位列表
     */
    @Override
    public List<SysPost> selectPostAll() {
        return baseMapper.selectList();
    }

    /**
     * 通过岗位ID查询岗位信息
     *
     * @param postId 岗位ID
     * @return 角色对象信息
     */
    @Override
    public SysPostVo selectPostById(Long postId) {
        return baseMapper.selectVoById(postId, SysPostVo.class);
    }

    /**
     * 根据用户ID获取岗位选择框列表
     *
     * @param userId 用户ID
     * @return 选中岗位ID列表
     */
    @Override
    public List<Long> selectPostListByUserId(Long userId) {
        return baseMapper.selectPostListByUserId(userId);
    }

    /**
     * 校验岗位名称是否唯一
     *
     * @param post 岗位信息
     * @return 结果
     */
    @Override
    public CommonYesOrNoEnum checkPostNameUnique(SysPost post) {
        boolean exist = baseMapper.exists(new LambdaQueryWrapper<SysPost>()
            .eq(SysPost::getPostName, post.getPostName())
            .ne(ObjectUtil.isNotNull(post.getPostId()), SysPost::getPostId, post.getPostId()));
        if (exist) {
            return CommonYesOrNoEnum.NO;
        }
        return CommonYesOrNoEnum.YES;
    }

    /**
     * 校验岗位编码是否唯一
     *
     * @param post 岗位信息
     * @return 结果
     */
    @Override
    public CommonYesOrNoEnum checkPostCodeUnique(SysPost post) {
        boolean exist = baseMapper.exists(new LambdaQueryWrapper<SysPost>()
            .eq(SysPost::getPostCode, post.getPostCode())
            .ne(ObjectUtil.isNotNull(post.getPostId()), SysPost::getPostId, post.getPostId()));
        if (exist) {
            return CommonYesOrNoEnum.NO;
        }
        return CommonYesOrNoEnum.YES;
    }

    /**
     * 通过岗位ID查询岗位使用数量
     *
     * @param postId 岗位ID
     * @return 结果
     */
    @Override
    public long countUserPostById(Long postId) {
        return userPostMapper.selectCount(new LambdaQueryWrapper<SysUserPost>().eq(SysUserPost::getPostId, postId));
    }

    /**
     * 删除岗位信息
     *
     * @param postId 岗位ID
     * @return 结果
     */
    @Override
    public int deletePostById(Long postId) {
        return baseMapper.deleteById(postId);
    }

    /**
     * 批量删除岗位信息
     *
     * @param postIds 需要删除的岗位ID
     * @return 结果
     */
    @Override
    public int deletePostByIds(Long[] postIds) {
        for (Long postId : postIds) {
            SysPostVo post = selectPostById(postId);
            if (countUserPostById(postId) > 0) {
                throw new ServiceException(String.format("%1$s已分配,不能删除", post.getPostName()));
            }
        }
        return baseMapper.deleteBatchIds(Arrays.asList(postIds));
    }

    /**
     * 新增保存岗位信息
     *
     * @param post 岗位信息
     * @return 结果
     */
    @Override
    public int insertPost(SysPost post) {
        return baseMapper.insert(post);
    }

    /**
     * 修改保存岗位信息
     *
     * @param post 岗位信息
     * @return 结果
     */
    @Override
    public int updatePost(SysPost post) {
        return baseMapper.updateById(post);
    }
}
