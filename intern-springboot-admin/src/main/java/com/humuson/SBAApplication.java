package com.humuson;

import de.codecentric.boot.admin.server.config.EnableAdminServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableAdminServer
public class SBAApplication {

    /*
        Spring Boot Actuator를 사용하면 모든 리소스 내용을 볼 수 있지만 json text 형식이라 한눈에 파악하기 힘듬
        Spring Boot Admin을 이용하여 Actuator의 내용을 실시간 형태의 Dashboard로 파악 가능하다.
     */
    public static void main(String[] args) {
        SpringApplication.run(SBAApplication.class, args);
    }

}