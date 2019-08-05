package com.ixchou.aop;

import com.ixchou.util.GsonUtil;
import com.ixchou.util.StringUtil;
import com.ixchou.util.request.CustomRequestWrapper;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.Enumeration;

/**
 * <b>Author</b>: Hsiang Leekwok<br/>
 * <b>Date</b>: 2019/08/01 19:45<br/>
 * <b>Version</b>: v1.0<br/>
 * <b>Subject</b>: 切面记录所有访问日志<br/>
 * <b>Description</b>:
 */
@Aspect
@Component
public class WebLogAspect {

    private static final Logger logger = LoggerFactory.getLogger(WebLogAspect.class);

    @Pointcut("execution(public * com.ixchou.controller.*.*(..))")
    public void webLog() {

    }

    @Before("webLog()")
    public void doBefore(JoinPoint joinPoint) {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        assert attributes != null;
        HttpServletRequest request = attributes.getRequest();
        // 记录下请求的内容
        logger.info("===============================");
        logger.info("URL: " + request.getRequestURL().toString());
        logger.info("METHOD: " + request.getMethod());
        logger.info("IP: " + request.getRemoteAddr());
        Enumeration<String> enumeration = request.getParameterNames();
        while (enumeration.hasMoreElements()) {
            String name = enumeration.nextElement();
            logger.info("name: {}, value: {}", name, request.getParameter(name));
        }
        // body
        try {
            CustomRequestWrapper wrapper = new CustomRequestWrapper(request);
            String body = wrapper.getBody();
            if (!StringUtil.isEmpty(body)) {
                logger.info("body: " + body);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @AfterReturning(returning = "object", pointcut = "webLog()")
    public void doAfterReturning(Object object) {
        // 处理完成，返回内容
        logger.info("RESPONSE: " + GsonUtil.toString(object));
        logger.info("===============================");
    }
}
