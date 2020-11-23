package com.humuson.controller;

import com.humuson.service.SBAService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class SBAController {

    private final SBAService actuatorService;

    @DeleteMapping("/applications/{app_name}")
    public void shutdownAllApplications(@PathVariable String app_name) {
        actuatorService.shutdownApplication(app_name);
    }

}
