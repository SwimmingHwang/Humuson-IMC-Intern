package com.example.main.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CustomerUpdateRequestDto {
    private String phoneNumber;
    private String userId;
    private String name;

    @Builder
    public CustomerUpdateRequestDto(String phoneNumber, String userId, String name) {
        this.phoneNumber = phoneNumber;
        this.userId = userId;
        this.name = name;
    }

}
