package com.ruoyi.sms.domain.bo;

import com.aliyun.tea.NameInMap;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *  查询短信模板参数
 * @Author Administrator
 * @Date 2023/7/11 11:06
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class QuerySmsTemplateBo {

    //页码(Aliyun默认为1.)
    public Integer pageIndex;

    //页码(aliyun默认为10.)
    public Integer pageSize;

    //是否国际/港澳台短信：
    //0：表示国内短信。
    //1：表示国际/港澳台短信。
    public Long international;

}
