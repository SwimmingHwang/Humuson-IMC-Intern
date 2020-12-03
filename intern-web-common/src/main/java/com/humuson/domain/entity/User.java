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
@Table(name = "imc_user")
@Entity //JPA의 어노테이션
public class User { // db layer 와 데이터 주고 받을 때 사용

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, length = 20)
    private String username;
    @Column(nullable = false, unique = true, length = 40)
    private String email;
    @Column(nullable = false, length = 70)
    private String password;
    @Column(nullable = false, length = 16)
    private String phoneNumber;
    private String authority;
    private Boolean status;

    @Builder
    public User(String username, String email, String password, String phoneNumber, String authority, Boolean status) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.phoneNumber = phoneNumber;
        this.authority = authority;
        this.status = status;
    }

    @PrePersist
    public void prePersist() {
        this.status = this.status == null? false : this.status;
    }

}