package com.humuson.dto.at;

import com.humuson.domain.entity.Customer;
import com.humuson.domain.msgs.AtMsgs;
import lombok.Builder;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Getter
public class MultiAtMsgsSaveListRequestDto {
    private String msg;
    private String phoneNumber;
    private String templateCode;
    private String reservedDate;
    private List<Integer> varCheckList;

    @Builder
    public MultiAtMsgsSaveListRequestDto(String msg, String phoneNumber, String templateCode, String reservedDate,
                                         List<Integer> varCheckList){
        this.msg = msg;
        this.phoneNumber = phoneNumber;
        this.templateCode = templateCode;
        this.reservedDate = reservedDate;
        this.varCheckList = varCheckList;
    }

    public List<AtMsgs> toEntity(List<Customer> all) {
        List<AtMsgs> msgs = new ArrayList<>();

        log.info("변수 체크 박스 리스트 "+varCheckList.toString()+"");

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
            AtMsgs atMsg = new AtMsgs(null,null,reservedDate,null,
                    "82"+customer.getPhoneNumber().substring(1),templateCode,msgCopied,null);
            msgs.add(atMsg);
        }
        return msgs;
    }
}
