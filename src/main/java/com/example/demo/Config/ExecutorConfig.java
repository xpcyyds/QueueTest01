package com.example.demo.Config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * Created by baimao
 * Time:2019/12/8
 * 线程池配置
 */

//@Configuration
//@EnableAsync
//public class ExecutorConfig {
//
//    private static final Logger logger = LoggerFactory.getLogger(ExecutorConfig.class);
//
//    private int corePoolSize = 4;
//    private int maxPoolSize = 6;
//    private int queueCapacity = 10000;
//    private String namePrefix = "async-service-";
//
//    @Bean(name = "asyncServiceExecutor")
//    public Executor asyncServiceExecutor() {
//        logger.info("start asyncServiceExecutor");
//        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
//        //配置核心线程数
//        executor.setCorePoolSize(corePoolSize);
//        //配置最大线程数
//        executor.setMaxPoolSize(maxPoolSize);
//        //配置队列大小
//        executor.setQueueCapacity(queueCapacity);
//        //配置线程池中的线程的名称前缀
//        executor.setThreadNamePrefix(namePrefix);
//        // rejection-policy：当pool已经达到max size的时候，如何处理新任务
//        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
//        //执行初始化
//        executor.initialize();
//        return executor;
//    }
//}
