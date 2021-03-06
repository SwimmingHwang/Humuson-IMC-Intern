package com.humuson.controller;

import com.google.gson.Gson;
import com.humuson.domain.report.AtReport;
import com.humuson.domain.report.MtReport;
import com.humuson.dto.report.AtReportSaveRequestDto;
import com.humuson.dto.report.MtReportSaveRequestDto;
import com.humuson.service.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Tag(name="메시지 전송 결과 관리", description = "메시지 전송 결과를 받는 컨트롤러")
@RequiredArgsConstructor
@RestController
@Slf4j
public class ReportReceiveController {

    private final AtMsgsService atMsgsService;
    private final AtReportService atReportService;
    private final MtMsgsService mtMsgsService;
    private final MtReportService mtReportService;

    @GetMapping("/api/v1/report-test")
    public String saveAllReport() {
        log.info("api called : {}");
        return "api-v1-report";
    }
    // TODO : PutMapping 으로 바꿀 것
    @Operation(summary = "AT status update 3", description = "agent db에서 log 데이터를 받아와 상태를 업데이트 해줍니다.")
    @PostMapping("/api/v1/at-report/{id}")
    @ResponseBody
    public String updateStatusAt(@PathVariable Integer id, @RequestBody String message) {
        log.info("AT update {} status api called ", id);
        atMsgsService.updateStatus(id, "3");
        // at report 저장
        Gson gson = new Gson();
        log.info("message:" + message);
        AtReportSaveRequestDto atReportSaveRequestDto = gson.fromJson(message, AtReportSaveRequestDto.class);
        AtReport atReport = atReportSaveRequestDto.toEntity();

        atReportService.save(atReport);
        return "AT : update 3 and report saved";
    }

    // TODO : PutMapping 으로 바꿀 것
    @Operation(summary = "MT status update 3", description = "agent db에서 log 데이터를 받아와 상태를 업데이트 해줍니다.")
    @PostMapping("/api/v1/mt-report/{id}")
    @ResponseBody
    public String updateStatusMt(@PathVariable Integer id, @RequestBody String message) {
        log.info("MT update {} status api called ", id);
        mtMsgsService.updateStatus(id, "3");
        // at report 저장
        Gson gson = new Gson();
        log.info("message:" + message);
        MtReportSaveRequestDto mtReportSaveRequestDto = gson.fromJson(message, MtReportSaveRequestDto.class);
        MtReport mtReport = mtReportSaveRequestDto.toEntity();

        mtReportService.save(mtReport);
        return "MT : update 3 and report saved";
    }


}