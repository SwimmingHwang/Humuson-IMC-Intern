package com.humuson.dto.at;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class AtMsgsUpdateRequestDto {
    private String msg;
    private String phoneNumber;

    @Builder
    public AtMsgsUpdateRequestDto(String msg, String phoneNumber) {
        this.msg = msg;
        this.phoneNumber = phoneNumber;
    }
}
