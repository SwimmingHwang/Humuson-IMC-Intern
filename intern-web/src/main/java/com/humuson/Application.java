package com.humuson;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


@EnableJpaRepositories("com.humuson")
@EnableJpaAuditing//JPA Auditing 활성화
@SpringBootApplication
public class Application { // 메인 클래스
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);//내장 WAS 실행
    }
}