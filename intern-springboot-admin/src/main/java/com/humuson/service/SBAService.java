package com.humuson.service;

import com.humuson.utiliy.ApiCall;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class SBAService {
    public void shutdownApplication(String app_name) {
        String port = null;
        if(app_name.equals("intern-web")) {
            port = "8080";
        } else if(app_name.equals("intern-admin")) {
            port = "8081";
        } else if(app_name.equals("intern-kafka-api")) {
            port = "8082";
        } else if(app_name.equals("intern-consumer-client")) {
            port = "8083";
        } else if(app_name.equals("intern-producer-batch")) {
            port = "8084";
        } else {
            log.info("failed shutdown");
            return;
        }

        String url = "http://localhost:" + port + "/sba/shutdown";
        ApiCall.get(url);
//        String statusCode = ApiCall.post(url,"");
//        if(statusCode.equals("200")) {
//            log.info("succeeded shutdown {}", app_name);
//        } else {
//            log.info("failed shutdown {}", app_name);
//        }
    }
}
