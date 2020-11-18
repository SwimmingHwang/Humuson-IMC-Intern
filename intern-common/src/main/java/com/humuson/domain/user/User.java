package com.humuson.domain.user;

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
    private String phoneNumber;

    @NotNull
    private String authority;

    @NotNull
    @Column(columnDefinition = "TINYINT", length=1)
    private Integer status; // 가입 대기 상태


    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL,
            fetch = FetchType.LAZY, optional = false)
    private UserSendInfo userSendInfo;

    @Builder
    public User(Long id, String username, String email, String password, String phoneNumber, String authority, Integer status) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.password = password;
        this.phoneNumber = phoneNumber;
        this.authority = authority;
        this.status = status;
    }

//    @PrePersist
//    public void prePersist() {
//        this.authority = this.authority == null ? "ROLE_MEMBER" : this.authority;
//        this.status = this.status == null ? '0' : this.status;
//    }

}