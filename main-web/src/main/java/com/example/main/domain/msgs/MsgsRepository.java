package com.example.main.domain.msgs;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MsgsRepository extends JpaRepository<Msgs, Integer> {

    @Query("SELECT p FROM Msgs p ORDER BY p.id DESC")
    List<Msgs> findAllDesc();
}
