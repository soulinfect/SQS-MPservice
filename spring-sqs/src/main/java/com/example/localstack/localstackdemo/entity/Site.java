package com.example.localstack.localstackdemo.entity;

import jakarta.persistence.*;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@Table(name = "sites")
public class Site extends BaseEntity {
    @Column(name = "s3_object_key")
    private String s3ObjectKey;
}
