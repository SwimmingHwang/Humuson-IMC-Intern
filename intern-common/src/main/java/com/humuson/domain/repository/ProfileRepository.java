package com.humuson.domain.repository;

import com.humuson.domain.Entity.Profile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProfileRepository extends JpaRepository<Profile, Long>{
}
