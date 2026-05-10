package com.example.localstack.localstackdemo.repository;

import com.example.localstack.localstackdemo.entity.Site;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SiteRepository extends JpaRepository<Site, String> {
    Optional<Site> findByExternalId(String externalId);
}
