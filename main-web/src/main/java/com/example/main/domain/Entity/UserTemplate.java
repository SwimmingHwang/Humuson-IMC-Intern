package com.example.main.domain.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;

@DynamicInsert // insert 시 null인 필드 제외
@DynamicUpdate // update 시
@Getter
@NoArgsConstructor
@Table(name = "imc_user_template")
@Entity //JPA의 어노테이션
public class UserTemplate {

    @Id
    private String id;

    @ManyToOne
    @JoinColumns({
            @JoinColumn(name="template_info_id", referencedColumnName = "id")
    })
    private TemplateInfo templateInfo;

    @ManyToOne
    @JoinColumns({
            @JoinColumn(name="user_id", referencedColumnName = "id")
    })
    private com.example.main.domain.entity.UserEntity userEntity;

//
}
