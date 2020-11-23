package com.humuson.domain.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Table(name = "imc_ft_biz_msg_log")
@Entity
public class FtMsgsLog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String status;
    private String priority;
    private String reserved_date;
    private String sender_key;
    private String phone_number;
    private String message;
    private String etc1;
    private String etc2;
    private String etc3;

    @Builder
    public FtMsgsLog(String status, String priority, String reserved_date, String sender_key, String phone_number, String message, String etc1, String etc2, String etc3) {
        this.status = status;
        this.priority = priority;
        this.reserved_date = reserved_date;
        this.sender_key = sender_key;
        this.phone_number = phone_number;
        this.message = message;
        this.etc1 = etc1;
        this.etc2 = etc2;
        this.etc3 = etc3;
    }

    public void setEtc1Status(String etc1) {
        this.etc1 = etc1;
    }
}
