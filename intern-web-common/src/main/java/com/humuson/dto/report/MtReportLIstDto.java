package com.humuson.dto.report;

import lombok.Builder;
import lombok.Getter;

@Getter
public class MtReportLIstDto {
    private String reserved_date;
    private String callback;
    private String phone_number;
    private String request_uid;
    private String request_date;
    private String response_date;
    private String response_code;
    private String report_date;
    private String report_code;
    private String arrival_date;

    @Builder
    public MtReportLIstDto(String reserved_date, String callback, String phone_number, String request_uid, String request_date, String response_date, String response_code, String report_date, String report_code, String arrival_date) {
        this.reserved_date = reserved_date;
        this.callback = callback;
        this.phone_number = phone_number;
        this.request_uid = request_uid;
        this.request_date = request_date;
        this.response_date = response_date;
        this.response_code = response_code;
        this.report_date = report_date;
        this.report_code = report_code;
        this.arrival_date = arrival_date;
    }
}