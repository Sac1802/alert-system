package com.said.auth.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.said.auth.models.users;

@Repository
public interface userRepository extends JpaRepository<users, Long>{
    Optional<users> findByEmail(String email);
    boolean existsByName(String name);
    boolean existsByEmail(String email);
}
