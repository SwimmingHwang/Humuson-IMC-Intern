package com.humuson.agent.domain.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Table(name = "imc_mt_msg_log")
@Entity
public class MtReport {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(nullable = false, length = 1)
    private String status;
    @Column(nullable = false, length = 1)
    private String priority;
    @Column(nullable = false, length = 19)
    private String reserved_date;
    @Column(nullable = false, length = 2)
    private String mt_type;
    @Column(nullable = false, length = 1)
    private String ad_flag;
    @Column(length = 16)
    private String callback;
    @Column(nullable = false, length = 16)
    private String phone_number;
    @Column(length = 100)
    private String title;
    @Column(nullable = false, length = 3000)
    private String message;
    @Column(length = 40)
    private String request_uid;
    @Column(length = 19)
    private String request_date;
    @Column(length = 19)
    private String response_date;
    @Column(length = 5)
    private String response_code;
    @Column(length = 2)
    private String report_type;
    @Column(length = 19)
    private String report_date;
    @Column(length = 5)
    private String report_code;
    @Column(length = 19)
    private String arrival_date;
    @Column(length = 2)
    private String sender_code;
    @Column(length = 64)
    private String etc1;
    @Column(length = 64)
    private String etc2;
    @Column(length = 64)
    private String etc3;

    @Builder
    public MtReport(String status, String priority, String reserved_date, String mt_type, String ad_flag, String callback, String phone_number, String title, String message, String request_uid, String request_date, String response_date, String response_code, String report_type, String report_date, String report_code, String arrival_date, String sender_code, String etc1, String etc2, String etc3) {
        this.status = status;
        this.priority = priority;
        this.reserved_date = reserved_date;
        this.mt_type = mt_type;
        this.ad_flag = ad_flag;
        this.callback = callback;
        this.phone_number = phone_number;
        this.title = title;
        this.message = message;
        this.request_uid = request_uid;
        this.request_date = request_date;
        this.response_date = response_date;
        this.response_code = response_code;
        this.report_type = report_type;
        this.report_date = report_date;
        this.report_code = report_code;
        this.arrival_date = arrival_date;
        this.sender_code = sender_code;
        this.etc1 = etc1;
        this.etc2 = etc2;
        this.etc3 = etc3;
    }

    public void setEtc1Status(String etc1) {
        this.etc1 = etc1;
    }
}
