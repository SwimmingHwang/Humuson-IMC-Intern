package com.humuson.dto.at;

import com.humuson.domain.msgs.AtMsgs;
import lombok.Builder;
import lombok.extern.slf4j.Slf4j;
import java.util.ArrayList;
import java.util.List;


@Slf4j
public class MultiAtMsgsSaveRequestDto {
    private String msg;
    private String phoneNumber;
    private String templateCode;
    private String reservedDate;
    private String senderKey;
    private List<List<String>> customerList;
    private List<Integer> varCheckList;

    @Builder
    public MultiAtMsgsSaveRequestDto(String msg, String phoneNumber, String templateCode,
                                     String reservedDate, List<List<String>>  customerList , List<Integer> varCheckList) {
        this.msg = msg;
        this.phoneNumber = phoneNumber;
        this.templateCode = templateCode;
        this.reservedDate = reservedDate;
        this.customerList = customerList;
        this.varCheckList = varCheckList;
    }

    @Builder
    public MultiAtMsgsSaveRequestDto(String msg, String templateCode,
                                     String reservedDate, List<List<String>> customerList) {
        this.msg = msg;
        this.templateCode = templateCode;
        this.reservedDate = reservedDate;
        this.customerList = customerList;
    }

//        public List<AtMsgs> toEntity() {
//        List<AtMsgs> msgs = new ArrayList<>();
//        log.info("고객 정보 리스트 "+customerList.toString()+"");
//        log.info("변수 체크 박스 리스트 "+varCheckList.toString()+"");
//
//
//        for (List<String> li : customerList) {
//            String msgCopied = msg + "";
//            // 변수 매핑
//            if (varCheckList.contains(1)==true){
//                msgCopied = msgCopied.replace("#{변수1}",li.get(4));
//            }
//            if (varCheckList.contains(2)==true){
//                msgCopied = msgCopied.replace("#{변수2}",li.get(5));
//            }
//            if (varCheckList.contains(3)==true){
//                msgCopied = msgCopied.replace("#{변수3}",li.get(6));
//            }
//
//            AtMsgs atMsg = new AtMsgs(null,null,reservedDate,null, li.get(3),
//                    templateCode, msgCopied,null,null);
//            msgs.add(atMsg);
//        }
//        return msgs;
//    }
    public List<AtMsgs> toEntity() {
        List<AtMsgs> msgs = new ArrayList<>();
        for (List<String> li : customerList) {
        //    public AtMsgs(String status, String priority, String reservedDate, String senderKey,
        //                  String phoneNumber, String templateCode, String msg, String etc1, String etc2){
//            log.info(li.toString());
            AtMsgs atMsg = new AtMsgs(null, null, reservedDate, senderKey,
                    li.get(0), templateCode, msg, null,null);
            msgs.add(atMsg);
        }
        return msgs;
    }

}
