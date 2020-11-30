package com.humuson.domain.repository;

import com.humuson.domain.entity.Customer;
import com.humuson.domain.entity.CustomerGroup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CustomerGroupRepository extends JpaRepository<CustomerGroup, Long> {

    List<CustomerGroup> findByGroup_Id(long groupId);

    @Query(value = "UPDATE imc_customer_group SET group_id=?2 WHERE group_id = ?1", nativeQuery = true)
    void updateGroupId(long beforeId, long afterId);


}
