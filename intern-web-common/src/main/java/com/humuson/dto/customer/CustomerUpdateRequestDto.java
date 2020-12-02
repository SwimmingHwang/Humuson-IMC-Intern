package com.humuson.dto.customer;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CustomerUpdateRequestDto {
    private String phoneNumber;
    private String userId;
    private String name;
    private String address;

    @Builder
    public CustomerUpdateRequestDto(String phoneNumber, String userId, String name, String address) {
        this.phoneNumber = phoneNumber;
        this.userId = userId;
        this.name = name;
        this.address = address;
    }
}
