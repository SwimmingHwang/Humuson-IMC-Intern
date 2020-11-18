package com.humuson.dto;

import com.humuson.domain.msgs.AtMsgsLog;
import lombok.Getter;

@Getter
public class AtMsgsLogDto {
    private Long id;
    private String status;
    private String priority;
    private String reserved_date;
    private String senderkey;
    private String phone_number;
    private String template_code;
    private String message;
    private String etc1;
    private String etc2;
    private String etc3;

    public AtMsgsLogDto(AtMsgsLog entity) {
        this.id = entity.getId();
        this.status = entity.getStatus();
        this.priority = entity.getPriority();
        this.reserved_date = entity.getReserved_date();
        this.senderkey = entity.getSenderkey();
        this.phone_number = entity.getPhone_number();
        this.template_code = entity.getTemplate_code();
        this.message = entity.getMessage();
        this.etc1 = entity.getEtc1();
        this.etc2 = entity.getEtc2();
        this.etc3 = entity.getEtc3();
    }
}
