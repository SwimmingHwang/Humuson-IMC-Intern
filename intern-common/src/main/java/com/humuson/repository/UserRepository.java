package com.humuson.repository;

import com.humuson.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    @Query("SELECT p FROM User p ORDER BY p.id DESC")
    Optional<User> findByEmail(String userEmail);
}