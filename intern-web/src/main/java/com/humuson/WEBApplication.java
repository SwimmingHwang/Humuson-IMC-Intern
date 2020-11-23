package com.humuson;

import org.apache.catalina.SessionListener;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import javax.servlet.http.HttpSessionListener;


@EnableJpaRepositories("com.humuson")
@EnableJpaAuditing//JPA Auditing 활성화
@SpringBootApplication
public class WEBApplication { // 메인 클래스
    public static void main(String[] args) {
        SpringApplication.run(WEBApplication.class, args);//내장 WAS 실행
    }
}