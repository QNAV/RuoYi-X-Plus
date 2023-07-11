package com.ruoyi.sms.domain.bo;

import com.aliyun.tea.NameInMap;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 *  编辑未审核通过的短信模板对象
 * @Author Administrator
 * @Date 2023/7/11 16:19
 */
@Data
@ToString
@NoArgsConstructor
public class ModifySmsTemplateBo {

    //短信模板申请说明
    @NameInMap("Remark")
    @NotBlank(message = "短信模板申请说明不能为空")
    public String remark;

    //模板内容,例子 您正在申请手机注册，验证码为：${code}，5分钟内有效！
    @NameInMap("TemplateContent")
    @NotBlank(message = "模板内容不能为空")
    public String templateContent;

    //模板名称
    @NameInMap("TemplateName")
    @NotBlank(message = "模板名称不能为空")
    public String templateName;

    //短信模板CODE
    @NameInMap("TemplateName")
    @NotBlank(message = "短信模板CODE为空")
    public String  TemplateCode;

    //短信类型
    @NameInMap("TemplateType")
    @NotNull(message = "模板名称不能为空")
    public Integer templateType;

}
