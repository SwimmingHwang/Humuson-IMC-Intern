package com.humuson.dto.at;

import com.humuson.domain.msgs.AtMsgs;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class AtMsgsListDashboardResponseDto {
    private String status;
    private String reserved_date;

    @Builder
    public AtMsgsListDashboardResponseDto(AtMsgs entity) {
        this.status = entity.getStatus();
        this.reserved_date = entity.getReservedDate();
    }

}
