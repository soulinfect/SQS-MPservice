package com.example.localstack.localstackdemo.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ActionDto {
    private String type;
    private Long id;
    private String action;
}
