package com.humuson.agent.dto;

import com.humuson.agent.domain.entity.AtReport;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class AtReportDto {
    private String status;
    private String priority;
    private String reserved_date;
    private String sender_key;
    private String phone_number;
    private String template_code;
    private String message;
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
    public AtReportDto(AtReport entity) {
        this.status = entity.getStatus();
        this.priority = entity.getPriority();
        this.reserved_date = entity.getReserved_date();
        this.sender_key = entity.getSender_key();
        this.phone_number = entity.getPhone_number();
        this.template_code = entity.getTemplate_code();
        this.message = entity.getMessage();
        this.request_uid = entity.getRequest_uid();
        this.request_date = entity.getRequest_date();
        this.response_date = entity.getResponse_date();
        this.response_code = entity.getResponse_code();
        this.report_type = entity.getReport_type();
        this.report_date = entity.getReport_date();
        this.report_code = entity.getReport_code();
        this.arrival_date = entity.getArrival_date();
        this.etc1 = entity.getEtc1();
        this.etc2 = entity.getEtc2();
    }

    public AtReport toEntity(){
        return AtReport.builder()
                .status(status)
                .priority(priority)
                .reserved_date(reserved_date)
                .sender_key(sender_key)
                .phone_number(phone_number)
                .template_code(template_code)
                .message(message)
                .request_uid(request_uid)
                .request_date(request_date)
                .response_date(response_date)
                .response_code(response_code)
                .report_type(report_type)
                .report_date(report_date)
                .report_code(report_code)
                .arrival_date(arrival_date)
                .etc1(etc1)
                .etc2(etc2)
                .build();
    }

    @Override
    public String toString() {
        return "AtReport {" +
                " status:" + status +
                ", priority:" + priority +
                ", reserved_date:" + reserved_date +
                ", sender_key:" + sender_key +
                ", phone_number:" + phone_number +
                ", template_code:" + template_code +
                ", message:" + message +
                ", request_uid:" + request_uid +
                ", request_date:" + request_date +
                ", response_date:" + response_date +
                ", response_code:" + response_code +
                ", report_type:" + report_type +
                ", report_date:" + report_date +
                ", report_code:" + report_code +
                ", arrival_date:" + arrival_date +
                ", etc1:" + etc1 +
                ", etc2:" + etc2 +
                "}";
    }
}
