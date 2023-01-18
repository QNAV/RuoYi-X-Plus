package com.ruoyi.system.domain.vo;

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
     * 业务类型（0其它 1新增 2修改 3删除）
     */
    @Schema(description = "业务类型（0其它 1新增 2修改 3删除）", required = true)
    private Integer businessType;

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
     * 操作类别（0其它 1后台用户 2手机端用户）
     */
    @Schema(description = "操作类别（0其它 1后台用户 2手机端用户）", required = true)
    private Integer operatorType;

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
     * 操作状态（0正常 1异常）
     */
    @Schema(description = "操作状态（0正常 1异常）", required = true)
    private Integer status;

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
