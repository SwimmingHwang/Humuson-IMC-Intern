package com.humuson.dto.mt;

import com.humuson.domain.msgs.MtMsgs;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class MtMsgsListDashboardResponseDto {
    private String status;
    private String reserved_date;

    @Builder
    public MtMsgsListDashboardResponseDto(MtMsgs entity) {
        this.status = entity.getStatus();
        this.reserved_date = entity.getReservedDate();
    }

}
