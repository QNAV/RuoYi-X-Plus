package com.ruoyi.sms.domain.bo;

import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

/**
 * @Author Administrator
 * @Date 2023/7/5 13:55
 */

@Data
//@Schema(description = "短信配置修改业务对象")
public class SysSmsConfigEditBo implements Serializable {

    private static final long serialVersionUID=1L;

    /**
     * 主建
     */
//    @TableId(value = "id")
    private Long id;

    /**
     * 厂商
     */
//    @Schema(description = "厂商", required = true)
//    @Size(min = 0, max = 64, message = "厂商长度不能超过{max}个字符")
    @NotBlank(message = "厂商不能为空")
    private String manufacturer;

    /**
     * 是否启用
     */
//    @Schema(description = "是否启用", required = true)
    @NotNull(message = "是否启用不能为空")
    private Boolean enabled;

    /**
     * key
     */
    @NotBlank(message = "key不能为空")
    private String accessKeyId;

    /**
     * 密匙
     */
    @NotBlank(message = "密匙不能为空")
    private String accessKeySecret;

    /**
     * 短信签名
     */
    private String signName;

    /**
     * 短信应用ID (腾讯专属)
     */
    private String sdkAppId;


    /**
     * 备注
     */
    private String remark;
}
