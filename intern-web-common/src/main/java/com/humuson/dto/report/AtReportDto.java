package com.humuson.dto.report;

import com.humuson.domain.report.AtReport;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class AtReportDto {
    private String reserved_date;
    private String phone_number;
    private String request_uid;
    private String request_date;
    private String response_date;
    private String response_code;
    private String report_date;
    private String report_code;
    private String arrival_date;

    @Builder
    public AtReportDto(AtReport entity) {
        this.reserved_date = entity.getReserved_date();
        this.phone_number = entity.getPhone_number();
        this.request_uid = entity.getRequest_uid();
        this.request_date = entity.getRequest_date();
        this.response_date = entity.getResponse_date();
        this.response_code = entity.getResponse_code();
        this.report_date = entity.getReport_date();
        this.report_code = entity.getReport_code();
        this.arrival_date = entity.getArrival_date();
    }

}
