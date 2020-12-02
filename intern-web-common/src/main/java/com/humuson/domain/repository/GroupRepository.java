package com.humuson.domain.repository;

import com.humuson.domain.entity.Customer;
import com.humuson.domain.entity.Group;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface GroupRepository extends JpaRepository<Group, Long> {
    @Query("SELECT p FROM Group p ORDER BY p.id DESC")
    List<Group> findAllDesc();


}
