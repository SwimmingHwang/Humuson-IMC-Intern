/*
* 엔티티 필드중 일부만 사용해서 생성자로 엔티티를 받아 필드에 값을 넣는다.
* */
package com.humuson.dto.customer;

import com.humuson.domain.entity.Customer;
import com.humuson.domain.msgs.Msgs;
import lombok.Getter;

@Getter
public class CustomerResponseDto {
    private long id;
    private String userId;
    private String name;
    private String phoneNumber;

    public CustomerResponseDto(Customer entity) {
        this.id = entity.getId();
        this.userId = entity.getUserId();
        this.name = entity.getName();
        this.phoneNumber = entity.getPhoneNumber();
    }

    public Customer toEntity() {
        return Customer.builder()
                .id(id)
                .userId(userId)
                .name(name)
                .phoneNumber(phoneNumber)
                .build();
    }
}
