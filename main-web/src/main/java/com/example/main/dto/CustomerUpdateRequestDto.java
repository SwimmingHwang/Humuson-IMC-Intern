package com.example.main.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CustomerUpdateRequestDto {
    private String phoneNumber;

    @Builder
    public CustomerUpdateRequestDto( String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
