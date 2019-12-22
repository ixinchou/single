package com.ixchou.aop;

import com.ixchou.util.GsonUtil;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
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

    /**
     * 是否显示返回的内容，如果返回的数据内容太多，必定会造成性能问题
     */
    @Value("${setting.show-response-data}")
    private boolean showResponseData;

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
        String method = request.getMethod();
        StringBuilder builder = new StringBuilder();
        builder.append("=============================== request start\n")
                .append("URL: ").append(url).append("\n")
                .append("METHOD: ").append(method).append("\n")
                .append("IP: ").append(request.getRemoteAddr());
        // 参数列表
        Enumeration<String> enumeration = request.getParameterNames();
        if (enumeration.hasMoreElements()) {
            builder.append("\nparameters:\n");
        }
        while (enumeration.hasMoreElements()) {
            String name = enumeration.nextElement();
            builder.append("  name: ").append(name).append(", ").append("value: ").append(request.getParameter(name)).append("\n");
        }
        // body
        if (!url.contains("/upload") && "POST".equals(method)) {
            Object[] args = joinPoint.getArgs();
            if (null != args && args.length > 0) {
                builder.append("\nbody:\n");
                for (Object object : args) {
                    builder.append(GsonUtil.toString(object)).append("\n");
                }
            }
        }
        logger.info(builder.toString());
    }

    @AfterReturning(returning = "object", pointcut = "webLog()")
    public void doAfterReturning(Object object) {
        // 处理完成，返回内容
        if (object instanceof WebAsyncTask) {
            logger.info("Waiting asynchronous task executing.......");
        } else {
            if (showResponseData) {
                logger.info("RESPONSE: " + GsonUtil.toString(object));
            }
            logger.info("=============================== request end");
        }
    }
}
