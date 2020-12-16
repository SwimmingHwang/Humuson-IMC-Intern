package com.humuson.dto.at;

import com.humuson.domain.entity.AtCampaign;
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
    private AtCampaign atCampaign;

    @Builder
    public MultiAtMsgsSaveRequestDto(String msg, String phoneNumber, String templateCode,
                                     String reservedDate, List<List<String>>  customerList , List<Integer> varCheckList) {
        this.msg = msg;
        this.phoneNumber = phoneNumber;
        this.templateCode = templateCode;
        this.reservedDate = reservedDate;
        this.customerList = customerList;
    }

    @Builder
    public MultiAtMsgsSaveRequestDto(String msg, String templateCode,
                                     String reservedDate, List<List<String>> customerList, AtCampaign atCampaign) {
        this.msg = msg;
        this.templateCode = templateCode;
        this.reservedDate = reservedDate;
        this.customerList = customerList;
        this.atCampaign = atCampaign;

    }
    public List<AtMsgs> toEntity() throws Exception {
        List<AtMsgs> msgs = new ArrayList<>();
        String varList = atCampaign.getVars();
        for (List<String> li : customerList) {
            String msgCopied = msg + "";
            /* user id, name, phone number, address, etc1, etc2, etc2
            * */
            for(String var:varList.split(",")) {
                if (!li.get(Integer.parseInt(var)).equals("")) {
                    if (var.equals("2"))
                        msgCopied = msgCopied.replaceFirst("#\\{(.|\n)*?\\}", "0" + li.get(Integer.parseInt(var)).substring(2));
                    else
                        msgCopied = msgCopied.replaceFirst("#\\{(.|\n)*?\\}", li.get(Integer.parseInt(var)));
                }
                else{
                    msgCopied = msgCopied.replaceFirst("#\\{(.|\n)*?\\}", "");
                    throw new Exception();
                }
            }
            AtMsgs atMsg = new AtMsgs(null, null, reservedDate, senderKey,
                    li.get(2), templateCode, msgCopied, null,null, atCampaign);
            msgs.add(atMsg);
        }
        return msgs;
    }

}
