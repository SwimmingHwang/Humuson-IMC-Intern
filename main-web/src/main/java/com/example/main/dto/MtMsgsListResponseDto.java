package com.example.main.dto;

import com.example.main.domain.msgs.MtMsgs;

public class MtMsgsListResponseDto {
    private Integer id;
    private String msg;
    private String phoneNumber;

    public MtMsgsListResponseDto(MtMsgs entity) {
        this.id = entity.getId();
        this.msg = entity.getMsg();
        this.phoneNumber = entity.getPhoneNumber();
    }
}