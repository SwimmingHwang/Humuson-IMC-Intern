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

    @Builder
    public MtReport(String status, String priority, String reserved_date, String callback,
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

    public void setEtc1Status(String etc1) {
        this.etc1 = etc1;
    }
}
