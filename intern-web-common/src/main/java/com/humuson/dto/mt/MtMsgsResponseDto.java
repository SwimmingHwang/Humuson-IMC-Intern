/*
* 엔티티 필드중 일부만 사용해서 생성자로 엔티티를 받아 필드에 값을 넣는다.
* */
package com.humuson.dto.mt;

import com.humuson.domain.msgs.MtMsgs;
import lombok.Getter;

@Getter
public class MtMsgsResponseDto {
    private Integer id;
    private String msg;
    private String phoneNumber;
    private String reservedDate;
    private String mtType;
    private String callback;

    public MtMsgsResponseDto(MtMsgs entity) {
        this.id = entity.getId();
        this.msg = entity.getMsg();
        this.phoneNumber = entity.getPhoneNumber();
        this.reservedDate = entity.getReservedDate();
        this.mtType = entity.getMtType();
        this.callback = entity.getCallback();
    }
}
