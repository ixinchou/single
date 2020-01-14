package com.ixchou.aop;

import com.ixchou.util.GsonUtil;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.context.request.async.WebAsyncTask;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
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

    @Around("webLog()")
    public Object around(ProceedingJoinPoint pjp) {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        assert attributes != null;
        HttpServletRequest request = attributes.getRequest();
        // 记录下请求的内容
        String url = request.getRequestURL().toString();
        String method = request.getMethod();
        StringBuilder builder = new StringBuilder();
        builder.append("web request\n=============================== request start\n")
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
        // header
        enumeration = request.getHeaderNames();
//        if (enumeration.hasMoreElements()) {
//            builder.append("\nheaders:\n");
//        }
        while (enumeration.hasMoreElements()) {
            String name = enumeration.nextElement();
            if ("51aea310ac094bf281205c37a3fc559d".equals(name)) {
                builder.append("\nheaders:\n");
                builder.append("  name: ").append(name).append(", ").append("value: ").append(request.getHeader(name)).append("\n");
            }
        }
        // body
        if (!url.contains("/upload") && "POST".equals(method)) {
            Object[] args = pjp.getArgs();
            if (null != args && args.length > 0) {
                builder.append("\nbody:\n");
                for (Object object : args) {
                    builder.append(GsonUtil.toString(object)).append("\n");
                }
            }
            int length = request.getContentLength();
            if (length > 0) {
                // 还有post的内容
                try {
                    if (!request.getInputStream().isFinished()) {
                        byte[] bytes = new byte[length];
                        int read = request.getInputStream().read(bytes);
                        builder.append("\ncontent:\n").append(new String(bytes, StandardCharsets.UTF_8));
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        Object object = null;
        try {
            object = pjp.proceed(pjp.getArgs());
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
        // 处理完成，返回内容
        if (null != object) {
            if (object instanceof WebAsyncTask) {
                builder.append("\nWaiting asynchronous task executing.......\n");
            } else {
                if (showResponseData) {
                    builder.append("\nresponse content: ").append(GsonUtil.toString(object));
                }
                builder.append("\n=============================== request end");
            }
        }
        logger.info(builder.toString());
        return object;
    }
}
