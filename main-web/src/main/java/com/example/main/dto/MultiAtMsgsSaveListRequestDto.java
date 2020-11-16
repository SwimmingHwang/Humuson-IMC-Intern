package com.example.main.dto;

import com.example.main.domain.msgs.AtMsgs;
import lombok.Builder;

import java.util.ArrayList;
import java.util.List;

public class MultiAtMsgsSaveListRequestDto {
    private String msg;
    private String phoneNumber;
    private String templateCode;
    private String reservedDate;
    private List<List<String>> phoneNumList;

    @Builder
    public MultiAtMsgsSaveListRequestDto(String msg, String phoneNumber, String templateCode, String reservedDate, List<List<String>>  phoneNumList) {
        this.msg = msg;
        this.phoneNumber = phoneNumber;
        this.templateCode = templateCode;
        this.reservedDate = reservedDate;
        this.phoneNumList = phoneNumList;
    }

    public List<AtMsgs> toEntity() {
        List<AtMsgs> msgs = new ArrayList<>();
        for (List<String> li : phoneNumList) {
            System.out.println("res" +li.toString());
            AtMsgs atMsg = new AtMsgs(null,null,reservedDate,null, li.get(3),templateCode,msg,null);
            msgs.add(atMsg);
        }
        return msgs;
    }
}
