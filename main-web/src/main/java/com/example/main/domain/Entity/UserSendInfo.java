package com.example.main.domain.entity;

import com.example.main.domain.Entity.UserEntity;
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
@Table(name = "imc_user_send_info", schema = "imc-intern")
@Entity //JPA의 어노테이션
public class UserSendInfo {
    @Id
    @GeneratedValue
    private long id;

    @NotNull
    @Column(name="SENDER_KEY")
    private String senderKey;

    @OneToOne(fetch = FetchType.LAZY)
    @MapsId
    private UserEntity user;


    @Builder
    public UserSendInfo(long id, String senderKey) {
        this.id = id;
        this.senderKey = senderKey;
    }

}
