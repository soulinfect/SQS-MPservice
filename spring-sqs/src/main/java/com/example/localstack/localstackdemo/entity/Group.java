package com.example.localstack.localstackdemo.entity;

import jakarta.persistence.*;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@Table(name = "groups")
public class Group extends BaseEntity {
    @OneToMany(mappedBy = "group")
    private List<User> users = new ArrayList<>();
}
