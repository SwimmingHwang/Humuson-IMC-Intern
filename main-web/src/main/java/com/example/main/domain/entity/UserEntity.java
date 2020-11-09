package com.example.main.domain.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

@DynamicInsert
@DynamicUpdate
@NoArgsConstructor
@Getter
@Entity
@Table(name = "imc_user", schema = "imc-intern")
public class UserEntity {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "이름을 입력하세요.")
    private String username;

    @NotEmpty(message = "이메일을 입력하세요.")
    private String email;

    @NotEmpty(message = "비밀번호를 입력하세요.")
    private String password;

    private String authority;

    @Builder
    public UserEntity(Long id, String username, String email, String password, String authority) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.password = password;
        this.authority = authority;
    }

    @PrePersist
    public void prePersist() {
        this.authority = this.authority== null ? "MEMBER" : this.authority;
    }
}