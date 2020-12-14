package com.humuson.domain.repository;

import com.humuson.domain.msgs.AtMsgs;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AtMsgsRepository extends JpaRepository<AtMsgs, Integer> {

    @Query("SELECT p FROM AtMsgs p ORDER BY p.id DESC")
    List<AtMsgs> findAllDesc();

    @Query("SELECT p FROM AtMsgs p ORDER BY p.reservedDate DESC")
    List<AtMsgs> findAllReservedDateDesc();

    @Query(value ="SELECT * FROM imc_at WHERE STR_TO_DATE(reserved_date,'%Y%m%d%H%i%s') <= now() and status='1'", nativeQuery=true)
    List<AtMsgs> findAllByReservedDate();

    @Query(value ="SELECT id FROM imc_at WHERE STR_TO_DATE(reserved_date,'%Y%m%d%H%i%s') <= now() and status='1'", nativeQuery=true)
    List<Integer> findAllIdByReservedDate();

    @Modifying
    @Query(value="UPDATE imc_at SET status  = '2' WHERE id  in ?1", nativeQuery = true)
    void updateStatusList(List<Integer> idList);

    @Query(value = "UPDATE imc_at SET etc2=concat(etc2,id) WHERE `id` in ?1", nativeQuery = true)
    void updateEtc2List(List<Integer> idList);

    @Modifying
    @Query(value = "UPDATE imc_at SET etc2=concat(etc2,id) WHERE etc2='http://localhost:8080/api/v1/at-report/' and id >=0", nativeQuery = true)
    void updateEtc2();
}
