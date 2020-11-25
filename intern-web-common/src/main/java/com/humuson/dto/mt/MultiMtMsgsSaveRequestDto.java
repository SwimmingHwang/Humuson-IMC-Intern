package com.humuson.dto.mt;

import com.humuson.domain.msgs.MtMsgs;
import lombok.Builder;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

@Slf4j
public class MultiMtMsgsSaveRequestDto {
    private String msg;
    private String reservedDate;
    private String adFlag;
    private String status;
    private String callback;
    private String priority;
    private String mtType;
    private List<List<String>> customerList;
    private List<Integer> varCheckList;
    private String etc1;
    private String etc2;

    @Builder
    public MultiMtMsgsSaveRequestDto(String msg, String reservedDate, String adFlag, String status, String callback,
                                     String priority, String mtType, List<List<String>> customerList, List<Integer> varCheckList) {
        this.msg = msg;
        this.reservedDate = reservedDate;
        this.adFlag = adFlag;
        this.status = status;
        this.callback = callback;
        this.priority = priority;
        this.mtType = mtType;
        this.customerList = customerList;
        this.varCheckList = varCheckList;
        this.etc1 = etc1;
        this.etc2 = etc2;
    }

    public List<MtMsgs> toEntity() {
        List<MtMsgs> msgs = new ArrayList<>();
        for (List<String> li : customerList) {
            String msgCopied = msg + "";
            // 변수 매핑
            if (varCheckList.contains(1)==true){
                msgCopied = msgCopied.replace("#{변수1}",li.get(4));
            }
            if (varCheckList.contains(2)==true){
                msgCopied = msgCopied.replace("#{변수2}",li.get(5));
            }
            if (varCheckList.contains(3)==true){
                msgCopied = msgCopied.replace("#{변수3}",li.get(6));
            }

            MtMsgs mtMsg = new MtMsgs(status,priority,reservedDate,callback, li.get(3), mtType, adFlag, msgCopied, etc1, etc2);
            msgs.add(mtMsg);
        }
        return msgs;
    }
}
