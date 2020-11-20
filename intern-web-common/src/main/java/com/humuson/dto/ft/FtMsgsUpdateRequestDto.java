package com.humuson.dto.ft;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class FtMsgsUpdateRequestDto {
    private String msg;
    private String phoneNumber;

    @Builder
    public FtMsgsUpdateRequestDto(String msg, String phoneNumber) {
        this.msg = msg;
        this.phoneNumber = phoneNumber;
    }
}
