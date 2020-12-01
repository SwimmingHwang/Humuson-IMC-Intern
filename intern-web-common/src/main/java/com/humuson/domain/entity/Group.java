package com.humuson.domain.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.util.*;

@DynamicInsert
@DynamicUpdate
@Getter
@NoArgsConstructor
@Table(name = "imc_group")
@Entity
public class Group {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String groupName;

    @ManyToMany(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    @JoinTable(
            name = "imc_customer_group",
            joinColumns = @JoinColumn(name = "group_id"),
            inverseJoinColumns = @JoinColumn(name = "customer_id")
    )
    private Set<Customer> customers = new HashSet<>();


    public Group(String groupName) {
        this.groupName = groupName;
    }
    public void updateGroupName(String groupName){
        this.groupName = groupName;
    }

    @Override
    public String toString() {
        return this.groupName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Group group = (Group) o;
        return id == group.id &&
                Objects.equals(groupName, group.groupName) &&
                Objects.equals(customers, group.customers);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, groupName, customers);
    }
}
