package com.humuson.domain.repository;

import com.humuson.domain.msgs.AtMsgs;
import com.humuson.domain.msgs.MtMsgs;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MtMsgsRepository extends JpaRepository<MtMsgs, Integer> {

    @Query("SELECT p FROM MtMsgs p ORDER BY p.id DESC")
    List<MtMsgs> findAllDesc();

    @Query("SELECT p FROM MtMsgs p ORDER BY p.reservedDate DESC")
    List<MtMsgs> findAllReservedDateDesc();

    @Query(value ="SELECT * FROM imc_mt WHERE STR_TO_DATE(reserved_date,'%Y%m%d%H%i%s') <= now() and status='1'", nativeQuery=true)
    List<MtMsgs> findAllByReservedDate();
}
