package com.example.main.domain.entity;

import com.example.main.domain.BaseTimeEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@DynamicInsert // insert 시 null인 필드 제외
@DynamicUpdate // update 시
@Getter
@NoArgsConstructor
@Table(name = "imc_profile", schema = "imc-intern")
@Entity //JPA의 어노테이션
public class Profile extends BaseTimeEntity {
    @Id
    @GeneratedValue
    private long id;

    private String profileId; // @humuson
    @Column(name="SENDER_KEY")
    private String senderKey; //fsjflasjfklasjdflak
    private String senderName; //휴머스온
    private String phoneNumber; // 플러스친구를 개설한 관리자 핸드폰 번호를 입력하세요

    @ManyToOne
    @JoinColumns({
            @JoinColumn(name="user_id", referencedColumnName = "id")
    })
    private UserEntity user;

    @Builder
    public Profile(long id, String profileId, String senderKey, String senderName, String phoneNumber, UserEntity user) {
        this.id = id;
        this.profileId = profileId;
        this.senderKey = senderKey;
        this.senderName = senderName;
        this.phoneNumber = phoneNumber;
        this.user = user;
    }
}
