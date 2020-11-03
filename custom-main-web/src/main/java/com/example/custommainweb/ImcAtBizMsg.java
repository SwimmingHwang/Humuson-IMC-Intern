package com.example.custommainweb;


import com.sun.istack.NotNull;

import javax.persistence.*;

@Entity // This tells Hibernate to make a table out of this class
@Table(name = "imc_at_biz_msg")
public class ImcAtBizMsg {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @NotNull
    private String status;
    @NotNull
    private String priority;
    @NotNull
    private String reservedDate;
    @NotNull
    private String senderKey;
    @NotNull
    private String phoneNumber;
    //    private String appUserId;
    @NotNull
    private String templateCode;
    @Column(name = "MESSAGE")
    private String msg; // 메시지 내용

    public Integer getId() {
        return id;
    }

    public String getStatus() {
        return status;
    }

    public String getPriority() {
        return priority;
    }

    public String getReservedDate() {
        return reservedDate;
    }

    public String getSenderKey() {
        return senderKey;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getTemplateCode() {
        return templateCode;
    }

    public String getMsg() {
        return msg;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public void setReservedDate(String reservedDate) {
        this.reservedDate = reservedDate;
    }

    public void setSenderKey(String senderKey) {
        this.senderKey = senderKey;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setTemplateCode(String templateCode) {
        this.templateCode = templateCode;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

}