package com.humuson.dto.at;

import com.humuson.domain.msgs.AtMsgs;

public class AtMsgsListResponseDto {
    private Integer id;
    private String reservedDate;
    private String msg;
    private String phoneNumber;
    private String templateCode;
    private String status;

    public AtMsgsListResponseDto(AtMsgs entity) {
        this.id = entity.getId();
        this.reservedDate = entity.getReservedDate();
        this.msg = entity.getMsg();
        this.phoneNumber = entity.getPhoneNumber();
        this.templateCode = entity.getTemplateCode();
        this.status = entity.getStatus();
    }
}
