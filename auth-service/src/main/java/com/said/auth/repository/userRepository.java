package com.said.auth.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.said.auth.models.users;

@Repository
public interface userRepository extends JpaRepository<users, Long>{
    users findByEmail(String email);
}
