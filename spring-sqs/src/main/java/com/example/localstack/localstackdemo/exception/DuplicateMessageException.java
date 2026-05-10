package com.example.localstack.localstackdemo.exception;

public class DuplicateMessageException extends RuntimeException {
    public DuplicateMessageException(String type, Long externalId) {
        super("Повторение сообщения типа "  + type + " с externalId: " + externalId);
    }
}
