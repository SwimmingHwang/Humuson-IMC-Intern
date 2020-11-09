package com.example.main.domain.msgs;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface FtMsgsRepository extends JpaRepository<FtMsgs, Integer> {

    @Query("SELECT p FROM FtMsgs p ORDER BY p.id DESC")
    List<FtMsgs> findAllDesc();
}
