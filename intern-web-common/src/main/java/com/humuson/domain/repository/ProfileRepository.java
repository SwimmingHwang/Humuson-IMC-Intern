package com.humuson.domain.repository;

import com.humuson.domain.entity.Profile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface ProfileRepository extends JpaRepository<Profile, Long>{

    @Query(value = "SELECT * FROM `imc-client`.imc_profile WHERE user_id = ?1", nativeQuery = true)
    Optional<Profile> findByUserId(long id);
}
