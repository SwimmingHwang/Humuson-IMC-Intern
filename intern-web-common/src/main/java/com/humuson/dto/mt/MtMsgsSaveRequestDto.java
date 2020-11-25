
/*
* Controller와 Service에서 사용할 Dto 클래스
* - entity 클래스와 유사하지만 Req/Res 클래스로 사용해서는 안 됨. : DB와 맞닿은 핵심클래스이기 때문에
* - Req/Res 용 DTO는 View를 위한 클래스라 자주 변경 필요!!!
* */

package com.humuson.dto.mt;

import com.humuson.domain.msgs.MtMsgs;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class MtMsgsSaveRequestDto {
    private String msg;
    private String phoneNumber;
    private String adFlag;
    private String mtType;
    private String reservedDate;
    private String callback;

    @Builder
    public MtMsgsSaveRequestDto(String msg, String phoneNumber, String adFlag, String mtType, String reservedDate, String callback) {
        this.msg = msg;
        this.phoneNumber = phoneNumber;
        this.adFlag = adFlag;
        this.mtType = mtType;
        this.reservedDate = reservedDate;
        this.callback = callback;
    }

    public MtMsgs toEntity() {
        return MtMsgs.builder()
                .msg(msg)
                .phoneNumber(phoneNumber)
                .adFlag(adFlag)
                .mtType(mtType)
                .callback(callback)
                .reservedDate(reservedDate)
                .build();
    }
}