package com.example.main.dto;

import com.example.main.domain.msgs.AtMsgs;
import com.example.main.domain.msgs.MtMsgs;
import lombok.Builder;

import java.util.ArrayList;
import java.util.List;

public class MultiMtMsgsSaveRequestDto {
    private String msg;
    private String reservedDate;
    private String adFlag;
    private String status;
    private String callback;
    private String priority;
    private String mtType;
    private List<List<String>> phoneNumList;

    @Builder
    public MultiMtMsgsSaveRequestDto(String msg, String reservedDate, String adFlag, String status, String callback, String priority, String mtType, List<List<String>> phoneNumList) {
        this.msg = msg;
        this.reservedDate = reservedDate;
        this.adFlag = adFlag;
        this.status = status;
        this.callback = callback;
        this.priority = priority;
        this.mtType = mtType;
        this.phoneNumList = phoneNumList;
    }

    public List<MtMsgs> toEntity() {
        List<MtMsgs> msgs = new ArrayList<>();
        for (List<String> li : phoneNumList) {
            System.out.println("Mtmsgs" +li.toString());
            MtMsgs mtMsg = new MtMsgs(status,priority,reservedDate,callback, li.get(3), mtType, adFlag, msg );
            msgs.add(mtMsg);
        }
        return msgs;
    }
}
