package com.humuson.domain.entity;

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

    private String templateCode;
    private String templateName;
    private String templateContent;
    private String messageType;
    private String buttonUrl;
    private String attachmentJson;
//
//    // parent에 식별자 클래스 타입의 필드 생성
//    @EmbeddedId// EmbeddedId를 사용해서 비식별 관계 매핑
//    protected TemplateInfoPK templateInfoPK;

    @Builder
    public TemplateInfo( String templateCode, String templateName, String templateContent,
                         String messageType, String buttonUrl, String attachmentJson) {
        this.templateCode = templateCode;
        this.templateName = templateName;
        this.templateContent = templateContent;
        this.messageType = messageType;
        this.buttonUrl = buttonUrl;
        this.attachmentJson = attachmentJson;
    }
}
