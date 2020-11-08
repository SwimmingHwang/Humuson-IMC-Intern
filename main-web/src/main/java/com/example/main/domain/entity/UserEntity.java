package com.example.main.domain.entity;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
@Table(name = "IMC_USER", schema = "imc-intern")
public class UserEntity {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @Column(length = 20, nullable = false)
    private String email;

    @Column(length = 100, nullable = false)
    private String password;

    @Builder
    public UserEntity(Long id, String email, String password) {
        this.id = id;
        this.email = email;
        this.password = password;
    }
}