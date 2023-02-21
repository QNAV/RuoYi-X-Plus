package com.ruoyi.system.domain.bo;

import com.ruoyi.common.enums.LoginActionEnum;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 业务用户登录记录业务查询对象 biz_logininfor
 *
 * @author weibocy
 * @date 2022-10-26
 */

@Data
@Schema(description = "业务用户登录记录业务查询对象")
public class BizLogininforQueryBo implements Serializable {

    private static final long serialVersionUID=1L;

    /**
     * 用户名(也可能是手机号等)
     */
    @Schema(description = "用户名(也可能是手机号等)")
    private String userName;


    /**
     * 登录状态（LOGINOK=登录成功 LOGINFAIL=登录失败 LOGOUT=注销登录 REGISTER=注册）
     */
    @Schema(description = "登录状态（LOGINOK=登录成功 LOGINFAIL=登录失败 LOGOUT=注销登录 REGISTER=注册）")
    private LoginActionEnum status;


    /**
     * 访问 开始时间
     */
    @Schema(description = "创建开始时间")
    private Date createBeginTime;

    /**
     * 访问 结束时间
     */
    @Schema(description = "创建结束时间")
    private Date createEndTime;
}
