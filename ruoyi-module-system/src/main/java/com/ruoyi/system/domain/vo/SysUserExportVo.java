package com.ruoyi.system.domain.vo;

import com.alibaba.excel.annotation.ExcelProperty;
import com.ruoyi.common.annotation.ExcelDictFormat;
import com.ruoyi.common.annotation.ExcelEnumFormat;
import com.ruoyi.common.convert.ExcelDictConvert;
import com.ruoyi.common.convert.ExcelEnumConvert;
import com.ruoyi.common.enums.CommonNormalDisableEnum;
import com.ruoyi.common.enums.UserSexEnum;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * 用户对象导出VO
 *
 * @author weibocy
 */

@Data
@NoArgsConstructor
public class SysUserExportVo implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 用户ID
     */
    @ExcelProperty(value = "用户序号")
    private Long userId;

    /**
     * 用户账号
     */
    @ExcelProperty(value = "登录名称")
    private String userName;

    /**
     * 用户昵称
     */
    @ExcelProperty(value = "用户名称")
    private String nickName;

    /**
     * 用户邮箱
     */
    @ExcelProperty(value = "用户邮箱")
    private String email;

    /**
     * 手机号码
     */
    @ExcelProperty(value = "手机号码")
    private String phoneNumber;

    /**
     * 用户性别
     */
    @ExcelProperty(value = "用户性别", converter = ExcelEnumConvert.class)
    @ExcelEnumFormat(enumClass = UserSexEnum.class)
    private UserSexEnum sex;

    /**
     * 帐号状态（NORMAL=正常 DISABLE=停用）
     */
    @ExcelProperty(value = "帐号状态", converter = ExcelEnumConvert.class)
    @ExcelEnumFormat(enumClass = CommonNormalDisableEnum.class)
    private CommonNormalDisableEnum status;

    /**
     * 最后登录IP
     */
    @ExcelProperty(value = "最后登录IP")
    private String loginIp;

    /**
     * 最后登录时间
     */
    @ExcelProperty(value = "最后登录时间")
    private Date loginDate;

    /**
     * 部门名称
     */
    @ExcelProperty(value = "部门名称")
    private String deptName;

    /**
     * 负责人
     */
    @ExcelProperty(value = "部门负责人")
    private String leader;

}
