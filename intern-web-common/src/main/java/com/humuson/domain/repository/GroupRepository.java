package com.humuson.domain.repository;

import com.humuson.domain.entity.Customer;
import com.humuson.domain.entity.Group;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Set;

public interface GroupRepository extends JpaRepository<Group, Long> {
    @Query("SELECT p FROM Group p ORDER BY p.id DESC")
    List<Group> findAllDesc();

    @Query("select a from Group a join fetch a.customers where a.id=?1")
    Group findAllJoinFetch(long id);

    @Query(value = "SELECT customer_id " +
            "FROM imc_group AS g " +
            "INNER JOIN imc_customer_group AS cg " +
            "WHERE g.id = ?1 and g.id = cg.group_id", nativeQuery = true)
    List<Long> findAllCustomersIds(long id);

    @Modifying
    @Query(value="DELETE FROM imc_customer_group WHERE group_id=?1 and customer_id  in ?2", nativeQuery = true)
    void deleteCustomers(long id, List<Long> idxList);
}
