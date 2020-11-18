package com.humuson.domain.msgs;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@Table(name = "imc_at_biz_msg")
@Entity //JPA의 어노테이션
public class AtMsgs {
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
    public AtMsgs(String status, String priority, String reserved_date, String senderkey, String phone_number, String template_code, String message, String etc1, String etc2, String etc3) {
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

    @Override
    public String toString() {
        return "AtMsgs {" +
                "id:" + id +
                ", status:" + status +
                ", priority:" + priority +
                ", reserved_dat:" + reserved_date +
                ", senderkey:" + senderkey +
                ", phone_number:" + phone_number +
                ", template_code:" + template_code +
                ", messag:" + message +
                ", etc1:" + etc1 +
                ", etc2:" + etc2 +
                ", etc3:" + etc3 +
                "}";
    }
}