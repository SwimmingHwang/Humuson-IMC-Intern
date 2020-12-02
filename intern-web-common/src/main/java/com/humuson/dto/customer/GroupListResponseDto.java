package com.humuson.dto.customer;

import com.humuson.domain.entity.Customer;
import com.humuson.domain.entity.Group;

public class GroupListResponseDto {
    private long id;
    private String phoneNumber;


    public GroupListResponseDto(Group entity) {
        this.id = entity.getId();
        this.phoneNumber = entity.getGroupName();
    }
}
