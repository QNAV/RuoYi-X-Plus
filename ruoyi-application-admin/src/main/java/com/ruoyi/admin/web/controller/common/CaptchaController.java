package com.ruoyi.admin.web.controller.common;

import cn.dev33.satoken.annotation.SaIgnore;
import cn.hutool.captcha.AbstractCaptcha;
import cn.hutool.captcha.generator.CodeGenerator;
import cn.hutool.core.convert.Convert;
import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.RandomUtil;
import com.ruoyi.common.constant.CacheConstants;
import com.ruoyi.common.core.domain.R;
import com.ruoyi.common.enums.CaptchaType;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.utils.redis.RedisUtils;
import com.ruoyi.common.utils.reflect.ReflectUtils;
import com.ruoyi.common.utils.spring.SpringUtils;
import com.ruoyi.framework.config.properties.CaptchaProperties;
import com.ruoyi.sms.config.properties.SmsProperties;
import com.ruoyi.sms.core.SmsTemplate;
import com.ruoyi.sms.entity.SmsResult;
import com.ruoyi.system.service.ISysConfigService;
import com.ruoyi.admin.web.model.vo.CaptchaImageVo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotBlank;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

/**
 * 验证码操作处理
 *
 * @author ruoyi
 * @author Lion Li
 */
@SaIgnore
@Slf4j
@Validated
@Tag( description= "验证码管理", name = "CaptchaService")
@RequiredArgsConstructor
@RestController
public class CaptchaController {

    private final CaptchaProperties captchaProperties;
    private final SmsProperties smsProperties;
    private final ISysConfigService configService;

    /**
     * 短信验证码
     *
     * @param phoneNumber 用户手机号
     */
    @Operation(description = "短信验证码", operationId = "CaptchaGetSmsCaptcha")
    @GetMapping("/captchaSms")
    public R<Void> smsCaptcha(@Parameter(description = "用户手机号")
                              @NotBlank(message = "{user.phonenumber.not.blank}")
                              String phoneNumber) {
        if (!smsProperties.getEnabled()) {
            R.fail("当前系统没有开启短信功能！");
        }
        String key = CacheConstants.SMS_CAPTCHA_CODE_KEY + phoneNumber;
        String code = RandomUtil.randomNumbers(4);
        RedisUtils.setCacheObject(key, code, Duration.ofMinutes(CacheConstants.CAPTCHA_EXPIRATION));
        // 验证码模板id 自行处理 (查数据库或写死均可)
        String templateId = "";
        Map<String, String> map = new HashMap<>(1);
        map.put("code", code);
        SmsTemplate smsTemplate = SpringUtils.getBean(SmsTemplate.class);
        SmsResult result = smsTemplate.send(phoneNumber, templateId, map);
        if (!result.isSuccess()) {
            log.error("验证码短信发送异常 => {}", result);
            return R.fail(result.getMessage());
        }
        return R.ok();
    }

    /**
     * 生成验证码
     */
    @Operation(description = "生成验证码", operationId = "CaptchaGetGetCode")
    @GetMapping("/captchaImage")
    public R<CaptchaImageVo> getCode() {
        CaptchaImageVo data = new CaptchaImageVo();
        boolean captchaEnabled = configService.selectCaptchaEnabled();
        data.setCaptchaEnabled(captchaEnabled);
        if (!captchaEnabled) {
            return R.ok(data);
        }
        // 保存验证码信息
        String uuid = IdUtil.simpleUUID();
        String verifyKey = CacheConstants.CAPTCHA_CODE_KEY + uuid;
        // 生成验证码
        CaptchaType captchaType = captchaProperties.getType();
        boolean isMath = CaptchaType.MATH == captchaType;
        Integer length = isMath ? captchaProperties.getNumberLength() : captchaProperties.getCharLength();
        CodeGenerator codeGenerator = ReflectUtils.newInstance(captchaType.getClazz(), length);
        AbstractCaptcha captcha = SpringUtils.getBean(captchaProperties.getCategory().getClazz());
        captcha.setGenerator(codeGenerator);
        captcha.createCode();
        String code = captcha.getCode();
        if (isMath) {
            ExpressionParser parser = new SpelExpressionParser();
            Expression exp = parser.parseExpression(StringUtils.remove(code, "="));
            code = exp.getValue(String.class);
        }
        RedisUtils.setCacheObject(verifyKey, code, Duration.ofMinutes(CacheConstants.CAPTCHA_EXPIRATION));
        data.setUuid(uuid);
        data.setImg(captcha.getImageBase64());
        log.debug("UUID为 => {},    图片验证码为 => {}", uuid, code);
        return R.ok(data);
    }

    private String getCodeResult(String capStr) {
        int numberLength = captchaProperties.getNumberLength();
        int a = Convert.toInt(StringUtils.substring(capStr, 0, numberLength).trim());
        char operator = capStr.charAt(numberLength);
        int b = Convert.toInt(StringUtils.substring(capStr, numberLength + 1, numberLength + 1 + numberLength).trim());
        switch (operator) {
            case '*':
                return Convert.toStr(a * b);
            case '+':
                return Convert.toStr(a + b);
            case '-':
                return Convert.toStr(a - b);
            default:
                return StringUtils.EMPTY;
        }
    }

}
