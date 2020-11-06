package com.example.main.dto;

import com.example.main.domain.msgs.Msgs;
import com.example.main.domain.posts.Posts;

import java.time.LocalDateTime;

public class MsgsListResponseDto {
    private Integer id;
    private String msg;
    private String phoneNumber;

    public MsgsListResponseDto(Msgs entity) {
        this.id = entity.getId();
        this.msg = entity.getMsg();
        this.phoneNumber = entity.getPhoneNumber();
    }
}
