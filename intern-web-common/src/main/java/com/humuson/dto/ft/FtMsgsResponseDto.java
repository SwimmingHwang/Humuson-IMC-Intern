/*
* 엔티티 필드중 일부만 사용해서 생성자로 엔티티를 받아 필드에 값을 넣는다.
* */
package com.humuson.dto.ft;

import com.humuson.domain.msgs.FtMsgs;
import lombok.Getter;

@Getter
public class FtMsgsResponseDto {
    private Integer id;
    private String msg;
    private String phoneNumber;

    public FtMsgsResponseDto(FtMsgs entity) {
        this.id = entity.getId();
        this.msg = entity.getMsg();
        this.phoneNumber = entity.getPhoneNumber();
    }
}
