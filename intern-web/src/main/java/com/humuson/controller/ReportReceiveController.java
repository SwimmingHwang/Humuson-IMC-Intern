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
//import io.swagger.v3.oas.annotations.Operation;
//import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//@Tag(name="메시지 전송 결과 관리", description = "메시지 전송 결과를 받는 컨트롤러")
@RequiredArgsConstructor
@RestController
@Slf4j
public class ReportReceiveController {

    private final AtMsgsService atMsgsService;
    private final AtReportService atReportService;
    private final AtMsgsJdbcService atMsgsJdbcService;
    private final AtReportJdbcRepository atReportJdbcRepository;

    @GetMapping("/api/v1/report-test")
    public String saveAllReport() {
        log.info("api called : {}");
        return "api-v1-report";
    }
    // TODO : PutMapping 으로 바꿈
//    @Operation(summary = "status update 3", description = "agent db에서 log 데이터를 받아와 상태를 업데이트 해줍니다.")
    @PostMapping("/api/v1/at-report/{id}")
    @ResponseBody
    public String updateStatus(@PathVariable Integer id, @RequestBody String message) {
        log.info("update {} status api called ", id);
        atMsgsService.updateStatus(id, "3");
        // at report 저장
        Gson gson = new Gson();
        log.info("message:" + message);
        AtReportSaveRequestDto atReportSaveRequestDto = gson.fromJson(message, AtReportSaveRequestDto.class);
        AtReport atReport = atReportSaveRequestDto.toEntity();

        atReportService.save(atReport);
        return "update 3 and report saved";
    }
//    @PostMapping(value = "/api/at-msgs",produces = "application/json; charset=utf8")
//    @ResponseBody
//    public String apiAtMsgs(@RequestBody List<AtMsgsSaveRequestDto> requestDto) {
//        String stringStatusCode = "";
//        stringStatusCode = Producer.batchAtProduce(requestDto);
//        return stringStatusCode;//200 or 9000
//    }

}