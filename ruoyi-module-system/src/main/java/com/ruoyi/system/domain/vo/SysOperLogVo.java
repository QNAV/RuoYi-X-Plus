package com.ruoyi.system.domain.vo;

import com.ruoyi.common.enums.OperationStatusEnum;
import com.ruoyi.common.enums.UserTypeEnum;
import com.ruoyi.system.enums.BusinessTypeEnum;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 操作日志记录视图对象
 *
 * @author weibocy
 */

@Data
@Schema(description = "操作日志记录视图对象")
public class SysOperLogVo implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 日志主键
     */
    @Schema(description = "日志主键", required = true)
    private Long operId;

    /**
     * 操作模块
     */
    @Schema(description = "操作模块", required = true)
    private String title;

    /**
     * 业务类型（ADD=新增 MODIFY=修改 DELETE=删除 GRANT=授权 EXPORT=导出 IMPORT=导入 FORCED=强退 GENCODE=生成代码 CLEAR=清空数据 OTHER=其他）
     */
    @Schema(description = "业务类型（ADD=新增 MODIFY=修改 DELETE=删除 GRANT=授权 EXPORT=导出 IMPORT=导入 FORCED=强退 GENCODE=生成代码 CLEAR=清空数据 OTHER=其他）", required = true)
    private BusinessTypeEnum businessType;

    /**
     * 业务类型数组
     */
    @Schema(description = "业务类型数组", required = true)
    private Integer[] businessTypes;

    /**
     * 请求方法
     */
    @Schema(description = "请求方法", required = true)
    private String method;

    /**
     * 请求方式
     */
    @Schema(description = "请求方式", required = true)
    private String requestMethod;

    /**
     * 操作类别（PC=电脑端 ANDROID=安卓app端 IOS=苹果app端 WXAPP=微信小程序端 WXMP=微信公众号端 ALIPAYAPP=支付宝小程序端）
     */
    @Schema(description = "操作类别（PC=电脑端 ANDROID=安卓app端 IOS=苹果app端 WXAPP=微信小程序端 WXMP=微信公众号端 ALIPAYAPP=支付宝小程序端）", required = true)
    private UserTypeEnum operatorType;

    /**
     * 操作人员
     */
    @Schema(description = "操作人员", required = true)
    private String operName;

    /**
     * 部门名称
     */
    @Schema(description = "部门名称")
    private String deptName;

    /**
     * 请求url
     */
    @Schema(description = "请求url", required = true)
    private String operUrl;

    /**
     * 操作地址
     */
    @Schema(description = "操作地址", required = true)
    private String operIp;

    /**
     * 操作地点
     */
    @Schema(description = "操作地点")
    private String operLocation;

    /**
     * 请求参数
     */
    @Schema(description = "请求参数", required = true)
    private String operParam;

    /**
     * 返回参数
     */
    @Schema(description = "返回参数")
    private String jsonResult;

    /**
     * 操作状态（NORMAL=正常 EXCEPTION=异常）
     */
    @Schema(description = "操作状态（NORMAL=正常 EXCEPTION=异常）", required = true)
    private OperationStatusEnum status;

    /**
     * 错误消息
     */
    @Schema(description = "错误消息")
    private String errorMsg;

    /**
     * 操作时间
     */
    @Schema(description = "操作时间", required = true)
    private Date operTime;

}
