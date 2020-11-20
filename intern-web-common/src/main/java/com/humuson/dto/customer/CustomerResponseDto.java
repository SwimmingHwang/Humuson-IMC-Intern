/*
* 엔티티 필드중 일부만 사용해서 생성자로 엔티티를 받아 필드에 값을 넣는다.
* */
package com.humuson.dto.customer;

import com.humuson.domain.entity.Customer;
import lombok.Getter;

@Getter
public class CustomerResponseDto {
    private long id;
    private String userId;
    private String name;
    private String phoneNumber;
    private String var1;
    private String var2;
    private String var3;

    public CustomerResponseDto(Customer entity) {
        this.id = entity.getId();
        this.userId = entity.getUserId();
        this.name = entity.getName();
        this.phoneNumber = entity.getPhoneNumber();
        this.var1 = entity.getVar1();
        this.var2 = entity.getVar2();
        this.var3 = entity.getVar3();
    }
}
