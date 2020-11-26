
/*
* Controller와 Service에서 사용할 Dto 클래스
* - entity 클래스와 유사하지만 Req/Res 클래스로 사용해서는 안 됨. : DB와 맞닿은 핵심클래스이기 때문에
* - Req/Res 용 DTO는 View를 위한 클래스라 자주 변경 필요!!!
* */

package com.humuson.agent.dto;

import com.humuson.agent.domain.entity.AtReport;
import com.humuson.agent.domain.entity.MtReport;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class MtReportSaveRequestDto {
    private String status;
    private String priority;
    private String reserved_date;
    private String mt_type;
    private String ad_flag;
    private String callback;
    private String phone_number;
    private String title;
    private String message;
    private String request_uid;
    private String request_date;
    private String response_date;
    private String response_code;
    private String report_type;
    private String report_date;
    private String report_code;
    private String arrival_date;
    private String sender_code;
    private String etc1;
    private String etc2;

    @Builder
    public MtReportSaveRequestDto(MtReport entity) {
        this.status = entity.getStatus();
        this.priority = entity.getPriority();
        this.reserved_date = entity.getReserved_date();
        this.mt_type = entity.getMt_type();
        this.ad_flag = entity.getAd_flag();
        this.callback = entity.getCallback();
        this.phone_number = entity.getPhone_number();
        this.title = entity.getTitle();
        this.message = entity.getMessage();
        this.request_uid = entity.getRequest_uid();
        this.request_date = entity.getRequest_date();
        this.response_date = entity.getResponse_date();
        this.response_code = entity.getResponse_code();
        this.report_type = entity.getReport_type();
        this.report_date = entity.getReport_date();
        this.report_code = entity.getReport_code();
        this.arrival_date = entity.getArrival_date();
        this.sender_code = entity.getSender_code();
        this.etc1 = entity.getEtc1();
        this.etc2 = entity.getEtc2();
    }

    public MtReport toEntity() {
        return MtReport.builder()
                .status(status)
                .priority(priority)
                .reserved_date(reserved_date)
                .mt_type(mt_type)
                .ad_flag(ad_flag)
                .callback(callback)
                .phone_number(phone_number)
                .title(title)
                .message(message)
                .request_uid(request_uid)
                .request_date(request_date)
                .response_date(response_date)
                .response_code(response_code)
                .report_type(report_type)
                .report_date(report_date)
                .report_code(report_code)
                .arrival_date(arrival_date)
                .sender_code(sender_code)
                .etc1(etc1)
                .etc2(etc2)
                .build();
    }
    
//    @Override
//    public String toString() {
//        return "AtReport {" +
//                " status:" + status +
//                ", priority:" + priority +
//                ", reserved_date:" + reserved_date +
//                ", sender_key:" + sender_key +
//                ", phone_number:" + phone_number +
//                ", template_code:" + template_code +
//                ", message:" + message +
//                ", request_uid:" + request_uid +
//                ", request_date:" + request_date +
//                ", response_date:" + response_date +
//                ", response_code:" + response_code +
//                ", report_type:" + report_type +
//                ", report_date:" + report_date +
//                ", report_code:" + report_code +
//                ", arrival_date:" + arrival_date +
//                ", etc1:" + etc1 +
//                ", etc2:" + etc2 +
//                "}";
//    }
}