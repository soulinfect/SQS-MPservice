package com.example.localstack.localstackdemo.entity;

import jakarta.persistence.*;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Entity
@Table(name = "spaces")
public class Space extends BaseEntity {

    @OneToMany(mappedBy = "space", cascade = CascadeType.ALL)
    private List<Form> forms = new ArrayList<>();
}
