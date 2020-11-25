package com.humuson.controller;

import com.google.gson.Gson;
import com.humuson.domain.msgs.AtMsgs;
import com.humuson.domain.report.AtReport;
import com.humuson.domain.report.AtReportJdbcRepository;
import com.humuson.dto.report.AtReportDto;
import com.humuson.dto.report.AtReportSaveRequestDto;
import com.humuson.service.AtMsgsJdbcService;
import com.humuson.service.AtMsgsService;
import com.humuson.service.AtReportService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//@Tag(name="알림톡 상태 수정", description = "알림톡의 상태를 수정합니다.")
@RequiredArgsConstructor
@Slf4j
@RestController
public class ReportReceiveController {

    private final AtMsgsService atMsgsService;
    private final AtReportService atReportService;
    private final AtMsgsJdbcService atMsgsJdbcService;
    private final AtReportJdbcRepository atReportJdbcRepository;

//    @PostMapping("/api/v1/report")
//    public void saveAllReport(@RequestBody String message) {
//        log.info("api called : {}",message);
//        Gson gson = new Gson();
//        AtReportDto atReportDto = gson.fromJson(message, AtReportDto.class);
//        atReportService.save(atReportDto);
//        return "atReportDto.getEtc2();
//    }

    @PutMapping(value="/api/v1/at-report/{id}")
    public void updateStatus(@PathVariable long id, @RequestBody String message) {
        log.info("update {} status api called ", id);
        Integer iid = (int)id;
        atMsgsService.updateStatus(iid, "3");
        // at report 저장
        Gson gson = new Gson();
        AtReportSaveRequestDto atReportSaveRequestDto = gson.fromJson(message, AtReportSaveRequestDto.class);
        AtReport atReport = atReportSaveRequestDto.toEntity();
        atReportService.save(atReport);
    }
}