package com.humuson.agent.dto;

import com.humuson.agent.domain.entity.FtReport;
import lombok.Builder;
import lombok.Getter;

@Getter
public class FtReportListDto {
    private Long id;
    private String status;
    private String priority;
    private String reserved_date;
    private String sender_key;
    private String phone_number;
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
    public FtReportListDto(FtReport entity) {
        this.id = entity.getId();
        this.status = entity.getStatus();
        this.priority = entity.getPriority();
        this.reserved_date = entity.getReserved_date();
        this.sender_key = entity.getSender_key();
        this.phone_number = entity.getPhone_number();
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
}