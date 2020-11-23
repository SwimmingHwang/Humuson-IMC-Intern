package com.humuson.agent.dto;

import com.humuson.agent.domain.entity.MtMsgs;
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
    public MtMsgsSaveRequestDto(String msg, String phoneNumber, String adFlag, String callback, String mtType, String reservedDate) {
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