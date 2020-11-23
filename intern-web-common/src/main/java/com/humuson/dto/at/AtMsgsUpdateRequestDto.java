package com.humuson.dto.at;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class AtMsgsUpdateRequestDto {
    private String msg;
    private String phoneNumber;
    private String templateCode;
    private String reservedDate;

    @Builder
    public AtMsgsUpdateRequestDto(String msg, String phoneNumber, String templateCode, String reservedDate) {
        this.msg = msg;
        this.phoneNumber = phoneNumber;
        this.templateCode = templateCode;
        this.reservedDate = reservedDate;
    }

}
