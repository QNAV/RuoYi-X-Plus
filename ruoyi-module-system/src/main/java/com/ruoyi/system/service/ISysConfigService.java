package com.ruoyi.system.service;

import com.ruoyi.common.core.domain.bo.PageQuery;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.CommonYesOrNoEnum;
import com.ruoyi.system.domain.SysConfig;
import com.ruoyi.system.domain.bo.SysConfigQueryBo;
import com.ruoyi.system.domain.vo.SysConfigVo;

import java.util.List;

/**
 * 参数配置 服务层
 *
 * @author ruoyi
 * @author Lion Li
 */
public interface ISysConfigService {


    TableDataInfo<SysConfigVo> selectPageConfigList(SysConfigQueryBo configQuery, PageQuery pageQuery);

    /**
     * 查询参数配置信息
     *
     * @param configId 参数配置ID
     * @return 参数配置信息
     */
    SysConfigVo selectConfigById(Long configId);

    /**
     * 根据键名查询参数配置信息
     *
     * @param configKey 参数键名
     * @return 参数键值
     */
    String selectConfigByKey(String configKey);

    /**
     * 获取验证码开关
     *
     * @return true开启，false关闭
     */
    boolean selectCaptchaEnabled();

    /**
     * 查询参数配置列表
     *
     * @param configQuery 参数配置查询对象
     * @return 参数配置集合
     */
    List<SysConfig> selectConfigList(SysConfigQueryBo configQuery);

    /**
     * 新增参数配置
     *
     * @param config 参数配置信息
     * @return 结果
     */
    String insertConfig(SysConfig config);

    /**
     * 修改参数配置
     *
     * @param config 参数配置信息
     * @return 结果
     */
    String updateConfig(SysConfig config);

    /**
     * 批量删除参数信息
     *
     * @param configIds 需要删除的参数ID
     */
    void deleteConfigByIds(Long[] configIds);

    /**
     * 加载参数缓存数据
     */
    void loadingConfigCache();

    /**
     * 清空参数缓存数据
     */
    void clearConfigCache();

    /**
     * 重置参数缓存数据
     */
    void resetConfigCache();

    /**
     * 校验参数键名是否唯一
     *
     * @param config 参数信息
     * @return 结果
     */
    CommonYesOrNoEnum checkConfigKeyUnique(SysConfig config);


}
