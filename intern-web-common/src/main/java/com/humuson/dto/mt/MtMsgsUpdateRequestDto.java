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
    private String msg;
    private String phoneNumber;

    @Builder
    public MtMsgsUpdateRequestDto(String reservedDate, String mtType, String callback, String msg, String phoneNumber) {
        this.reservedDate = reservedDate;
        this.mtType = mtType;
        this.callback = callback;
        this.msg = msg;
        this.phoneNumber = phoneNumber;
    }
}
