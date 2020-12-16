package com.humuson.dto.at;

import com.humuson.domain.entity.AtCampaign;
import com.humuson.domain.entity.TemplateInfo;
import com.humuson.service.TemplateInfoService;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@ToString
@Setter
@Getter
@NoArgsConstructor
public class AtCampaignSaveRequestDto {

    private String campName;
    private String reservedDate;
    private String senderKey;
    private String senderName;
    private String msg;
    private long count;
    private String status;
    private TemplateInfo templateInfoC; // service에서 저장 로직 시 사용
    private String templateContent; // view에서 request시 사용
    private List<String> customers; // at msgs save controller에서
    private String vars;

    public AtCampaignSaveRequestDto(String campName, String reservedDate, String senderKey,
                                    String senderName, String msg, String templateContent, List<String> customers, String vars) {
        this.campName = campName;
        this.reservedDate = reservedDate;
        this.senderKey = senderKey;
        this.senderName = senderName;
        this.msg = msg;
        this.templateContent = templateContent;
        this.customers = customers;
        this.vars = vars;

    }


    public AtCampaign toEntity() {
        return AtCampaign.builder()
                .campName(campName)
                .reservedDate(reservedDate)
                .senderKey(senderKey)
                .msg(msg)
                .count(count)
                .status(status)
                .templateInfo(templateInfoC)
                .vars(vars)
                .build();
    }
}
