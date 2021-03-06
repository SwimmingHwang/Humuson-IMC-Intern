
/*
* Controller와 Service에서 사용할 Dto 클래스
* - entity 클래스와 유사하지만 Req/Res 클래스로 사용해서는 안 됨. : DB와 맞닿은 핵심클래스이기 때문에
* - Req/Res 용 DTO는 View를 위한 클래스라 자주 변경 필요!!!
* */

package com.humuson.dto.customer;

import com.humuson.domain.entity.Customer;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CustomerSaveRequestDto {
    private String phoneNumber;
    private String userId;
    private String name;
    private String address;

    @Builder
    public CustomerSaveRequestDto(String phoneNumber, String userId, String name, String address) {
        this.phoneNumber = phoneNumber;
        this.userId = userId;
        this.name = name;
        this.address = address;
    }

    public Customer toEntity() {
        return Customer.builder()
                .phoneNumber(phoneNumber)
                .userId(userId)
                .name(name)
                .address(address)
                .build();
    }
}