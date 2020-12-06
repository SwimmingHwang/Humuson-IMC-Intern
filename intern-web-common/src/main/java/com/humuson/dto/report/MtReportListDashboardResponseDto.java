package com.humuson.dto.report;

import com.humuson.domain.report.MtReport;
import lombok.Getter;

@Getter
public class MtReportListDashboardResponseDto {
    private String response_date;
    private String report_code;

    public MtReportListDashboardResponseDto(MtReport entity) {
        this.response_date = entity.getResponse_date();
        this.report_code = entity.getReport_code();
    }

}