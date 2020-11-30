package com.humuson.dto.customer;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CustomerGroupUpdateRequestDto {
    private String groupName;

    @Builder
    public CustomerGroupUpdateRequestDto(String groupName) {
        this.groupName = groupName;
    }
}
