package com.example.user_service.repository;

import com.example.user_service.entity.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface UserRepository extends JpaRepository<AppUser , String> {
    Optional<AppUser> findByEmail(String email);
}