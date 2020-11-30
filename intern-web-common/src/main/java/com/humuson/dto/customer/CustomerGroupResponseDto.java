/*
* 엔티티 필드중 일부만 사용해서 생성자로 엔티티를 받아 필드에 값을 넣는다.
* */
package com.humuson.dto.customer;

import com.humuson.domain.entity.Customer;
import com.humuson.domain.entity.CustomerGroup;
import lombok.Getter;

@Getter
public class CustomerGroupResponseDto {

    private String groupName;

    public CustomerGroupResponseDto(CustomerGroup entity) {
        this.groupName = entity.getGroupName();
    }
}
