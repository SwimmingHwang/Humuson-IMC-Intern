package com.humuson.domain.user;

import com.sun.istack.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;

@DynamicInsert // insert 시 null인 필드 제외
@DynamicUpdate // update 시
@Getter
@NoArgsConstructor
@Table(name = "imc_user_send_info")
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
    private User user;


    @Builder
    public UserSendInfo(long id, String senderKey) {
        this.id = id;
        this.senderKey = senderKey;
    }

}
