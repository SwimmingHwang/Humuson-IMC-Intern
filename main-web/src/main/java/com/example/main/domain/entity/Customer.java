package com.example.main.domain.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@DynamicInsert // insert 시 null인 필드 제외
@DynamicUpdate // update 시
@Getter
@NoArgsConstructor
@Table(name = "imc_customer", schema = "imc-intern")
@Entity //JPA의 어노테이션
public class Customer {
    @Id
    @GeneratedValue
    private long id;
    private String phoneNumber; // 플러스친구를 개설한 관리자 핸드폰 번호를 입력하세요

    public void update(String phoneNumber){
        this.phoneNumber = phoneNumber;
    }

    @Builder
    public Customer(long id, String phoneNumber) {
        this.id = id;
        this.phoneNumber = phoneNumber;
    }
}
