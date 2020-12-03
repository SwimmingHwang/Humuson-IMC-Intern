package com.humuson.domain.report;

import com.humuson.domain.msgs.AtMsgs;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Table(name = "imc_at_report")
@Entity
public class AtReport {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, length = 19)
    private String reserved_date;
    @Column(nullable = false, length = 16)
    private String phone_number;
    @Column(length = 40)
    private String request_uid;
    @Column(length = 19)
    private String request_date;
    @Column(length = 19)
    private String response_date;
    @Column(length = 5)
    private String response_code;
    @Column(length = 19)
    private String report_date;
    @Column(length = 5)
    private String report_code;
    @Column(length = 19)
    private String arrival_date;

    @Builder
    public AtReport(String reserved_date, String phone_number, String request_uid, String request_date, String response_date, String response_code, String report_date, String report_code, String arrival_date) {
        this.reserved_date = reserved_date;
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