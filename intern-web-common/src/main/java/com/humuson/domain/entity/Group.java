package com.humuson.domain.entity;

import com.humuson.service.GroupService;
import lombok.*;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.util.*;

@DynamicInsert
@DynamicUpdate
@Setter
@Getter
@NoArgsConstructor
@Table(name = "imc_group")
@Entity
public class Group {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String groupName;

    @ManyToMany(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    @JoinTable(
            name = "imc_customer_group",
            joinColumns = @JoinColumn(name = "group_id"),
            inverseJoinColumns = @JoinColumn(name = "customer_id")
    )
    private Set<Customer> customers = new HashSet<>();


    public Group(String groupName) {
        this.groupName = groupName;
    }

    public void update(String groupName, Set<Customer> customers){
        this.groupName = groupName;

        Set<Customer> setCha = new HashSet<>();
        // 차집합
        setCha.addAll(this.getCustomers());
        setCha.removeAll(customers);

        this.getCustomers().removeAll(setCha);
        this.getCustomers().addAll(customers);
    }
}
