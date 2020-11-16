/*
* 엔티티 필드중 일부만 사용해서 생성자로 엔티티를 받아 필드에 값을 넣는다.
* */
package com.example.main.dto;

import com.example.main.domain.entity.Customer;
import com.example.main.domain.msgs.AtMsgs;
import lombok.Getter;

@Getter
public class CustomerResponseDto {
    private long id;
    private String phoneNumber;

    public CustomerResponseDto(Customer entity) {
        this.id = entity.getId();
        this.phoneNumber = entity.getPhoneNumber();
    }
}
