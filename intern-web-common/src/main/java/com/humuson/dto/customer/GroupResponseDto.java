/*
* 엔티티 필드중 일부만 사용해서 생성자로 엔티티를 받아 필드에 값을 넣는다.
* */
package com.humuson.dto.customer;

import com.humuson.domain.entity.Customer;
import com.humuson.domain.entity.Group;
import lombok.Getter;

@Getter
public class GroupResponseDto {

    private String groupName;

    public GroupResponseDto(Group entity) {
        this.groupName = entity.getGroupName();
    }
}
