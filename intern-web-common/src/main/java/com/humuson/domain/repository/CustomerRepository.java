package com.humuson.domain.repository;

import com.humuson.domain.entity.Customer;
import com.humuson.domain.entity.Group;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Set;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

    @Query("SELECT p FROM Customer p ORDER BY p.id DESC")
    List<Customer> findAllDesc();


}

