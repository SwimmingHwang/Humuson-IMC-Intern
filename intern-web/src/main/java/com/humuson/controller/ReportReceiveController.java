package com.humuson.controller;

import com.google.gson.Gson;
import com.humuson.domain.msgs.AtMsgs;
import com.humuson.domain.report.AtReport;
import com.humuson.domain.report.AtReportJdbcRepository;
import com.humuson.dto.report.AtReportDto;
import com.humuson.service.AtMsgsJdbcService;
import com.humuson.service.AtReportService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//@Tag(name="알림톡 상태 수정", description = "알림톡의 상태를 수정합니다.")
@RequiredArgsConstructor
@RestController
@Slf4j
public class ReportReceiveController {

    private final AtReportService atReportService;
    private final AtMsgsJdbcService atMsgsJdbcService;
    private final AtReportJdbcRepository atReportJdbcRepository;

    @PostMapping("/api/v1/report")
    public String saveAllReport(@RequestBody String message) {
        log.info("{}",message);
        Gson gson = new Gson();
        AtReportDto atReportDto = gson.fromJson(message, AtReportDto.class);
        atReportService.save(atReportDto);
        return "123";
    }

    @PutMapping("/api/v1/at-report/{id}")
    public void updateAllStatus(@PathVariable Integer id, @RequestBody List<Integer> idList) {
//        atMsgsJdbcService.updateStatus(idList);
    }



}