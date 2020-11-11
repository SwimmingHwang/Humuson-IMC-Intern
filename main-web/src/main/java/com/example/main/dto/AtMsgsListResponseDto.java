package com.example.main.dto;

import com.example.main.domain.msgs.AtMsgs;

public class AtMsgsListResponseDto {
    private Integer id;
    private String msg;
    private String phoneNumber;

    public AtMsgsListResponseDto(AtMsgs entity) {
        this.id = entity.getId();
        this.msg = entity.getMsg();
        this.phoneNumber = entity.getPhoneNumber();
    }
}
