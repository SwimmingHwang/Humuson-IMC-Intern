package com.example.main.dto;

import com.example.main.domain.entity.Customer;
import com.example.main.domain.msgs.AtMsgs;

public class CustomerListResponseDto {
    private long id;
    private String phoneNumber;

    public CustomerListResponseDto(Customer entity) {
        this.id = entity.getId();
        this.phoneNumber = entity.getPhoneNumber();
    }
}
