package com.example.localstack.localstackdemo.repository;

import com.example.localstack.localstackdemo.entity.Form;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface FormRepository extends JpaRepository<Form, String> {
    Optional<Form> findByExternalId(String externalId);
}
