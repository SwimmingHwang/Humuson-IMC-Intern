package com.humuson.domain.repository;

import com.humuson.domain.msgs.AtMsgs;
import com.humuson.domain.msgs.MtMsgs;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MtMsgsRepository extends JpaRepository<MtMsgs, Integer> {

    @Query("SELECT p FROM MtMsgs p ORDER BY p.id DESC")
    List<MtMsgs> findAllDesc();

    @Query("SELECT p FROM MtMsgs p ORDER BY p.reservedDate DESC")
    List<MtMsgs> findAllReservedDateDesc();

    @Query(value ="SELECT * FROM imc_mt WHERE STR_TO_DATE(reserved_date,'%Y%m%d%H%i%s') <= now() and status='1'", nativeQuery=true)
    List<MtMsgs> findAllByReservedDate();

    @Query(value ="SELECT id FROM imc_mt WHERE STR_TO_DATE(reserved_date,'%Y%m%d%H%i%s') <= now() and status='1'", nativeQuery=true)
    List<Integer> findAllIdByReservedDate();

    @Modifying
    @Query(value="UPDATE imc_mt SET status  = '2' WHERE id  in ?1", nativeQuery = true)
    void updateStatusList(List<Integer> idList);

    @Modifying
    @Query(value = "UPDATE imc_mt SET etc2=concat(etc2,id) WHERE etc2='http://localhost:8080/api/v1/mt-report/' and id >=0", nativeQuery = true)
    void updateEtc2();

}
