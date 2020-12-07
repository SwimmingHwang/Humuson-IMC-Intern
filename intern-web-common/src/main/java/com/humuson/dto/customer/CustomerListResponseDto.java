package com.humuson.dto.customer;

import com.humuson.domain.entity.Customer;

import java.io.Serializable;

public class CustomerListResponseDto{
    private long id;
    private String phoneNumber;


    public CustomerListResponseDto(Customer entity) {
        this.id = entity.getId();
        this.phoneNumber = entity.getPhoneNumber();
    }
}
