/*
 *  테이블과 연결되어있어 엔티티 글래스를 기준으로 테이블이 생성되고 스키마가 변경되기 때문에 DTO필요
 * */
package com.humuson.agent.domain.entity;

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
    @Column(nullable = false, length = 1)
    private String status;
    @Column(nullable = false, length = 1)
    private String priority;
    @Column(nullable = false, length = 19)
    private String reservedDate;
    @Column(nullable = false, length = 16)
    private String phoneNumber;
    @Column(length = 16)
    private String callback;
    @Column(length = 100)
    private String title;
    @Column(nullable = false, length = 2)
    private String mtType;
    @Column(nullable = false, length = 1)
    private String adFlag; //CHAR(1)
    @Column(name = "MESSAGE", nullable = false, length = 3000)
    private String msg; // 메시지 내용
    @Column(length = 64)
    private String etc1;
    @Column(length = 64)
    private String etc2;

    @Builder // 해당 클래스의 빌더 패턴 클래스를 생성
    public MtMsgs(String status, String priority, String reservedDate, String callback, String title,
                  String phoneNumber, String mtType, String adFlag, String msg, String etc1, String etc2){
        this.status = status;
        this.priority = priority;
        this.reservedDate = reservedDate;
        this.callback = callback;
        this.title = title;
        this.phoneNumber = phoneNumber;
        this.mtType = mtType;
        this.adFlag = adFlag;
        this.msg = msg;
        this.etc1 = etc1;
        this.etc2 = etc2;
    }

    public void update(String msg){
        this.msg = msg;
    }
    /**
     * insert 되기전 (persist 되기전) 실행된다.
     * */
    @PrePersist
    public void prePersist() {

        this.status = this.status== null ? "1" : this.status;
        this.priority = this.priority== null ? "N" : this.priority;
        this.reservedDate = this.reservedDate== null ? "reservedDate" : this.reservedDate;
        this.phoneNumber = this.phoneNumber== null ? "phonenumber" : this.phoneNumber;
        this.callback = this.callback == null ? "01065362547" : this.callback;
        this.title = this.title == null ? "title" : this.title;
        this.mtType = this.mtType== null ? "SM" : this.mtType; // MT 상품 타입 (SM-SMS, LM-LMS)
        this.msg = this.msg== null ? "msg" : this.msg;
        this.adFlag = this.adFlag == null ? "N" : this.adFlag;

    }

}
