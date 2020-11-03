package com.example.custommainweb;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity // This tells Hibernate to make a table out of this class
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
//	private Integer id;
//
//	private String name;
//
//	private String email;
//
//	public Integer getId() {
//		return id;
//	}
//
//	public void setId(Integer id) {
//		this.id = id;
//	}
//
//	public String getName() {
//		return name;
//	}
//
//	public void setName(String name) {
//		this.name = name;
//	}
//
//	public String getEmail() {
//		return email;
//	}
//
//	public void setEmail(String email) {
//		this.email = email;
//	}
//}
    private Integer id;
    private String status;
    private String priority;
    private String reservedDate;
    private String senderKey;
    private String phoneNumber;
    //    private String appUserId;
    private String templateCode;
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