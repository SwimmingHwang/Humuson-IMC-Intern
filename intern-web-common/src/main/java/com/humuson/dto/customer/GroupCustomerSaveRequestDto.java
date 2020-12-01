package com.humuson.dto.customer;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@ToString
@Getter
@NoArgsConstructor
public class GroupCustomerSaveRequestDto {

    private String groupName;
    private List<String> customerIdStrList;

    @Builder
    public GroupCustomerSaveRequestDto(String groupName, List<String> customerIdList) {
        this.groupName = groupName;
        this.customerIdStrList = customerIdList;
    }


}