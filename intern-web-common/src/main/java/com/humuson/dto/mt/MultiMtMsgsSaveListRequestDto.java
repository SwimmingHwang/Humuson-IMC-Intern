package com.humuson.dto.mt;

import com.humuson.domain.entity.Customer;
import com.humuson.domain.msgs.MtMsgs;
import lombok.Builder;

import java.util.ArrayList;
import java.util.List;

public class MultiMtMsgsSaveListRequestDto {
    private String msg;
    private String reservedDate;
    private String adFlag;
    private String callback;
    private String title;
    private String status;
    private String priority;
    private String mtType;
    private List<Integer> varCheckList;
    private String etc1;
    private String etc2;


    @Builder
    public MultiMtMsgsSaveListRequestDto(String msg, String reservedDate, String adFlag, String callback, String title, String status,
                                         String priority, String mtType, List<Integer> varCheckList, String etc1, String etc2) {
        this.msg = msg;
        this.reservedDate = reservedDate;
        this.adFlag = adFlag;
        this.callback = callback;
        this.title = title;
        this.status = status;
        this.priority = priority;
        this.mtType = mtType;
        this.varCheckList = varCheckList;
        this.etc1 = etc1;
        this.etc2 = etc2;

    }

    public List<MtMsgs> toEntity(List<Customer> all) {
        List<MtMsgs> msgs = new ArrayList<>();
        for (Customer customer : all) {
            String msgCopied = msg + "";

            // 변수 매핑
            if (varCheckList.contains(1)==true){
                msgCopied = msgCopied.replace("#{변수1}", customer.getVar1()== null? "" : customer.getVar1());
            }
            if (varCheckList.contains(2)==true){
                msgCopied = msgCopied.replace("#{변수2}", customer.getVar2()== null? "" : customer.getVar2());
            }
            if (varCheckList.contains(3)==true){
                msgCopied = msgCopied.replace("#{변수3}", customer.getVar3()== null? "" : customer.getVar3());
            }
            MtMsgs mtMsg = new MtMsgs(status, priority, reservedDate, callback, title,
                    "82"+customer.getPhoneNumber().substring(1), mtType, adFlag, msgCopied, etc1, etc2 );
            msgs.add(mtMsg);
        }
        return msgs;
    }
}
