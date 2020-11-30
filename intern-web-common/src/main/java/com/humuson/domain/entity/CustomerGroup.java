package com.humuson.domain.entity;


import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.util.Optional;

@DynamicInsert
@DynamicUpdate
@Getter
@NoArgsConstructor
@Table(name = "imc_customer_group")
@Entity
public class CustomerGroup {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;

    @ManyToOne
    @JoinColumn(name = "group_id")
    private Group group;

    @ManyToOne
    @JoinColumn(name="customer_id")
    private Customer customer;

    public CustomerGroup( Group group, Customer customer) {
        this.group = group;
        this.customer = customer;
    }
}
