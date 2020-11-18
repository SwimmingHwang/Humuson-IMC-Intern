/*
* 엔티티 필드중 일부만 사용해서 생성자로 엔티티를 받아 필드에 값을 넣는다.
* */
package com.humuson.agent.dto;

import com.humuson.agent.domain.entity.AtMsgs;
import com.humuson.agent.domain.entity.FtMsgs;
import lombok.Builder;
import lombok.Getter;

@Getter
public class FtMsgsSaveRequestDto {

    private String msg;
    private String phoneNumber;
    private String templateCode;
    private String reservedDate;

    @Builder
    public FtMsgsSaveRequestDto(String msg, String phoneNumber, String templateCode, String reservedDate) {
        this.msg = msg;
        this.phoneNumber = phoneNumber;
        this.templateCode = templateCode;
        this.reservedDate = reservedDate;
    }

    public FtMsgs toEntity() {
        return FtMsgs.builder()
                .msg(msg)
                .phoneNumber(phoneNumber)
                .templateCode(templateCode)
                .reservedDate(reservedDate)
                .build();
    }

    @Override
    public String toString() {
        return "AtMsgs {" +
                ", reserved_dat:" + reservedDate +
                ", phone_number:" + phoneNumber +
                ", template_code:" + templateCode +
                ", messag:" + msg +
                "}";
    }
}
