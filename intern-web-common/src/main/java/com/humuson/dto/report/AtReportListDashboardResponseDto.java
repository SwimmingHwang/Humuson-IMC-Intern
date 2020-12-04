package com.humuson.dto.report;

import com.humuson.domain.report.AtReport;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class AtReportListDashboardResponseDto {
    private String response_date;
    private String report_code;

    @Builder
    public AtReportListDashboardResponseDto(AtReport entity) {
        this.response_date = entity.getResponse_date();
        this.report_code = entity.getReport_code();
    }

}
