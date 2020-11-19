package com.humuson.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class MsgsUpdateRequestDto {
    private String msg;
    private String phoneNumber;

    @Builder
    public MsgsUpdateRequestDto(String msg, String phoneNumber) {
        this.msg = msg;
        this.phoneNumber = phoneNumber;
    }
}
