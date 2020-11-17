package com.example.main.dto;

import com.example.main.domain.entity.Customer;
import com.example.main.domain.msgs.AtMsgs;
import lombok.Builder;

import java.util.ArrayList;
import java.util.List;

public class MultiAtMsgsSaveListRequestDto {
    private String msg;
    private String phoneNumber;
    private String templateCode;
    private String reservedDate;

    @Builder
    public MultiAtMsgsSaveListRequestDto(String msg, String phoneNumber, String templateCode, String reservedDate){
        this.msg = msg;
        this.phoneNumber = phoneNumber;
        this.templateCode = templateCode;
        this.reservedDate = reservedDate;
    }

    public List<AtMsgs> toEntity(List<Customer> all) {
        List<AtMsgs> msgs = new ArrayList<>();
        for (Customer customer : all) {
            AtMsgs atMsg = new AtMsgs(null,null,reservedDate,null, "82"+customer.getPhoneNumber().substring(1),templateCode,msg,null);
            msgs.add(atMsg);
        }
        return msgs;
    }
}
