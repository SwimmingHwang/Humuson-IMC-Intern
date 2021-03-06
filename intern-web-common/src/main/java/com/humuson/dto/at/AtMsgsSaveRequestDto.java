
/*
* Controller와 Service에서 사용할 Dto 클래스
* - entity 클래스와 유사하지만 Req/Res 클래스로 사용해서는 안 됨. : DB와 맞닿은 핵심클래스이기 때문에
* - Req/Res 용 DTO는 View를 위한 클래스라 자주 변경 필요!!!
* */

package com.humuson.dto.at;

import com.humuson.domain.msgs.AtMsgs;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class AtMsgsSaveRequestDto {
    private String msg;
    private String phoneNumber;
    private String templateCode;
    private String reservedDate;
    private String senderKey;
    private String etc1;
    private String etc2;
    private Integer id;

    @Builder
    public AtMsgsSaveRequestDto(String msg, String phoneNumber, String templateCode, String reservedDate, String senderKey, String etc1, String etc2) {
        this.msg = msg;
        this.phoneNumber = phoneNumber;
        this.templateCode = templateCode;
        this.reservedDate = reservedDate;
        this.senderKey = senderKey;
        this.etc1 = etc1;
        this.etc2 = etc2;
    }

    public AtMsgs toEntity() {
        return AtMsgs.builder()
                .msg(msg)
                .phoneNumber(phoneNumber)
                .templateCode(templateCode)
                .reservedDate(reservedDate)
                .senderKey(senderKey)
                .etc1(etc1)
                .etc2(etc2)
                .build();
    }

    /*@Override
    public String toString() {
        return "AtMsgs {" +
                ", reserved_dat:" + reservedDate +
                ", phone_number:" + phoneNumber +
                ", template_code:" + templateCode +
                ", message:" + msg +
                ", etc1:" + etc1 +
                ", etc2:" + etc2 +
                "}";
    }*/
}