package com.humuson.controller;

import com.humuson.actuator.HealthCheck;
import com.humuson.service.SBAService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.actuate.health.Health;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class SBAController {

    private final HealthCheck healthCheck;
    private final SBAService actuatorService;

    @GetMapping("/healthInfo")
    public Health healthInfo() {
        return healthCheck.health();
    }

    @DeleteMapping("/applications/{app_name}")
    public void shutdownAllApplications(@PathVariable String app_name) {
        actuatorService.shutdownApplication(app_name);
    }

}
