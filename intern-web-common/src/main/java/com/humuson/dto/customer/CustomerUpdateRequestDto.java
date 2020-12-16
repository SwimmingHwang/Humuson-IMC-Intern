package com.humuson.dto.customer;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CustomerUpdateRequestDto {
    private String phoneNumber;
    private String userId;
    private String name;
    private String address;
    private String etc1;
    private String etc2;
    private String etc3;

    @Builder
    public CustomerUpdateRequestDto(String phoneNumber, String userId, String name, String address,
                                    String etc1, String etc2, String etc3) {
        this.phoneNumber = phoneNumber;
        this.userId = userId;
        this.name = name;
        this.address = address;
        this.etc1 = etc1;
        this.etc2 = etc2;
        this.etc3 = etc3;
    }
}
