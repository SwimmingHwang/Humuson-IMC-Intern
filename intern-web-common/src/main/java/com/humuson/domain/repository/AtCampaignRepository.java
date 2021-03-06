package com.humuson.domain.repository;

import com.humuson.domain.entity.AtCampaign;
import com.humuson.domain.msgs.AtMsgs;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AtCampaignRepository extends JpaRepository<AtCampaign, Long> {

    @Query("SELECT p FROM AtCampaign p ORDER BY p.reservedDate DESC")
    List<AtCampaign> findAllReservedDateDesc();

}
