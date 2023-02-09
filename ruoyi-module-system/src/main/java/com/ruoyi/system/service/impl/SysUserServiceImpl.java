package com.ruoyi.system.service.impl;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.ArrayUtil;
import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ruoyi.admin.helper.AdminLoginHelper;
import com.ruoyi.common.constant.CacheNames;
import com.ruoyi.common.constant.UserConstants;
import com.ruoyi.common.core.domain.bo.PageQuery;
import com.ruoyi.common.core.domain.entity.SysDept;
import com.ruoyi.common.core.domain.entity.SysRole;
import com.ruoyi.common.core.domain.entity.SysUser;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.core.service.UserService;
import com.ruoyi.common.exception.ServiceException;
import com.ruoyi.common.helper.DataBaseHelper;
import com.ruoyi.common.utils.BeanCopyUtils;
import com.ruoyi.common.utils.StreamUtils;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.system.domain.SysPost;
import com.ruoyi.system.domain.SysUserPost;
import com.ruoyi.system.domain.SysUserRole;
import com.ruoyi.system.domain.bo.SysUserEditBo;
import com.ruoyi.system.domain.bo.SysUserQueryBo;
import com.ruoyi.common.core.domain.vo.SysUserVo;
import com.ruoyi.system.mapper.*;
import com.ruoyi.system.service.ISysUserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;

/**
 * 用户 业务层处理
 *
 * @author ruoyi
 * @author Lion Li
 */
@Slf4j
@RequiredArgsConstructor
@Service
public class SysUserServiceImpl implements ISysUserService, UserService {

    private final SysUserMapper baseMapper;
    private final SysDeptMapper deptMapper;
    private final SysRoleMapper roleMapper;
    private final SysPostMapper postMapper;
    private final SysUserRoleMapper userRoleMapper;
    private final SysUserPostMapper userPostMapper;

    @Override
    public TableDataInfo<SysUser> selectPageUserList(SysUserQueryBo userQuery, PageQuery pageQuery) {
        Page<SysUser> page = baseMapper.selectPageUserList(pageQuery.build(), this.buildQueryWrapper(userQuery));
        return TableDataInfo.build(page);
    }

    @Override
    public TableDataInfo<SysUserVo> selectPageUserVoList(SysUserQueryBo userQuery, PageQuery pageQuery) {
        Page<SysUserVo> page = baseMapper.selectPageUserVoList(pageQuery.build(), this.buildQueryWrapper(userQuery));
        return TableDataInfo.build(page);
    }

    /**
     * 根据条件分页查询用户列表
     *
     * @param userQuery 用户信息查询对象
     * @return 用户信息集合信息
     */
    @Override
    public List<SysUser> selectUserList(SysUserQueryBo userQuery) {
        return baseMapper.selectUserList(this.buildQueryWrapper(userQuery));
    }

    private Wrapper<SysUser> buildQueryWrapper(SysUserQueryBo userQuery) {
        QueryWrapper<SysUser> wrapper = Wrappers.query();
        wrapper.eq("u.del_flag", UserConstants.USER_NORMAL)
            .eq(userQuery != null && ObjectUtil.isNotNull(userQuery.getUserId()), "u.user_id", userQuery.getUserId())
            .like(userQuery != null && StringUtils.isNotBlank(userQuery.getUserName()), "u.user_name", userQuery.getUserName())
            .eq(userQuery != null && StringUtils.isNotBlank(userQuery.getStatus()), "u.status", userQuery.getStatus())
            .like(userQuery != null && StringUtils.isNotBlank(userQuery.getPhoneNumber()), "u.phone_number", userQuery.getPhoneNumber())
            .between(userQuery != null && userQuery.getBeginCreateTime() != null && userQuery.getEndCreateTime() != null,
                "u.create_time", userQuery.getBeginCreateTime(), userQuery.getEndCreateTime())
            .and(userQuery != null && ObjectUtil.isNotNull(userQuery.getDeptId()), w -> {
                List<SysDept> deptList = deptMapper.selectList(new LambdaQueryWrapper<SysDept>()
                    .select(SysDept::getDeptId)
                    .apply(DataBaseHelper.findInSet(userQuery.getDeptId(), "ancestors")));
                List<Long> ids = StreamUtils.toList(deptList, SysDept::getDeptId);
                ids.add(userQuery.getDeptId());
                w.in("u.dept_id", ids);
            });
        return wrapper;
    }

    /**
     * 根据条件分页查询已分配用户角色列表
     *
     * @param userQuery 用户信息查询对象
     * @return 用户信息集合信息
     */
    @Override
    public TableDataInfo<SysUser> selectAllocatedList(SysUserQueryBo userQuery, PageQuery pageQuery) {
        QueryWrapper<SysUser> wrapper = Wrappers.query();
        wrapper.eq("u.del_flag", UserConstants.USER_NORMAL)
            .eq(userQuery != null && ObjectUtil.isNotNull(userQuery.getRoleId()), "r.role_id", userQuery.getRoleId())
            .like(userQuery != null && StringUtils.isNotBlank(userQuery.getUserName()), "u.user_name", userQuery.getUserName())
            .eq(userQuery != null && StringUtils.isNotBlank(userQuery.getStatus()), "u.status", userQuery.getStatus())
            .like(userQuery != null && StringUtils.isNotBlank(userQuery.getPhoneNumber()), "u.phone_number", userQuery.getPhoneNumber());
        Page<SysUser> page = baseMapper.selectAllocatedList(pageQuery.build(), wrapper);
        return TableDataInfo.build(page);
    }

    /**
     * 根据条件分页查询已分配用户角色列表
     *
     * @param userQuery 用户信息查询对象
     * @return 用户信息集合信息
     */
    @Override
    public TableDataInfo<SysUserVo> selectAllocatedVoList(SysUserQueryBo userQuery, PageQuery pageQuery) {
        QueryWrapper<SysUser> wrapper = Wrappers.query();
        wrapper.eq("u.del_flag", UserConstants.USER_NORMAL)
            .eq(userQuery != null && ObjectUtil.isNotNull(userQuery.getRoleId()), "r.role_id", userQuery.getRoleId())
            .like(userQuery != null && StringUtils.isNotBlank(userQuery.getUserName()), "u.user_name", userQuery.getUserName())
            .eq(userQuery != null && StringUtils.isNotBlank(userQuery.getStatus()), "u.status", userQuery.getStatus())
            .like(userQuery != null && StringUtils.isNotBlank(userQuery.getPhoneNumber()), "u.phone_number", userQuery.getPhoneNumber());
        Page<SysUserVo> page = baseMapper.selectAllocatedVoList(pageQuery.build(), wrapper);
        return TableDataInfo.build(page);
    }

    /**
     * 根据条件分页查询未分配用户角色列表
     *
     * @param userQuery 用户信息查询对象
     * @return 用户信息集合信息
     */
    @Override
    public TableDataInfo<SysUser> selectUnallocatedList(SysUserQueryBo userQuery, PageQuery pageQuery) {
        List<Long> userIds = userRoleMapper.selectUserIdsByRoleId(userQuery.getRoleId());
        QueryWrapper<SysUser> wrapper = Wrappers.query();
        wrapper.eq("u.del_flag", UserConstants.USER_NORMAL)
            .and(w -> w.ne("r.role_id", userQuery.getRoleId()).or().isNull("r.role_id"))
            .notIn(CollUtil.isNotEmpty(userIds), "u.user_id", userIds)
            .like(userQuery != null && StringUtils.isNotBlank(userQuery.getUserName()), "u.user_name", userQuery.getUserName())
            .like(userQuery != null && StringUtils.isNotBlank(userQuery.getPhoneNumber()), "u.phone_number", userQuery.getPhoneNumber());
        Page<SysUser> page = baseMapper.selectUnallocatedList(pageQuery.build(), wrapper);
        return TableDataInfo.build(page);
    }


    /**
     * 根据条件分页查询未分配用户角色列表
     *
     * @param userQuery 用户信息查询对象
     * @return 用户信息集合信息
     */
    @Override
    public TableDataInfo<SysUserVo> selectUnallocatedVoList(SysUserQueryBo userQuery, PageQuery pageQuery) {
        List<Long> userIds = userRoleMapper.selectUserIdsByRoleId(userQuery.getRoleId());
        QueryWrapper<SysUser> wrapper = Wrappers.query();
        wrapper.eq("u.del_flag", UserConstants.USER_NORMAL)
            .and(w -> w.ne("r.role_id", userQuery.getRoleId()).or().isNull("r.role_id"))
            .notIn(CollUtil.isNotEmpty(userIds), "u.user_id", userIds)
            .like(userQuery != null && StringUtils.isNotBlank(userQuery.getUserName()), "u.user_name", userQuery.getUserName())
            .like(userQuery != null && StringUtils.isNotBlank(userQuery.getPhoneNumber()), "u.phone_number", userQuery.getPhoneNumber());
        Page<SysUserVo> page = baseMapper.selectUnallocatedVoList(pageQuery.build(), wrapper);
        return TableDataInfo.build(page);
    }

    /**
     * 通过用户名查询用户
     *
     * @param userName 用户名
     * @return 用户对象信息
     */
    @Override
    public SysUser selectUserByUserName(String userName) {
        return baseMapper.selectUserByUserName(userName);
    }

    /**
     * 通过手机号查询用户
     *
     * @param phoneNumber 手机号
     * @return 用户对象信息
     */
    @Override
    public SysUser selectUserByPhoneNumber(String phoneNumber) {
        return baseMapper.selectUserByPhoneNumber(phoneNumber);
    }

    /**
     * 通过用户ID查询用户
     *
     * @param userId 用户ID
     * @return 用户对象信息
     */
    @Override
    public SysUser selectUserById(Long userId) {
        return baseMapper.selectUserById(userId);
    }

    /**
     * 通过用户ID查询用户
     *
     * @param userId 用户ID
     * @return 用户信息视图对象
     */
    @Override
    public SysUserVo selectUserVoById(Long userId) {
        return baseMapper.selectUserVoById(userId);
    }

    /**
     * 查询用户所属角色组
     *
     * @param userName 用户名
     * @return 结果
     */
    @Override
    public String selectUserRoleGroup(String userName) {
        List<SysRole> list = roleMapper.selectRolesByUserName(userName);
        if (CollUtil.isEmpty(list)) {
            return StringUtils.EMPTY;
        }
        return StreamUtils.join(list, SysRole::getRoleName);
    }

    /**
     * 查询用户所属岗位组
     *
     * @param userName 用户名
     * @return 结果
     */
    @Override
    public String selectUserPostGroup(String userName) {
        List<SysPost> list = postMapper.selectPostsByUserName(userName);
        if (CollUtil.isEmpty(list)) {
            return StringUtils.EMPTY;
        }
        return StreamUtils.join(list, SysPost::getPostName);
    }

    /**
     * 校验用户名称是否唯一
     *
     * @param user 用户信息
     * @return 结果
     */
    @Override
    public String checkUserNameUnique(SysUser user) {
        boolean exist = baseMapper.exists(new LambdaQueryWrapper<SysUser>()
                .eq(SysUser::getUserName, user.getUserName())
                .ne(ObjectUtil.isNotNull(user.getUserId()), SysUser::getUserId, user.getUserId()));
        if (exist) {
            return UserConstants.NOT_UNIQUE;
        }
        return UserConstants.UNIQUE;
    }

    /**
     * 校验手机号码是否唯一
     *
     * @param user 用户信息
     */
    @Override
    public String checkPhoneUnique(SysUser user) {
        boolean exist = baseMapper.exists(new LambdaQueryWrapper<SysUser>()
            .eq(SysUser::getPhoneNumber, user.getPhoneNumber())
            .ne(ObjectUtil.isNotNull(user.getUserId()), SysUser::getUserId, user.getUserId()));
        if (exist) {
            return UserConstants.NOT_UNIQUE;
        }
        return UserConstants.UNIQUE;
    }

    /**
     * 校验email是否唯一
     *
     * @param user 用户信息
     */
    @Override
    public String checkEmailUnique(SysUser user) {
        boolean exist = baseMapper.exists(new LambdaQueryWrapper<SysUser>()
            .eq(SysUser::getEmail, user.getEmail())
            .ne(ObjectUtil.isNotNull(user.getUserId()), SysUser::getUserId, user.getUserId()));
        if (exist) {
            return UserConstants.NOT_UNIQUE;
        }
        return UserConstants.UNIQUE;
    }

    /**
     * 校验用户是否允许操作
     *
     * @param userBo 用户信息
     */
    @Override
    public void checkUserAllowed(SysUserEditBo userBo) {
        if (ObjectUtil.isNotNull(userBo.getUserId()) && userBo.isAdmin()) {
            throw new ServiceException("不允许操作超级管理员用户");
        }
    }

    /**
     * 校验用户是否有数据权限
     *
     * @param userId 用户id
     */
    @Override
    public void checkUserDataScope(Long userId) {
        if (!AdminLoginHelper.isAdmin()) {
            SysUserQueryBo user = new SysUserQueryBo();
            user.setUserId(userId);
            List<SysUser> users = this.selectUserList(user);
            if (CollUtil.isEmpty(users)) {
                throw new ServiceException("没有权限访问用户数据！");
            }
        }
    }

    /**
     * 新增保存用户信息
     *
     * @param user 用户信息
     * @return 结果
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public int insertUser(SysUser user) {
        // 新增用户信息
        int rows = baseMapper.insert(user);
        // 新增用户岗位关联
        insertUserPost(user);
        // 新增用户与角色管理
        insertUserRole(user);
        return rows;
    }

    /**
     * 注册用户信息
     *
     * @param user 用户信息
     * @return 结果
     */
    @Override
    public boolean registerUser(SysUser user) {
        user.setCreateBy(user.getUserName());
        user.setUpdateBy(user.getUserName());
        return baseMapper.insert(user) > 0;
    }

    /**
     * 修改保存用户信息
     *
     * @param user 用户信息
     * @return 结果
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public int updateUser(SysUser user) {
        Long userId = user.getUserId();
        // 删除用户与角色关联
        userRoleMapper.delete(new LambdaQueryWrapper<SysUserRole>().eq(SysUserRole::getUserId, userId));
        // 新增用户与角色管理
        insertUserRole(user);
        // 删除用户与岗位关联
        userPostMapper.delete(new LambdaQueryWrapper<SysUserPost>().eq(SysUserPost::getUserId, userId));
        // 新增用户与岗位管理
        insertUserPost(user);
        return baseMapper.updateById(user);
    }

    /**
     * 用户授权角色
     *
     * @param userId  用户ID
     * @param roleIds 角色组
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void insertUserAuth(Long userId, Long[] roleIds) {
        userRoleMapper.delete(new LambdaQueryWrapper<SysUserRole>()
            .eq(SysUserRole::getUserId, userId));
        insertUserRole(userId, roleIds);
    }

    /**
     * 修改用户状态
     *
     * @param userBo 用户信息
     * @return 结果
     */
    @Override
    public int updateUserStatus(SysUserEditBo userBo) {
        SysUser user = BeanCopyUtils.copy(userBo, SysUser.class);
        return baseMapper.updateById(user);
    }

    /**
     * 修改用户基本信息
     *
     * @param user 用户信息
     * @return 结果
     */
    @Override
    public int updateUserProfile(SysUser user) {
        return baseMapper.updateById(user);
    }

    /**
     * 修改用户头像
     *
     * @param userName 用户名
     * @param avatar   头像地址
     * @return 结果
     */
    @Override
    public boolean updateUserAvatar(String userName, String avatar) {
        return baseMapper.update(null,
            new LambdaUpdateWrapper<SysUser>()
                .set(SysUser::getAvatar, avatar)
                .eq(SysUser::getUserName, userName)) > 0;
    }

    /**
     * 重置用户密码
     *
     * @param userBo 用户信息
     * @return 结果
     */
    @Override
    public int resetPwd(SysUserEditBo userBo) {
        SysUser user = BeanCopyUtils.copy(userBo, SysUser.class);
        return baseMapper.updateById(user);
    }

    /**
     * 重置用户密码
     *
     * @param userName 用户名
     * @param password 密码
     * @return 结果
     */
    @Override
    public int resetUserPwd(String userName, String password) {
        return baseMapper.update(null,
            new LambdaUpdateWrapper<SysUser>()
                .set(SysUser::getPassword, password)
                .eq(SysUser::getUserName, userName));
    }

    /**
     * 新增用户角色信息
     *
     * @param user 用户对象
     */
    public void insertUserRole(SysUser user) {
        this.insertUserRole(user.getUserId(), user.getRoleIds());
    }

    /**
     * 新增用户岗位信息
     *
     * @param user 用户对象
     */
    public void insertUserPost(SysUser user) {
        Long[] posts = user.getPostIds();
        if (ArrayUtil.isNotEmpty(posts)) {
            // 新增用户与岗位管理
            List<SysUserPost> list = StreamUtils.toList(Arrays.asList(posts), postId -> {
                SysUserPost up = new SysUserPost();
                up.setUserId(user.getUserId());
                up.setPostId(postId);
                return up;
            });
            userPostMapper.insertBatch(list);
        }
    }

    /**
     * 新增用户角色信息
     *
     * @param userId  用户ID
     * @param roleIds 角色组
     */
    public void insertUserRole(Long userId, Long[] roleIds) {
        if (ArrayUtil.isNotEmpty(roleIds)) {
            // 新增用户与角色管理
            List<SysUserRole> list = StreamUtils.toList(Arrays.asList(roleIds), roleId -> {
                SysUserRole ur = new SysUserRole();
                ur.setUserId(userId);
                ur.setRoleId(roleId);
                return ur;
            });
            userRoleMapper.insertBatch(list);
        }
    }

    /**
     * 通过用户ID删除用户
     *
     * @param userId 用户ID
     * @return 结果
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public int deleteUserById(Long userId) {
        // 删除用户与角色关联
        userRoleMapper.delete(new LambdaQueryWrapper<SysUserRole>().eq(SysUserRole::getUserId, userId));
        // 删除用户与岗位表
        userPostMapper.delete(new LambdaQueryWrapper<SysUserPost>().eq(SysUserPost::getUserId, userId));
        return baseMapper.deleteById(userId);
    }

    /**
     * 批量删除用户信息
     *
     * @param userIds 需要删除的用户ID
     * @return 结果
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public int deleteUserByIds(Long[] userIds) {
        for (Long userId : userIds) {
            SysUserEditBo editBo = new SysUserEditBo();
            editBo.setUserId(userId);
            checkUserAllowed(editBo);
            checkUserDataScope(userId);
        }
        List<Long> ids = Arrays.asList(userIds);
        // 删除用户与角色关联
        userRoleMapper.delete(new LambdaQueryWrapper<SysUserRole>().in(SysUserRole::getUserId, ids));
        // 删除用户与岗位表
        userPostMapper.delete(new LambdaQueryWrapper<SysUserPost>().in(SysUserPost::getUserId, ids));
        return baseMapper.deleteBatchIds(ids);
    }

    @Cacheable(cacheNames = CacheNames.SYS_USER_NAME, key = "#userId")
    @Override
    public String selectUserNameById(Long userId) {
        SysUser sysUser = baseMapper.selectOne(new LambdaQueryWrapper<SysUser>()
                .select(SysUser::getUserName).eq(SysUser::getUserId, userId));
        return ObjectUtil.isNull(sysUser) ? null : sysUser.getUserName();
    }
}
