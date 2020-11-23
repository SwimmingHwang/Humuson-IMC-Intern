package com.humuson.domain.msgs;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AtMsgsRepository extends JpaRepository<AtMsgs, Integer> {

    @Query("SELECT p FROM AtMsgs p ORDER BY p.id DESC")
    List<AtMsgs> findAllDesc();

    @Query(value ="SELECT * FROM imc_at WHERE STR_TO_DATE(reserved_date,'%Y%m%d%H%i%s') <= now() and status='1'", nativeQuery=true)
    List<AtMsgs> findAllByReservedDate();
}
