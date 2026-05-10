package com.example.localstack.localstackdemo.repository;

import com.example.localstack.localstackdemo.entity.Space;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SpaceRepository extends JpaRepository<Space, String> {
    Optional<Space> findByExternalId(String externalId);
}
