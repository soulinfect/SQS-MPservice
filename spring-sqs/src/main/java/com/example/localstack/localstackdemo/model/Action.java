package com.example.localstack.localstackdemo.model;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Action {

    private String type;
    private Long id;
    private String action;
}