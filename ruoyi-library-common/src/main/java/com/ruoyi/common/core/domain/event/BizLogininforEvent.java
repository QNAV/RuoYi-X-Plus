package com.ruoyi.common.core.domain.event;

import com.ruoyi.common.enums.UserActionEnum;
import lombok.Data;

import javax.servlet.http.HttpServletRequest;
import java.io.Serializable;

/**
 * 业务用户登录事件
 *
 * @author weibocy
 */

@Data
public class BizLogininforEvent implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 用户账号
     */
    private String username;

    /**
     * 登录状态（LOGINOK=登录成功 LOGINFAIL=登录失败 LOGOUT=注销登录 REGISTER=注册）
     */
    private UserActionEnum status;

    /**
     * 提示消息
     */
    private String message;

    /**
     * 请求体
     */
    private HttpServletRequest request;

    /**
     * 其他参数
     */
    private Object[] args;

}
