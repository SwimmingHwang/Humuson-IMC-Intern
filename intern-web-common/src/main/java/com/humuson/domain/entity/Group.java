package com.humuson.domain.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@ToString
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

    /* 일대다 양방향 매핑 */
    @OneToMany(mappedBy ="group" ,fetch = FetchType.EAGER, cascade =CascadeType.REMOVE, orphanRemoval = true)
    private List<CustomerGroup> customerGroups = new ArrayList<>();

    public Group(String groupName) {
        this.groupName = groupName;
    }

    @Builder
    public Group(String groupName, List<CustomerGroup> customerGroups) {
        this.groupName = groupName;
        this.customerGroups.addAll(customerGroups);
    }

    public void updateGroupName(String groupName){
        this.groupName = groupName;
    }

}
