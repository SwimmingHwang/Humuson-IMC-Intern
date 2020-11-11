package com.example.main.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class MtMsgsUpdateRequestDto {
    private String msg;
    private String phoneNumber;

    @Builder
    public MtMsgsUpdateRequestDto(String msg, String phoneNumber) {
        this.msg = msg;
        this.phoneNumber = phoneNumber;
    }
}
