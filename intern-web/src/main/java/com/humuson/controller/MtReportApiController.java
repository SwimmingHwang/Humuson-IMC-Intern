package com.humuson.controller;

import com.humuson.domain.report.MtReport;
import com.humuson.service.MtReportService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Tag(name="문자 레포트", description = "대시보드에 필요한 문자 레포트 정보들을 전달합니다.")
@RequiredArgsConstructor
@RestController
public class MtReportApiController {

    private final MtReportService mtReportService;

    @Operation(summary="문자 레포트 결과 정보 조회", description = "발송된 문자 메세지에 대한 결과 정보를 조희")
    @GetMapping("/api/v1/mt-report/list")
    public List<MtReport> mtReportFindAll() {
        return mtReportService.findAll();
    }

}
