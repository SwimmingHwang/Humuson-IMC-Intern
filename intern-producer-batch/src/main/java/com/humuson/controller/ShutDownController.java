package com.humuson.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequiredArgsConstructor
@RestController
public class ShutDownController {

    private final ApplicationContext context;

    @GetMapping("/sba/shutdown")
    public void shutdown() {
        log.info("application shutdown. bye bye~~");
        SpringApplication.exit(context);
    }

}
