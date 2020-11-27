package com.humuson.domain.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;

@DynamicInsert
@DynamicUpdate
@Getter
@NoArgsConstructor
@Table(name = "imc_mt_campaign")
@Entity
public class MtCampaign {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String reservedDate;
    private String callback;
    private long count;
    @Column(name = "MESSAGE")
    private String msg;

    @Builder
    public MtCampaign(long id, String reservedDate, String callback, long count, String msg) {
        this.id = id;
        this.reservedDate = reservedDate;
        this.callback = callback;
        this.count = count;
        this.msg = msg;
    }
}
