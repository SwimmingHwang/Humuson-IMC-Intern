package com.example.main.dto;

import com.example.main.domain.entity.Customer;
import com.example.main.domain.msgs.AtMsgs;
import com.example.main.domain.msgs.MtMsgs;
import lombok.Builder;

import javax.persistence.Column;
import java.util.ArrayList;
import java.util.List;

public class MultiMtMsgsSaveListRequestDto {
    private String msg;
    private String reservedDate;
    private String adFlag;
    private String callback;
    private String status;
    private String priority;
    private String mtType;

    @Builder
    public MultiMtMsgsSaveListRequestDto(String msg, String reservedDate, String adFlag, String callback, String status, String priority, String mtType) {
        this.msg = msg;
        this.reservedDate = reservedDate;
        this.adFlag = adFlag;
        this.callback = callback;
        this.status = status;
        this.priority = priority;
        this.mtType = mtType;
    }

    public List<MtMsgs> toEntity(List<Customer> all) {
        List<MtMsgs> msgs = new ArrayList<>();
        for (Customer customer : all) {
            MtMsgs mtMsg = new MtMsgs(status,priority,reservedDate,callback, "82"+customer.getPhoneNumber().substring(1), mtType, adFlag, msg );
            msgs.add(mtMsg);
        }
        return msgs;
    }
}
