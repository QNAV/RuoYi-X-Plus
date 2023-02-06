package com.ruoyi.demo.controller;

import com.ruoyi.common.core.domain.R;
import com.ruoyi.common.utils.email.MailUtils;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;


/**
 * 邮件发送案例
 * !!!不推荐模块内写控制器
 * @author Michelle.Chung
 */
@Validated
@Tag(description = "邮件发送案例", name = "MailService")
@RequiredArgsConstructor
@RestController
@RequestMapping("/demo/mail")
public class MailController {

    @Operation(description = "发送邮件", operationId = "MailServiceGetSendSimpleMessage")
    @GetMapping("/sendSimpleMessage")
    public R<Void> sendSimpleMessage(@Parameter(description = "接收人") String to,
                                     @Parameter(description = "标题") String subject,
                                     @Parameter(description = "内容") String text) {
        MailUtils.sendText(to, subject, text);
        return R.ok();
    }

    @Operation(description = "发送邮件（带附件）", operationId = "MailServiceGetSendMessageWithAttachment")
    @GetMapping("/sendMessageWithAttachment")
    public R<Void> sendMessageWithAttachment(@Parameter(description = "接收人") String to,
                                             @Parameter(description = "标题") String subject,
                                             @Parameter(description = "内容") String text,
                                             @Parameter(description = "附件路径") String filePath) {
        MailUtils.sendText(to, subject, text, new File(filePath));
        return R.ok();
    }

}
