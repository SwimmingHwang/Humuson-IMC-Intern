package com.humuson.domain.repository;

import com.humuson.domain.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

    @Query("SELECT p FROM Customer p ORDER BY p.id DESC")
    List<Customer> findAllDesc();

}

