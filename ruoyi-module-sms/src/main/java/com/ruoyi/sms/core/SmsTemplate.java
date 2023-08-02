package com.ruoyi.sms.core;

import com.ruoyi.sms.domain.bo.AddSmsTemplateBo;
import com.ruoyi.sms.domain.bo.ModifySmsTemplateBo;
import com.ruoyi.sms.domain.bo.QuerySmsTemplateBo;
import com.ruoyi.sms.domain.vo.UnifySmsTemplatePageVo;
import com.ruoyi.sms.domain.vo.UnifySmsTemplateVo;
import com.ruoyi.sms.entity.SmsResult;

import java.util.Map;

/**
 * 短信模板
 *
 * @author Lion Li
 */
public interface SmsTemplate {

    /**
     * 发送短信
     *
     * @param phones     电话号(多个逗号分割)
     * @param templateId 模板id
     * @param param      模板对应参数
     *                   阿里 需使用 模板变量名称对应内容 例如: code=1234
     *                   腾讯 需使用 模板变量顺序对应内容 例如: 1=1234, 1为模板内第一个参数
     * @return 短信发送结果
     */
    SmsResult send(String phones, String templateId, Map<String, String> param);


    /**
     *  查询短信模板列表
     * @param querySmsTemplateBo 查询短信模板请求参数
     * @return 短信模板列表
     */
    UnifySmsTemplatePageVo querySmsTemplateList(QuerySmsTemplateBo querySmsTemplateBo);

    /**
     *  申请短信模板
     * @param addSmsTemplateBo 短信模板申请对象
     * @return  短信模板ID
     */
    String addSmsTemplate(AddSmsTemplateBo addSmsTemplateBo);

    /**
     *  修改审核未通过的短信模板
     * @param modifySmsTemplateBo  编辑短信模板对象
     * @return 短信模板ID
     */
    String modifySmsTemplate(ModifySmsTemplateBo modifySmsTemplateBo);

    /**
     *  根据短信模板ID删除短模板
     * @param templateCode 短信模板ID
     * @return 删除短信模板结果
     */
    Boolean deleteSmsTemplate(String templateCode);

    /**
     *  查询短信模板审核状态
     * @param templateCode 短信模板ID
     * @param international 是否国际/港澳台短信：
     * @return 查询短信模板审核状态模板结果
     */
    UnifySmsTemplateVo querySmsTemplate(String templateCode,Long international);
}
