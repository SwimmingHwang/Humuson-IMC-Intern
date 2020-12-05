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

//    private TemplateInfoService templateInfoService;

    private String campName;
    private String reservedDate;
    private String senderKey;
    private String msg;
    // TODO : 잘 모르겠음. view에서 어떻게 전달올지
    private TemplateInfo templateInfoC;
    private  String templateInfo;
    private String templateCode;
//    private List<String> groups; //
    private List<String> customers; // at msgs save controller에서

    public AtCampaign toEntity() {
        return AtCampaign.builder()
                .campName(campName)
                .reservedDate(reservedDate)
                .senderKey(senderKey)
                .msg(msg)
                .templateInfo(templateInfoC)
                .build();
    }
}
