package com.example.main.domain.entity;

import com.sun.istack.NotNull;
import lombok.*;
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

    @NotNull
    private String username;

    @NotNull
    private String email;

    @NotNull
    private String password;

    @NotNull
    private String authority;


    @Builder
    public User(String username, String email, String password, String authority) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.authority = authority;
    }

    @PrePersist
    public void prePersist() {
        this.authority = this.authority == null ? "ROLE_MEMBER" : this.authority;
    }


}