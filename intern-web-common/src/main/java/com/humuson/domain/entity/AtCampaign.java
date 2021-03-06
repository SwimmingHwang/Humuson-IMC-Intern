package com.humuson.domain.entity;

import com.humuson.domain.msgs.AtMsgs;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;

@DynamicInsert
@DynamicUpdate
@Setter
@Getter
@NoArgsConstructor
@Table(name = "imc_at_campaign")
@Entity
public class AtCampaign {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String campName;
    private String reservedDate;
    private String senderKey;
    private long count;
    @Column(name = "MESSAGE")
    private String msg;

    @OneToOne
    @JoinColumns({
            @JoinColumn(name="template_info_id", referencedColumnName = "id")
    })
    private TemplateInfo templateInfo;

    @Builder
    public AtCampaign(String campName, String reservedDate, String senderKey, long count, String msg, TemplateInfo templateInfo) {
        this.campName = campName;
        this.reservedDate = reservedDate;
        this.senderKey = senderKey;
        this.count = count;
        this.msg = msg;
        this.templateInfo = templateInfo;
    }
}
