package com.ixchou.aop;

import com.ixchou.util.GsonUtil;
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
import org.springframework.web.context.request.async.WebAsyncTask;

import javax.servlet.http.HttpServletRequest;
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
        String url = request.getRequestURL().toString();
        logger.info("===============================");
        logger.info("URL: " + url);
        logger.info("METHOD: " + request.getMethod());
        logger.info("IP: " + request.getRemoteAddr());
        // 参数列表
        Enumeration<String> enumeration = request.getParameterNames();
        while (enumeration.hasMoreElements()) {
            String name = enumeration.nextElement();
            logger.info("name: {}, value: {}", name, request.getParameter(name));
        }
        // body
        if (!url.contains("/api/upload")) {
            Object[] args = joinPoint.getArgs();
            if (null != args && args.length > 0) {
                for (Object object : args) {
                    logger.info("body: " + GsonUtil.toString(object));
                }
            }
        }
    }

    @AfterReturning(returning = "object", pointcut = "webLog()")
    public void doAfterReturning(Object object) {
        // 处理完成，返回内容
        if (object instanceof WebAsyncTask) {
            logger.info("Asynchronous task executing.......");
        } else {
            logger.info("RESPONSE: " + GsonUtil.toString(object));
            logger.info("===============================");
        }
    }
}
