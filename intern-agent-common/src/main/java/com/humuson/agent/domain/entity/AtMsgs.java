package com.humuson.agent.domain.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@Table(name = "imc_at_biz_msg")
@Entity //JPA의 어노테이션
public class AtMsgs {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name="status")
    private String status;

    @Column(name="priority")
    private String priority;

    @Column(name="reserved_date")
    private String reservedDate;

    @Column(name="sender_key")
    private String senderKey;

    @Column(name="phone_number")
    private String phoneNumber;

    @Column(name="template_code")
    private String templateCode;

    @Column(name="message")
    private String msg;

    @Column(name="etc1")
    private String etc1;

    @Column(name="etc2")
    private String etc2;


    @Builder // 해당 클래스의 빌더 패턴 클래스를 생성
    public AtMsgs(String status, String priority, String reservedDate, String senderKey,
                  String phoneNumber, String templateCode, String msg, String etc1, String etc2){
        this.status = status== null ? "1" : status;
        this.priority = priority== null ? "N" : priority; // S-Slow, N-Normal, F-Fast
        this.reservedDate = reservedDate== null ? "null" : reservedDate; // yyyyMMddhhmmss (hh:24h)
        this.senderKey = senderKey== null ? "54ef196697bda7dbc36a45a334beb83580d8ca2a" : senderKey;
        this.phoneNumber = phoneNumber== null ? "phoneNumber" : phoneNumber; //821012345678
        this.templateCode = templateCode== null ? "null" : templateCode;
        this.msg = msg== null ? "NULL MESSAGE" : msg;
        this.etc1 = etc1 == null? "0" : etc1;
        this.etc2 = etc2;
    }

    @PrePersist
    public void prePersist() {
        this.status = this.status== null ? "1" : this.status;
        this.priority = this.priority== null ? "N" : this.priority; // S-Slow, N-Normal, F-Fast
        this.reservedDate = this.reservedDate== null ? "null" : this.reservedDate; // yyyyMMddhhmmss (hh:24h)
        this.senderKey = this.senderKey== null ? "54ef196697bda7dbc36a45a334beb83580d8ca2a" : this.senderKey;
        this.phoneNumber = this.phoneNumber== null ? "phoneNumber" : this.phoneNumber; //821012345678
        this.templateCode = this.templateCode== null ? "null" : this.templateCode;
        this.msg = this.msg== null ? "NULL MESSAGE" : this.msg;
        this.etc1 = this.etc1 == null? "0" : this.etc1;
        this.etc2 = this.etc2 == null ? "http://localhost:8080/api/v1/at-report/" : this.etc2;

    }

}
