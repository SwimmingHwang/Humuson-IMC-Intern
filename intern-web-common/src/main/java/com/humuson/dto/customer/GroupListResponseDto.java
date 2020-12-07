package com.humuson.dto.customer;

import com.humuson.domain.entity.Customer;
import com.humuson.domain.entity.Group;

public class GroupListResponseDto {
    private long id;


    public GroupListResponseDto(Group entity) {
        this.id = entity.getId();
    }
}
