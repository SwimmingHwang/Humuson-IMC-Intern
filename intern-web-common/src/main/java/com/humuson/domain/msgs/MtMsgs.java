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
@Table(name = "imc_mt")
@Entity //JPA의 어노테이션
public class MtMsgs {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String status;
    private String priority;
    private String reservedDate;
    private String phoneNumber;
    private String callback;
    private String mtType;
    private String adFlag; //CHAR(1)
    @Column(name = "MESSAGE")
    private String msg; // 메시지 내용
    private String etc1;
    private String etc2;


    @Builder // 해당 클래스의 빌더 패턴 클래스를 생성
    public MtMsgs(String status, String priority, String reservedDate, String callback,
                  String phoneNumber, String mtType, String adFlag, String msg, String etc1, String etc2){
        this.status = status== null ? "1" : status;
        this.priority = priority== null ? "N" : priority; // S-Slow, N-Normal, F-Fast
        this.reservedDate = reservedDate;
        this.callback = callback;
        this.phoneNumber = phoneNumber;
        this.mtType = mtType;
        this.adFlag = adFlag == null? "N" : adFlag;
        this.msg = msg;
        this.etc1 = etc1 == null? "0" : etc1;
        this.etc2 = etc2 == null ? "http://localhost:8080/api/v1/at-report" : etc2;
    }

    public void update(String reservedDate, String mtType, String callback, String msg , String phoneNumber){
        this.reservedDate = reservedDate;
        this.mtType = mtType;
        this.callback = callback;
        this.msg = msg;
        this.phoneNumber = phoneNumber;
}
    public void updateStatus(String status){
        this.status=status;
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
        this.callback = this.callback == null ? "010callback" : this.callback;
        this.mtType = this.mtType== null ? "MM" : this.mtType; // MT 상품 타입 (SM-SMS, LM-LMS)
        this.msg = this.msg== null ? "NULL MESSAGE" : this.msg;
        this.adFlag = this.adFlag == null ? "N" : this.adFlag;
    }

}
