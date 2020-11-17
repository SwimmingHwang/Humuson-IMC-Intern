package com.humuson;

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

//@EnableBatchProcessing // 배치 기능 활성화
@EnableScheduling // 스케쥴러 기능 활성화
@SpringBootApplication
public class KafkaBatchApplication {
    public static void main(String[] args){
        SpringApplication.run(KafkaBatchApplication.class, args);
    }
}