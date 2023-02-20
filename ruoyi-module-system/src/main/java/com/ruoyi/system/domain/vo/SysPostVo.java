package com.ruoyi.system.domain.vo;

import com.ruoyi.common.core.domain.BaseVo;
import com.ruoyi.common.enums.CommonNormalDisableEnum;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;


/**
 * 岗位信息视图对象
 *
 * @author weibocy
 */

@Data
@EqualsAndHashCode(callSuper = true)
@Schema(description = "岗位信息视图对象")
public class SysPostVo extends BaseVo {

    private static final long serialVersionUID=1L;

    /**
     * 岗位序号
     */
    @Schema(description = "岗位序号", required = true)
    private Long postId;

    /**
     * 岗位编码
     */
    @Schema(description = "岗位编码", required = true)
    private String postCode;

    /**
     * 岗位名称
     */
    @Schema(description = "岗位名称", required = true)
    private String postName;

    /**
     * 岗位排序
     */
    @Schema(description = "岗位排序", required = true)
    private Integer postSort;

    /**
     * 状态（NORMAL=正常 DISABLE=停用）
     */
    @Schema(description = "状态（NORMAL=正常 DISABLE=停用）", required = true)
    private CommonNormalDisableEnum status;

    /**
     * 备注
     */
    @Schema(description = "备注")
    private String remark;

    /**
     * 用户是否存在此岗位标识 默认不存在
     */
    @Schema(description = "用户是否存在此岗位标识 默认不存在", required = true)
    private boolean flag = false;

}
