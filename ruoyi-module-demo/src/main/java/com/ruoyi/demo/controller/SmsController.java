package com.ruoyi.demo.controller;

import com.ruoyi.common.core.domain.R;
import com.ruoyi.common.utils.spring.SpringUtils;
import com.ruoyi.sms.config.properties.SmsProperties;
import com.ruoyi.sms.core.SmsTemplate;
import com.ruoyi.sms.factory.SmsFactory;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * 短信演示案例
 * !!!不推荐模块内写控制器
 * 请先阅读文档 否则无法使用
 *
 * @author Lion Li
 */
@Validated
@Tag(description = "短信演示案例", name = "SmsDemoService")
@RequiredArgsConstructor
@RestController
@RequestMapping("/demo/sms")
public class SmsController {



    @Operation(description = "发送短信Aliyun/Tencent", operationId = "SmsDemoServiceGetSendAliyun")
    @GetMapping("/sendAliyun")
    public R<Object> sendAliyun(@Parameter(description = "电话号") String phones,
                                     @Parameter(description = "模板ID") String templateId) {
        SmsTemplate smsTemplate = SmsFactory.instance();
        Map<String, String> map = new HashMap<>(1);
        map.put("code", "1234");
        Object send = smsTemplate.send(phones, templateId, map);
        return R.ok(send);
    }

}
