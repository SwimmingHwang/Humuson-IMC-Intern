package com.humuson.domain.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@Table(name = "imc_at_biz_msg_log")
@Entity //JPA의 어노테이션
public class AtMsgsLog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String status;
    private String priority;
    private String reserved_date;
    private String senderkey;
    private String phone_number;
    private String template_code;
    private String message;
    private String etc1;
    private String etc2;
    private String etc3;

    @Builder
    public AtMsgsLog(String status, String priority, String reserved_date, String senderkey, String phone_number, String template_code, String message, String etc1, String etc2, String etc3) {
        this.status = status;
        this.priority = priority;
        this.reserved_date = reserved_date;
        this.senderkey = senderkey;
        this.phone_number = phone_number;
        this.template_code = template_code;
        this.message = message;
        this.etc1 = etc1;
        this.etc2 = etc2;
        this.etc3 = etc3;
    }

    public void setEtc1Status(String etc1) {
        this.etc1 = etc1;
    }
}