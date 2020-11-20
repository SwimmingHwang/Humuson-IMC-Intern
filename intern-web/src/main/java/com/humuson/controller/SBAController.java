package com.humuson.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RequiredArgsConstructor
@RestController
//@CrossOrigin(value = "http://localhost:9000")
public class SBAController {

    private final ApplicationContext context;

    public void close() {
        SpringApplication.exit(context);
    }

    @GetMapping("/sba/shutdown")
    public void shutdown() {
        log.info("application shutdown. bye bye~~");
        close();
    }

}
