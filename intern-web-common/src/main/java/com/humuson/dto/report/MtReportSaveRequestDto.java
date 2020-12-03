
/*
* Controller와 Service에서 사용할 Dto 클래스
* - entity 클래스와 유사하지만 Req/Res 클래스로 사용해서는 안 됨. : DB와 맞닿은 핵심클래스이기 때문에
* - Req/Res 용 DTO는 View를 위한 클래스라 자주 변경 필요!!!
* */

package com.humuson.dto.report;

import com.humuson.domain.report.MtReport;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class MtReportSaveRequestDto {
    private String reserved_date;
    private String callback;
    private String phone_number;
    private String request_uid;
    private String request_date;
    private String response_date;
    private String response_code;
    private String report_type;
    private String report_date;
    private String report_code;
    private String arrival_date;
    private String etc1;
    private String etc2;

    @Builder
    public MtReportSaveRequestDto(String reserved_date, String callback, String phone_number, String request_uid, String request_date, String response_date, String response_code, String report_type, String report_date, String report_code, String arrival_date) {
        this.reserved_date = reserved_date;
        this.callback = callback;
        this.phone_number = phone_number;
        this.request_uid = request_uid;
        this.request_date = request_date;
        this.response_date = response_date;
        this.response_code = response_code;
        this.report_type = report_type;
        this.report_date = report_date;
        this.report_code = report_code;
        this.arrival_date = arrival_date;
    }

    public MtReport toEntity() {
        return MtReport.builder()
                .reserved_date(reserved_date)
                .callback(callback)
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