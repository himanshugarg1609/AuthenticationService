package com.him.authservice.repositories;


import com.him.authservice.models.Session;
import org.springframework.data.jpa.repository.JpaRepository;


public interface SessionRepository extends JpaRepository<Session, Integer> {
    boolean existsByToken(String token);
}
