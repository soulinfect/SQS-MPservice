package com.example.localstack.localstackdemo.repository;

import com.example.localstack.localstackdemo.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, String> {
    Optional<User> findByExternalId(String externalId);
}
