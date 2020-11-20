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
    private String var1;
    private String var2;
    private String var3;

    @Builder
    public CustomerUpdateRequestDto(String phoneNumber, String userId, String name, String var1, String var2, String var3) {
        this.phoneNumber = phoneNumber;
        this.userId = userId;
        this.name = name;
        this.var1 = var1;
        this.var2 = var2;
        this.var3 = var3;
    }

}
