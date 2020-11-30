package com.humuson.dto.customer;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@ToString
@Getter
@NoArgsConstructor
public class GroupCustomerUpdateRequestDto {

    private String groupName;
    private List<String> notCustomerIdStrList;

    @Builder
    public GroupCustomerUpdateRequestDto(String groupName, List<String> notCustomerIdList) {
        this.groupName = groupName;
        this.notCustomerIdStrList = notCustomerIdList;
    }


}