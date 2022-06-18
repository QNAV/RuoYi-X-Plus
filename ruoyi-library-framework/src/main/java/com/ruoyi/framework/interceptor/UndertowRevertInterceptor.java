package com.ruoyi.framework.interceptor;

import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;

import javax.servlet.http.HttpServletResponse;

import java.util.Enumeration;

/**
 * undertow容器的中文参数乱码解决
 * @author weibocy
 */
public class UndertowRevertInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        Enumeration parameterNames = request.getParameterNames();
        while (parameterNames.hasMoreElements()) {
            Object name = parameterNames.nextElement();
            String value = request.getParameter(name.toString());
            value = revert(value);
            //注意不要和原有key重复，我不是教你写bug，只是提供一种思路。
            request.setAttribute(name.toString(), value);
        }
        return true;
    }

    private String revert(String s) {
        char[] chars = s.toCharArray();
        byte[] bytes = new byte[chars.length];
        for (int i = 0; i < chars.length; i++) {
            bytes[i] = (byte) chars[i];
        }
        //如系统非使用UTF-8编码，请替换为带有编码格式的构造函数
        return new String(bytes);
    }

}
