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
    @NotBlank(message = "短信模板申请说明不能为空")
    public String remark;

    //模板内容,例子 您正在申请手机注册，验证码为：${code}，5分钟内有效！
    @NotBlank(message = "模板内容不能为空")
    public String templateContent;

    //模板名称
    @NotBlank(message = "模板名称不能为空")
    public String templateName;

    //短信类型: 阿里云 0：验证码。1：短信通知。2：推广短信。3：国际/港澳台消息。
    //短信类型: 腾讯云 0表示普通短信, 1表示营销短信。
    @NotNull(message = "模板类型不能为空")
    public Integer templateType;

    //是否国际/港澳台短信：
    //0：表示国内短信。
    //1：表示国际/港澳台短信。
    public Long international;
}
