package com.humuson.agent.dto;

import lombok.Getter;

@Getter
public class MtReportDto {
    private Long id;
    private String status;
    private String priority;
    private String reserved_date;
    private String phone_number;
    private String callback;
    private String mt_type;
    private String ad_flag;
    private String message;
    private String etc1;
    private String etc2;
    private String etc3;

    public MtReportDto(String status, String priority, String reserved_date, String callback,
                       String phone_number, String mt_type, String ad_flag, String msg, String etc1, String etc2, String etc3){
        this.status = status;
        this.priority = priority;
        this.reserved_date = reserved_date;
        this.callback = callback;
        this.phone_number = phone_number;
        this.mt_type = mt_type;
        this.ad_flag = ad_flag;
        this.message = msg;
        this.etc1 = etc1;
        this.etc2 = etc2;
        this.etc3 = etc3;
    }
}