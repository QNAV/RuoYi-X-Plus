package com.ruoyi.common.core.domain.event;

import com.ruoyi.common.enums.BusinessTypeEnum;
import com.ruoyi.common.enums.OperationStatusEnum;
import com.ruoyi.common.enums.UserTypeEnum;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 后台操作日志事件
 *
 * @author Lion Li
 */

@Data
public class AdminOperLogEvent implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 日志主键
     */
    private Long operId;

    /**
     * 操作模块
     */
    private String title;

    /**
     * 业务类型（ADD=新增 MODIFY=修改 DELETE=删除 GRANT=授权 EXPORT=导出 IMPORT=导入 FORCED=强退 GENCODE=生成代码 CLEAR=清空数据 OTHER=其他）
     */
    private BusinessTypeEnum businessType;

    /**
     * 业务类型数组
     */
    private Integer[] businessTypes;

    /**
     * 请求方法
     */
    private String method;

    /**
     * 请求方式
     */
    private String requestMethod;

    /**
     * 操作类别（PC=电脑端 ANDROID=安卓app端 IOS=苹果app端 WXAPP=微信小程序端 WXMP=微信公众号端 ALIPAYAPP=支付宝小程序端）
     */
    private UserTypeEnum operatorType;

    /**
     * 操作人员
     */
    private String operName;

    /**
     * 部门名称
     */
    private String deptName;

    /**
     * 请求url
     */
    private String operUrl;

    /**
     * 操作地址
     */
    private String operIp;

    /**
     * 操作地点
     */
    private String operLocation;

    /**
     * 请求参数
     */
    private String operParam;

    /**
     * 返回参数
     */
    private String jsonResult;

    /**
     * 操作状态（NORMAL=正常 EXCEPTION=异常）
     */
    private OperationStatusEnum status;

    /**
     * 错误消息
     */
    private String errorMsg;

    /**
     * 操作时间
     */
    private Date operTime;

}
