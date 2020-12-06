package com.humuson.agent.domain.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Table(name = "imc_at_biz_msg_log")
@Entity
public class AtReport {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, length = 1)
    private String status;
    @Column(nullable = false, length = 1)
    private String priority;
    @Column(nullable = false, length = 19)
    private String reserved_date;
    @Column(nullable = false, length = 40)
    private String sender_key;
    @Column(nullable = false, length = 16)
    private String phone_number;
    @Column(nullable = false, length = 30)
    private String template_code;
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
    @Column(length = 64)
    private String etc1;
    @Column(length = 64)
    private String etc2;

    @Builder
    public AtReport(String status, String priority, String reserved_date, String sender_key, String phone_number, String template_code, String message, String request_uid, String request_date, String response_date, String response_code, String report_type, String report_date, String report_code, String arrival_date, String etc1, String etc2) {
        this.status = status;
        this.priority = priority;
        this.reserved_date = reserved_date;
        this.sender_key = sender_key;
        this.phone_number = phone_number;
        this.template_code = template_code;
        this.message = message;
        this.request_uid = request_uid;
        this.request_date = request_date;
        this.response_date = response_date;
        this.response_code = response_code;
        this.report_type = report_type;
        this.report_date = report_date;
        this.report_code = report_code;
        this.arrival_date = arrival_date;
        this.etc1 = etc1;
        this.etc2 = etc2;
    }

    public void setEtc1Status(String etc1) {
        this.etc1 = etc1;
    }
}