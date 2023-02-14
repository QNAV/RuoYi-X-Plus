package com.ruoyi.system.domain.bo;

import com.ruoyi.common.core.domain.bo.PageQuery;
import com.ruoyi.common.enums.CommonNormalDisable;
import com.ruoyi.common.enums.CommonYesOrNo;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * 菜单权限查询对象
 * @author weibocy
 */
@Schema(description = "菜单权限查询对象")
@Data
public class SysMenuQueryBo extends PageQuery {

    private static final long serialVersionUID=1L;

    /**
     * 菜单名称
     */
    @Schema(description = "菜单名称")
    private String menuName;

    /**
     * 显示状态（Y显示 N隐藏）
     */
    @Schema(description = "显示状态（Y显示 N隐藏）")
    private CommonYesOrNo visible;

    /**
     * 菜单状态（N正常 D停用）
     */
    @Schema(description = "菜单状态（N正常 D停用）")
    private CommonNormalDisable status;

}
