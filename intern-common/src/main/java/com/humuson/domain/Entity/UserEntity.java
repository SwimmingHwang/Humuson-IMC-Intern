package com.humuson.domain.Entity;

import com.sun.istack.NotNull;
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
@Table(name = "imc_user", schema = "imc-client")
@Entity //JPA의 어노테이션
public class UserEntity { // db layer 와 데이터 주고 받을 때 사용

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
    private String phoneNumber;

    @NotNull
    private String authority;

    @NotNull
    private Boolean status;

    @Builder
    public UserEntity(String username, String email, String password, String phoneNumber, String authority, Boolean status) {
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
        this.authority = this.authority == null ? "ROLE_MEMBER" : this.authority;
    }

}