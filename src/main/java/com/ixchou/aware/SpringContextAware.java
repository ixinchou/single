package com.ixchou.aware;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

/**
 * <b>Author</b>: Xiang Liguo<br/>
 * <b>Date</b>: 2019/10/08 16:17<br/>
 * <b>Version</b>: 1.0<br/>
 * <b>Subject</b>: 实现 ApplicationContextAware 接口并提供 getBean 方法<br/>
 * <b>Description</b>:
 */
@Component
public class SpringContextAware implements ApplicationContextAware, DisposableBean {

    private static final Logger logger = LoggerFactory.getLogger(SpringContextAware.class);
    private static ApplicationContext context;

    @Override
    public void destroy() throws Exception {
        context = null;
        logger.info("ApplicationContext has been destroyed.");
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        context = applicationContext;
        logger.info("ApplicationContext was injected....");
    }

    private static void assertContextInjected() {
        Assert.notNull(context, "ApplicationContext is not inject yet.");
    }

    /**
     * 通过 beanName 获取 bean 实例
     *
     * @param name bean 的名字
     * @param <T>  类型
     * @return 返回获取到的 bean 实例或 null
     */
    @SuppressWarnings("unchecked")
    public static <T> T getBean(String name) {
        assertContextInjected();
        return (T) context.getBean(name);
    }

    /**
     * 通过指定类获取 bean
     */
    public static <T> T getBean(Class<T> clazz) {
        assertContextInjected();
        return context.getBean(clazz);
    }
}
