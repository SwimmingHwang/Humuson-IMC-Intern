package com.humuson.domain.Entity;

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
@Table(name = "imc_template_info")
@Entity
public class TemplateInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="template_code")
    private String templateCode;
    @Column(name="template_name")
    private String templateName;
    @Column(name="message_type")
    private String messageType;
    @Column(name="button_url")
    private String buttonUrl;
    @Column(name="attachment_json")
    private String attachmentJson;
//
//    // parent에 식별자 클래스 타입의 필드 생성
//    @EmbeddedId// EmbeddedId를 사용해서 비식별 관계 매핑
//    protected TemplateInfoPK templateInfoPK;

    @Builder
    public TemplateInfo( String templateCode, String templateName, String messageType, String buttonUrl, String attachmentJson) {
        this.templateCode = templateCode;
        this.templateName = templateName;
        this.messageType = messageType;
        this.buttonUrl = buttonUrl;
        this.attachmentJson = attachmentJson;
    }
}
