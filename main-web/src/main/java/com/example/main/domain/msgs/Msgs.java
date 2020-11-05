/*
 *  테이블과 연결되어있어 엔티티 글래스를 기준으로 테이블이 생성되고 스키마가 변경되기 때문에 DTO필요
 * */
package com.example.main.domain.msgs;

import com.example.main.domain.BaseTimeEntity;
import com.sun.istack.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;

@DynamicInsert // insert 시 null인 필드 제외
@DynamicUpdate // update 시
@Getter
@NoArgsConstructor
@Table(name = "imc_at_biz_msg", schema = "imc-intern")
@Entity //JPA의 어노테이션
public class Msgs {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @NotNull
    @ColumnDefault("0")
    private String status;

    @NotNull
    @ColumnDefault("0")
    private String priority;

    @NotNull
    @ColumnDefault("1999-99-99")
    private String reservedDate;

    @NotNull
    @ColumnDefault("0")
    private String senderKey;

    @NotNull
    @ColumnDefault("0")
    private String phoneNumber;

    //    private String appUserId;
    @NotNull
    @ColumnDefault("0")
    private String templateCode;

    @Column(name = "MESSAGE")
    @ColumnDefault("0")
    private String msg; // 메시지 내용


    @Builder // 해당 클래스의 빌더 패턴 클래스를 생성
    public Msgs(String status, String priority, String reservedDate, String senderKey,
                String phoneNumber, String templateCode, String msg){
        this.status = status;
        this.priority = priority;
        this.reservedDate = reservedDate;
        this.senderKey = senderKey;
        this.phoneNumber = phoneNumber;
        this.templateCode = templateCode;
        this.msg = msg;
    }

    public void update(String msg){
        this.msg = msg;
    }
    /**
     * insert 되기전 (persist 되기전) 실행된다.
     * */
    @PrePersist
    public void prePersist() {

        this.status = this.status== null ? "0" : this.status;
        this.priority = this.priority== null ? "0" : this.priority;
        this.reservedDate = this.reservedDate== null ? "0" : this.reservedDate;
        this.senderKey = this.senderKey== null ? "0" : this.senderKey;
        this.phoneNumber = this.phoneNumber== null ? "0" : this.phoneNumber;
        this.templateCode = this.templateCode== null ? "0" : this.templateCode;
        this.msg = this.msg== null ? "0" : this.msg;

    }

}
