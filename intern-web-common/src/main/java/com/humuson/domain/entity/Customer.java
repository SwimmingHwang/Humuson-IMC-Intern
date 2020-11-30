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
@Table(name = "imc_customer")
@Entity //JPA의 어노테이션
public class Customer {
    @Id
    @GeneratedValue
    private long id;
    private String userId;
    private String name;
    private String phoneNumber; // 플러스친구를 개설한 관리자 핸드폰 번호를 입력하세요
    private String var1;
    private String var2;
    private String var3;
    private String address;

    @ManyToOne
    @JoinColumns({
            @JoinColumn(name="customer_group_id", referencedColumnName = "id")
    })
    private CustomerGroup customerGroup;


    public void update(String userId, String name, String phoneNumber, String address, String var1, String var2, String var3) {
        this.userId = userId;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.var1 = var1;
        this.var2 = var2;
        this.var3 = var3;
    }
    @Builder
    public Customer(long id, String userId, String name, String phoneNumber, String address, String var1, String var2, String var3) {
        this.id = id;
        this.userId = userId;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.address = address == null? "":address;
        this.var1 = var1 == null? "" : var1; // 결과 받는 URL
        this.var2 = var2 == null? "" : var2; // 결과 받는 URL
        this.var3 = var3 == null? "" : var3; // 결과 받는 URL
    }
}

