/*
 *  테이블과 연결되어있어 엔티티 글래스를 기준으로 테이블이 생성되고 스키마가 변경되기 때문에 DTO필요
 * */
package com.humuson.domain.msgs;

import com.humuson.domain.entity.AtCampaign;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.text.SimpleDateFormat;
import java.util.Date;

@DynamicInsert // insert 시 null인 필드 제외
@DynamicUpdate // update 시
@Getter
@Setter
@NoArgsConstructor
@Table(name = "imc_at")
@Entity //JPA의 어노테이션
public class AtMsgs {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(nullable = false, length = 1)
    private String status;
    @Column(nullable = false, length = 1)
    private String priority;
    @Column(nullable = false, length = 19)
    private String reservedDate;
    @Column(nullable = false, length = 40)
    private String senderKey;
    @Column(nullable = false, length = 16)
    private String phoneNumber;
    @Column(nullable = false, length = 30)
    private String templateCode;
    @Column(name = "MESSAGE", nullable = false, length = 3000)
    private String msg; // 메시지 내용
    @Column(length = 64)
    private String etc1; // Agent DB에 전달되었는지 확인하는 status
    @Column(length = 64)
    private String etc2; // 결과 받는 URL

    @ManyToOne
    @JoinColumn(name="camp_id", referencedColumnName = "id", insertable=false, updatable=false)
    private AtCampaign atCampaign;

    @Builder // 해당 클래스의 빌더 패턴 클래스를 생성
    public AtMsgs(String status, String priority, String reservedDate, String senderKey,
                  String phoneNumber, String templateCode, String msg, String etc1, String etc2, AtCampaign atCampaign){
        this.status = this.status== null ? "1" : status;
        this.priority = this.priority== null ? "N" : priority; // S-Slow, N-Normal, F-Fast
        this.reservedDate = reservedDate; // yyyyMMddhhmmss (hh:24h)
        this.senderKey = this.senderKey== null ? "54ef196697bda7dbc36a45a334beb83580d8ca2a" :senderKey;
        this.phoneNumber = phoneNumber; //821012345678
        this.templateCode = templateCode;
        this.msg = msg;
        this.etc1 = this.etc1 == null? "0" : etc1;
        this.etc2 = this.etc2 == null ? "http://localhost:8080/api/v1/at-report/" : etc2;
        this.atCampaign = atCampaign;
    }
    // 예약메시지 수정
    public void update(String reservedDate, String msg, String phoneNumber, String templateCode){
        this.reservedDate = reservedDate;
        this.msg = msg;
        this.phoneNumber = phoneNumber;
        this.templateCode = templateCode;
    }
    // 예약시간 되었을 때 상태 업데이트
    public void updateStatus(String status){
        this.status=status;
    }
    public void updateStatus(String status, boolean isReport){
        this.status=status;
        if (isReport)
            this.etc2 = etc2+this.getId().toString();
    }
    public void updateEtc2(String status){
        this.etc2 = etc2;
    }
    /**
     * insert 되기전 (persist 되기전) 실행된다.
     * */
    @PrePersist
    public void prePersist() {
        this.status = this.status== null ? "1" : this.status;
        this.priority = this.priority== null ? "N" : this.priority; // S-Slow, N-Normal, F-Fast
        this.reservedDate = this.reservedDate== null ? "null" : this.reservedDate; // yyyyMMddhhmmss (hh:24h)
        this.senderKey = this.senderKey== null ? "54ef196697bda7dbc36a45a334beb83580d8ca2a" : this.senderKey;
        this.phoneNumber = this.phoneNumber== null ? "phoneNumber" : this.phoneNumber; //821012345678
        this.templateCode = this.templateCode== null ? "null" : this.templateCode;
        this.msg = this.msg== null ? "NULL MESSAGE" : this.msg;
        this.etc1 = this.etc1 == null? "0" : this.etc1;
        this.etc2 = this.etc2 == null ? "http://localhost:8080/api/v1/at-report/" : this.etc2;
    }
    public static String getCurrentTimeStamp() {
        SimpleDateFormat sdfDate = new SimpleDateFormat("yyyyMMddHHmmss");//dd/MM/yyyy
        Date now = new Date();
        String strDate = sdfDate.format(now);
        return strDate;
    }
}
