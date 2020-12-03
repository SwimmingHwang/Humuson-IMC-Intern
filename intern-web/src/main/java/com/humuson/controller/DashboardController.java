package com.humuson.controller;

import com.humuson.domain.report.AtReport;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@Controller
public class DashboardController {

    @GetMapping("/dashboard/at-report")
    public String getAtReportList() {

        return "api/v1/at-report/list";
    }


}
