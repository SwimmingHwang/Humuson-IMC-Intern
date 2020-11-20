package com.humuson.dto.ft;


import com.humuson.domain.msgs.FtMsgs;

public class FtMsgsListResponseDto {
    private Integer id;
    private String msg;
    private String phoneNumber;

    public FtMsgsListResponseDto(FtMsgs entity) {
        this.id = entity.getId();
        this.msg = entity.getMsg();
        this.phoneNumber = entity.getPhoneNumber();
    }
}
