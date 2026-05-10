package com.example.localstack.localstackdemo.exception;

public class NotFoundException extends RuntimeException {
    public NotFoundException(String type, Long externalId, String action) {
        super(type + " " + externalId + "не найден для операции " + action);
    }
}
