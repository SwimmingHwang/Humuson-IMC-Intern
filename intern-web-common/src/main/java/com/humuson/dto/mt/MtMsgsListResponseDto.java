package com.humuson.dto.mt;

import com.humuson.domain.msgs.MtMsgs;

public class MtMsgsListResponseDto {
    private Integer id;
    private String title;
    private String msg;
    private String phoneNumber;

    public MtMsgsListResponseDto(MtMsgs entity) {
        this.id = entity.getId();
        this.msg = entity.getMsg();
        this.title = entity.getTitle();
        this.phoneNumber = entity.getPhoneNumber();
    }
}
