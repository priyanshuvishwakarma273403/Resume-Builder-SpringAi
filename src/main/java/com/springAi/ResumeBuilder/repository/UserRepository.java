package com.springAi.ResumeBuilder.repository;

import com.springAi.ResumeBuilder.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
}
