package com.example.localstack.localstackdemo.repository;

import com.example.localstack.localstackdemo.entity.Group;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface GroupRepository extends JpaRepository<Group, String> {
    Optional<Group> findByExternalId(String externalId);
}
