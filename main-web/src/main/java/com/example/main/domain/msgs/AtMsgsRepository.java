package com.example.main.domain.msgs;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AtMsgsRepository extends JpaRepository<AtMsgs, Integer> {

    @Query("SELECT p FROM AtMsgs p ORDER BY p.id DESC")
    List<AtMsgs> findAllDesc();

}
