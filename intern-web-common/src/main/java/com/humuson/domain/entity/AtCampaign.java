package com.humuson.domain.entity;

import com.humuson.domain.msgs.AtMsgs;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

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
    private String status;
    private String customers;
    private String vars;

    @OneToOne
    @JoinColumns({
            @JoinColumn(name="template_info_id", referencedColumnName = "id")
    })
    private TemplateInfo templateInfo;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "camp_id", referencedColumnName = "id")
    private List<AtMsgs> atMsgs = new ArrayList<>();


    @Builder
    public AtCampaign(String campName, String reservedDate, String senderKey, long count, String msg,
                      TemplateInfo templateInfo, String status, String customers, String vars) {
        this.campName = campName;
        this.reservedDate = reservedDate;
        this.senderKey = senderKey;
        this.count = count;
        this.msg = msg;
        this.templateInfo = templateInfo;
        this.status = status == null? "1":status;
        this.customers = customers;
        this.vars = vars;
    }

    public void update(String campName, String reservedDate, String senderKey, long count, String msg, TemplateInfo templateInfo){
        this.campName = campName;
        this.reservedDate = reservedDate;
        this.senderKey = senderKey;
        this.count = count;
        this.msg = msg;
        this.templateInfo = templateInfo;
    }

    public void updateStatus(String status) {
        this.status = status;
    }
    public void updateMsgs(List<AtMsgs> atMsgs){this.atMsgs = atMsgs;}
}
