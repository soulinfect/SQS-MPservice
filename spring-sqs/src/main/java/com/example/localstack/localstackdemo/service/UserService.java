package com.example.localstack.localstackdemo.service;

import com.example.localstack.localstackdemo.dto.ActionDto;
import com.example.localstack.localstackdemo.entity.User;
import com.example.localstack.localstackdemo.exception.DuplicateMessageException;
import com.example.localstack.localstackdemo.exception.NotFoundException;
import com.example.localstack.localstackdemo.mapper.UserMapper;
import com.example.localstack.localstackdemo.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class UserService {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private final UserMapper userMapper;
    private final UserRepository userRepository;

    public void processAction(ActionDto dto) {
        String externalId = String.valueOf(dto.getId());

        switch (dto.getAction().toLowerCase()) {
            case "create" -> {
                if (userRepository.findByExternalId(externalId).isPresent()) {
                    throw new DuplicateMessageException(dto.getType(), dto.getId());
                }

                User user = userMapper.toEntity(dto);
                userRepository.save(user);
                logger.info("Создана запись {}: {}", dto.getType(), externalId);
            }
            case "update" -> {
                User user = userRepository.findByExternalId(externalId)
                        .orElseThrow(() -> new NotFoundException(dto.getType(), dto.getId(), dto.getAction()));
                user.setUpdatedAt(LocalDateTime.now());
                user.setAction("update");
                userRepository.save(user);
                logger.info("Обновлена запись {}: {}", dto.getType(), externalId);

            }
            case "delete" -> {
                User user = userRepository.findByExternalId(externalId)
                        .orElseThrow(() -> new NotFoundException(dto.getType(), dto.getId(), dto.getAction()));
                user.setAction("delete");
                user.setDeleted(true);
                userRepository.save(user);
                logger.info("Помечено под удаление {}: {}", dto.getType(), externalId);

            }
            default -> logger.warn("Неизвестная операция: {} для: {}", dto.getAction(), dto.getType());
        }
    }
}