package com.humuson.dto.mt;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class MtMsgsUpdateRequestDto {
    private String reservedDate;
    private String mtType;
    private String callback;
    private String title;
    private String msg;
    private String phoneNumber;
    private String adFlag;

    @Builder
    public MtMsgsUpdateRequestDto(String reservedDate, String mtType, String callback, String title, String msg, String phoneNumber, String adFlag) {
        this.reservedDate = reservedDate;
        this.mtType = mtType;
        this.callback = callback;
        this.title = title;
        this.msg = msg;
        this.phoneNumber = phoneNumber;
        this.adFlag = adFlag;
    }
}
