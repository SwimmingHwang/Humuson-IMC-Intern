/*
* ThreadPool 사용해서 중복되는 스케쥴러 세팅
* */
package com.humuson.job;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;

@Configuration
@EnableScheduling
@EnableAsync
public class SchedulerConfiguration{
    @Bean
    public TaskScheduler poolScheduler() {
        ThreadPoolTaskScheduler threadPoolTaskScheduler = new ThreadPoolTaskScheduler();
        threadPoolTaskScheduler.setPoolSize(Runtime.getRuntime().availableProcessors() * 2);
        threadPoolTaskScheduler.setThreadNamePrefix("예약상태로 UPDATE-");
        return threadPoolTaskScheduler;
    }

}
//public class SchedulerConfig implements SchedulingConfigurer{
//
//
//    //TODO : config 적용안되는 문제 해결할 것
//    private static final int POOL_SIZE = 10;
//
//    @Override
//    public void configureTasks(ScheduledTaskRegistrar taskRegistrar) {
//        final ThreadPoolTaskScheduler threadPoolTaskScheduler = new ThreadPoolTaskScheduler();
//
//        threadPoolTaskScheduler.setPoolSize(POOL_SIZE);
//        threadPoolTaskScheduler.setThreadNamePrefix("예약상태로 UPDATE-");
//        threadPoolTaskScheduler.initialize();
//
//        taskRegistrar.setTaskScheduler(threadPoolTaskScheduler);
//    }
//}
