
/*
* Controller와 Service에서 사용할 Dto 클래스
* - entity 클래스와 유사하지만 Req/Res 클래스로 사용해서는 안 됨. : DB와 맞닿은 핵심클래스이기 때문에
* - Req/Res 용 DTO는 View를 위한 클래스라 자주 변경 필요!!!
* */

package com.example.main.dto;

import com.example.main.domain.msgs.FtMsgs;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class FtMsgsSaveRequestDto {
    private String msg;
    private String phoneNumber;

    @Builder
    public FtMsgsSaveRequestDto(String msg, String phoneNumber) {
        this.msg = msg;
        this.phoneNumber = phoneNumber;
    }
    public FtMsgs toEntity() {
        return FtMsgs.builder()
                .msg(msg)
                .phoneNumber(phoneNumber)
                .build();
    }
}