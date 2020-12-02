
/*
* Controller와 Service에서 사용할 Dto 클래스
* - entity 클래스와 유사하지만 Req/Res 클래스로 사용해서는 안 됨. : DB와 맞닿은 핵심클래스이기 때문에
* - Req/Res 용 DTO는 View를 위한 클래스라 자주 변경 필요!!!
* */

package com.humuson.dto.report;

import com.humuson.domain.report.AtReport;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class AtReportSaveRequestDto {
    private String reserved_date;
    private String phone_number;
    private String request_uid;
    private String request_date;
    private String response_date;
    private String response_code;
    private String report_date;
    private String report_code;
    private String arrival_date;
    private String etc1;
    private String etc2;

    @Builder
    public AtReportSaveRequestDto(AtReport entity) {
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

    public AtReport toEntity() {
        return AtReport.builder()
                .reserved_date(reserved_date)
                .phone_number(phone_number)
                .request_uid(request_uid)
                .request_date(request_date)
                .response_date(response_date)
                .response_code(response_code)
                .report_date(report_date)
                .report_code(report_code)
                .arrival_date(arrival_date)
                .build();
    }

}