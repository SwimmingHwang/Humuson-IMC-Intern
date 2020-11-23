package com.humuson.dto;

import com.humuson.domain.entity.FtMsgsLog;
import lombok.Getter;

@Getter
public class FtMsgsLogListDto {
    private Long id;
    private String status;
    private String priority;
    private String reserved_date;
    private String sender_key;
    private String phone_number;
    private String message;
    private String etc1;
    private String etc2;
    private String etc3;

    public FtMsgsLogListDto(FtMsgsLog entity) {
        this.id = entity.getId();
        this.status = entity.getStatus();
        this.priority = entity.getPriority();
        this.reserved_date = entity.getReserved_date();
        this.sender_key = entity.getSender_key();
        this.phone_number = entity.getPhone_number();
        this.message = entity.getMessage();
        this.etc1 = entity.getEtc1();
        this.etc2 = entity.getEtc2();
        this.etc3 = entity.getEtc3();
    }
}