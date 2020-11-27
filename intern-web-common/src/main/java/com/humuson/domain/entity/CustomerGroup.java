package com.humuson.domain.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;

@DynamicInsert
@DynamicUpdate
@Getter
@NoArgsConstructor
@Table(name = "imc_customer_group")
@Entity
public class CustomerGroup {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String groupName;

    @Builder
    public CustomerGroup(long id, String groupName) {
        this.id = id;
        this.groupName = groupName;
    }
}
