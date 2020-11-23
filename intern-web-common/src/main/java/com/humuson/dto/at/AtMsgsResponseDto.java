/*
* 엔티티 필드중 일부만 사용해서 생성자로 엔티티를 받아 필드에 값을 넣는다.
* */
package com.humuson.dto.at;

import com.humuson.domain.msgs.AtMsgs;
import lombok.Getter;

@Getter
public class AtMsgsResponseDto {
    private Integer id;
    private String msg;
    private String phoneNumber;
    private String templateCode;

    public AtMsgsResponseDto(AtMsgs entity) {
        this.id = entity.getId();
        this.msg = entity.getMsg();
        this.phoneNumber = entity.getPhoneNumber();
        this.templateCode = entity.getTemplateCode();
    }


}
