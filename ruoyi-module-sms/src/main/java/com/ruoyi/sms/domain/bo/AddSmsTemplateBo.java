package com.ruoyi.sms.domain.bo;

import com.aliyun.tea.NameInMap;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * 新增短信模板对象
 * @Author Administrator
 * @Date 2023/7/11 15:55
 */
@Data
@ToString
@NoArgsConstructor
public class AddSmsTemplateBo {

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

    //短信类型
    @NameInMap("TemplateType")
    @NotNull(message = "模板名称不能为空")
    public Integer templateType;
}
