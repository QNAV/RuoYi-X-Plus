package com.ruoyi.system.domain;

import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.ruoyi.common.annotation.ExcelDictFormat;
import com.ruoyi.common.convert.ExcelDictConvert;
import com.ruoyi.common.enums.CommonNormalDisable;
import com.ruoyi.common.enums.LoginStatusEnum;
import com.ruoyi.common.enums.UserType;
import com.ruoyi.system.enums.BusinessTypeEnum;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 操作日志记录表 oper_log
 *
 * @author ruoyi
 * @author Lion Li
 */

@Data
@TableName("sys_oper_log")
@ExcelIgnoreUnannotated
@Schema(description = "操作日志记录业务对象")
public class SysOperLog implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 日志主键
     */
    @Schema(description = "日志主键")
    @ExcelProperty(value = "日志主键")
    @TableId(value = "oper_id")
    private Long operId;

    /**
     * 操作模块
     */
    @Schema(description = "操作模块")
    @ExcelProperty(value = "操作模块")
    private String title;

    /**
     * 业务类型（ADD=新增 MODIFY=修改 DELETE=删除 GRANT=授权 EXPORT=导出 IMPORT=导入 FORCED=强退 GENCODE=生成代码 CLEAR=清空数据 OTHER=其他）
     */
    @Schema(description = "业务类型（ADD=新增 MODIFY=修改 DELETE=删除 GRANT=授权 EXPORT=导出 IMPORT=导入 FORCED=强退 GENCODE=生成代码 CLEAR=清空数据 OTHER=其他）")
    @ExcelProperty(value = "业务类型", converter = ExcelDictConvert.class)
    @ExcelDictFormat(dictType = "sys_oper_type")
    private BusinessTypeEnum businessType;

    /**
     * 业务类型数组
     */
    @Schema(description = "业务类型数组")
    @TableField(exist = false)
    private Integer[] businessTypes;

    /**
     * 请求方法
     */
    @Schema(description = "请求方法")
    @ExcelProperty(value = "请求方法")
    private String method;

    /**
     * 请求方式
     */
    @Schema(description = "请求方式")
    @ExcelProperty(value = "请求方式")
    private String requestMethod;

    /**
     * 操作类别（PC=电脑端 ANDROID=安卓app端 IOS=苹果app端 WXAPP=微信小程序端 WXMP=微信公众号端 ALIPAYAPP=支付宝小程序端）
     */
    @Schema(description = "操作类别（PC=电脑端 ANDROID=安卓app端 IOS=苹果app端 WXAPP=微信小程序端 WXMP=微信公众号端 ALIPAYAPP=支付宝小程序端）")
    @ExcelProperty(value = "操作类别", converter = ExcelDictConvert.class)
    @ExcelDictFormat(readConverterExp = "PC=电脑端 ANDROID=安卓app端 IOS=苹果app端 WXAPP=微信小程序端 WXMP=微信公众号端 ALIPAYAPP=支付宝小程序端")
    private UserType operatorType;

    /**
     * 操作人员
     */
    @Schema(description = "操作人员")
    @ExcelProperty(value = "操作人员")
    private String operName;

    /**
     * 部门名称
     */
    @Schema(description = "部门名称")
    @ExcelProperty(value = "部门名称")
    private String deptName;

    /**
     * 请求url
     */
    @Schema(description = "请求url")
    @ExcelProperty(value = "请求地址")
    private String operUrl;

    /**
     * 操作地址
     */
    @Schema(description = "操作地址")
    @ExcelProperty(value = "操作地址")
    private String operIp;

    /**
     * 操作地点
     */
    @Schema(description = "操作地点")
    @ExcelProperty(value = "操作地点")
    private String operLocation;

    /**
     * 请求参数
     */
    @Schema(description = "请求参数")
    @ExcelProperty(value = "请求参数")
    private String operParam;

    /**
     * 返回参数
     */
    @Schema(description = "返回参数")
    @ExcelProperty(value = "返回参数")
    private String jsonResult;

    /**
     * 操作状态（NORMAL=正常 EXCEPTION=异常）
     */
    @Schema(description = "操作状态（NORMAL=正常 EXCEPTION=异常）")
    @ExcelProperty(value = "状态", converter = ExcelDictConvert.class)
    @ExcelDictFormat(dictType = "sys_common_status")
    private LoginStatusEnum status;

    /**
     * 错误消息
     */
    @Schema(description = "错误消息")
    @ExcelProperty(value = "错误消息")
    private String errorMsg;

    /**
     * 操作时间
     */
    @Schema(description = "操作时间")
    @ExcelProperty(value = "操作时间")
    private Date operTime;

}
