/*
* 엔티티 필드중 일부만 사용해서 생성자로 엔티티를 받아 필드에 값을 넣는다.
* */
package com.example.main.dto;

import com.example.main.domain.msgs.Msgs;
import com.example.main.domain.posts.Posts;
import lombok.Getter;

@Getter
public class MsgsResponseDto {
    private Integer id;
    private String msg;
    private String phoneNumber;

    public MsgsResponseDto(Msgs entity) {
        this.id = entity.getId();
        this.msg = entity.getMsg();
        this.phoneNumber = entity.getPhoneNumber();
    }
}
