
/*
* Controller와 Service에서 사용할 Dto 클래스
* - entity 클래스와 유사하지만 Req/Res 클래스로 사용해서는 안 됨. : DB와 맞닿은 핵심클래스이기 때문에
* - Req/Res 용 DTO는 View를 위한 클래스라 자주 변경 필요!!!
* */

package com.humuson.dto.customer;

import com.humuson.domain.entity.Customer;
import com.humuson.domain.entity.CustomerGroup;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CustomerGroupSaveRequestDto {
    private String groupName;

    @Builder
    public CustomerGroupSaveRequestDto(String groupName) {
        this.groupName = groupName;
    }

    public CustomerGroup toEntity() {
        return CustomerGroup.builder()
                .groupName(groupName)
                .build();
    }
}