package com.example.main.domain.Entity;

import com.sun.istack.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import com.example.main.domain.entity.UserSendInfo;
import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

@DynamicInsert // insert 시 null인 필드 제외
@DynamicUpdate // update 시
@Getter
@NoArgsConstructor
@Table(name = "imc_user", schema = "imc-intern")
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
    private String authority;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL,
            fetch = FetchType.LAZY, optional = false)
    private UserSendInfo userSendInfo;

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
        this.authority = this.authority == null ? "ROLE_MEMBER" : this.authority;
    }
}