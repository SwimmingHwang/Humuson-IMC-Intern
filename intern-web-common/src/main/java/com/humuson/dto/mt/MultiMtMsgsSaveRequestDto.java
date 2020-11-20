package com.humuson.dto.mt;

import com.humuson.domain.msgs.MtMsgs;
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
    private List<List<String>> customerList;

    @Builder
    public MultiMtMsgsSaveRequestDto(String msg, String reservedDate, String adFlag, String status, String callback, String priority, String mtType, List<List<String>> customerList) {
        this.msg = msg;
        this.reservedDate = reservedDate;
        this.adFlag = adFlag;
        this.status = status;
        this.callback = callback;
        this.priority = priority;
        this.mtType = mtType;
        this.customerList = customerList;
    }

    public List<MtMsgs> toEntity() {
        List<MtMsgs> msgs = new ArrayList<>();
        for (List<String> li : customerList) {
            System.out.println("Mtmsgs" +li.toString());
            MtMsgs mtMsg = new MtMsgs(status,priority,reservedDate,callback, li.get(3), mtType, adFlag, msg );
            msgs.add(mtMsg);
        }
        return msgs;
    }
}
