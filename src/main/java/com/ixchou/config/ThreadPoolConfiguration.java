package com.ixchou.config;

import com.ixchou.controller.WxController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.AsyncConfigurer;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.Arrays;
import java.util.concurrent.Executor;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * <b>Author</b>: Hsiang Leekwok<br/>
 * <b>Date</b>: 2019/08/05 10:16<br/>
 * <b>Version</b>: v1.0<br/>
 * <b>Subject</b>: <br/>
 * <b>Description</b>:
 */
@Configuration
@EnableAsync
public class ThreadPoolConfiguration implements AsyncConfigurer {

    private final Logger logger = LoggerFactory.getLogger(WxController.class);

    @Bean("asyncTaskExecutor")
    @Override
    public Executor getAsyncExecutor() {
        int threads = Runtime.getRuntime().availableProcessors() + 1;
        ThreadPoolTaskExecutor executor= new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(threads);
        executor.setMaxPoolSize(threads);
        executor.setQueueCapacity(200);
        executor.setKeepAliveSeconds(60);
        executor.setThreadNamePrefix("asyncTaskExecutor-");
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        return executor;
    }

    @Override
    public AsyncUncaughtExceptionHandler getAsyncUncaughtExceptionHandler() {
        return (throwable, method, objects) -> {
            logger.error("AsyncUncaughtExceptionHandler:", throwable);
            System.out.println("method:" + method.getName());
            System.out.println("objects:" + Arrays.asList(objects));
        };
    }
}
