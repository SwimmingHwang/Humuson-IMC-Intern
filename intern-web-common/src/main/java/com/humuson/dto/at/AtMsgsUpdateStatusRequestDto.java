package com.humuson.dto.at;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class AtMsgsUpdateStatusRequestDto {
    private String status;


    @Builder
    public AtMsgsUpdateStatusRequestDto(String status) {
        this.status = status;
    }
}
