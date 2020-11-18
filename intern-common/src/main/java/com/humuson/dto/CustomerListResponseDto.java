package com.humuson.dto;

import com.humuson.domain.Entity.Customer;

public class CustomerListResponseDto {
    private long id;
    private String phoneNumber;

    public CustomerListResponseDto(Customer entity) {
        this.id = entity.getId();
        this.phoneNumber = entity.getPhoneNumber();
    }
}
