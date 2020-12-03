package com.humuson.controller;

import com.humuson.domain.report.AtReport;
import com.humuson.service.AtReportService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Tag(name="알림톡 레포트", description = "대시보드에 필요한 알림톡 레포트 정보들을 전달합니다.")
@RequiredArgsConstructor
@RestController
public class AtReportApiController {

    private final AtReportService atReportService;

    @Operation(summary="알림톡 레포트 결과 정보 조회", description = "발송된 알림톡 메세지에 대한 결과 정보를 조희")
    @GetMapping("/api/v1/at-report/list")
    public List<AtReport> atReportFindAll() {
        return atReportService.findAll();
    }

}
