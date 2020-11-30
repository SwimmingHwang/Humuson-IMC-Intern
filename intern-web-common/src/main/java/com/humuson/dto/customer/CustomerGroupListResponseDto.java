package com.humuson.dto.customer;

import com.humuson.domain.entity.Customer;
import com.humuson.domain.entity.CustomerGroup;

public class CustomerGroupListResponseDto {
    private long id;
    private String phoneNumber;


    public CustomerGroupListResponseDto(CustomerGroup entity) {
        this.id = entity.getId();
        this.phoneNumber = entity.getGroupName();
    }
}
