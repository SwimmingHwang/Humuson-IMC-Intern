/*
 *  테이블과 연결되어있어 엔티티 글래스를 기준으로 테이블이 생성되고 스키마가 변경되기 때문에 DTO필요
 * */
package com.humuson.domain.msgs;

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
@Table(name = "imc_mt_msg")
@Entity //JPA의 어노테이션
public class MtMsgs {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String status;
    private String priority;
    private String reservedDate;
    private String phoneNumber;
    private String mtType;
    private String adFlag; //CHAR(1)
    @Column(name = "MESSAGE")
    private String msg; // 메시지 내용

    @Builder // 해당 클래스의 빌더 패턴 클래스를 생성
    public MtMsgs(String status, String priority, String reservedDate, String senderKey,
                  String phoneNumber, String mtType, String adFlag, String msg){
        this.status = status;
        this.priority = priority;
        this.reservedDate = reservedDate;
        this.phoneNumber = phoneNumber;
        this.mtType = mtType;
        this.adFlag = adFlag;
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

        this.status = this.status== null ? "9" : this.status;
        this.priority = this.priority== null ? "9" : this.priority;
        this.reservedDate = this.reservedDate== null ? "reservedDate" : this.reservedDate;
        this.phoneNumber = this.phoneNumber== null ? "phonenumber" : this.phoneNumber;
        this.mtType = this.mtType== null ? "mt" : this.mtType;
        this.msg = this.msg== null ? "msg" : this.msg;

    }

}
