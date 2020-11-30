package com.humuson.dto.customer;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class GroupUpdateRequestDto {
    private String groupName;

    @Builder
    public GroupUpdateRequestDto(String groupName) {
        this.groupName = groupName;
    }
}
