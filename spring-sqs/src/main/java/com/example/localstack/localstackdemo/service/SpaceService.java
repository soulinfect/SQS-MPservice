package com.example.localstack.localstackdemo.service;

import com.example.localstack.localstackdemo.dto.ActionDto;
import com.example.localstack.localstackdemo.entity.Space;
import com.example.localstack.localstackdemo.exception.DuplicateMessageException;
import com.example.localstack.localstackdemo.exception.NotFoundException;
import com.example.localstack.localstackdemo.mapper.SpaceMapper;
import com.example.localstack.localstackdemo.repository.SpaceRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class SpaceService {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private final SpaceMapper spaceMapper;
    private final SpaceRepository spaceRepository;

    public void processAction(ActionDto dto) {
        String externalId = String.valueOf(dto.getId());

        switch (dto.getAction().toLowerCase()) {
            case "create" -> {
                if (spaceRepository.findByExternalId(externalId).isPresent()) {
                    throw new DuplicateMessageException(dto.getType(), dto.getId());
                }

                Space space = spaceMapper.toEntity(dto);
                spaceRepository.save(space);
                logger.info("Создана запись {}: {}", dto.getType(), externalId);
            }
            case "update" -> {
                Space space = spaceRepository.findByExternalId(externalId)
                        .orElseThrow(() -> new NotFoundException(dto.getType(), dto.getId(), dto.getAction()));
                space.setUpdatedAt(LocalDateTime.now());
                space.setAction("update");
                spaceRepository.save(space);
                logger.info("Обновлена запись {}: {}", dto.getType(), externalId);

            }
            case "delete" -> {
                Space space = spaceRepository.findByExternalId(externalId)
                        .orElseThrow(() -> new NotFoundException(dto.getType(), dto.getId(), dto.getAction()));
                space.setAction("delete");
                space.setDeleted(true);
                spaceRepository.save(space);
                logger.info("Помечено под удаление {}: {}", dto.getType(), externalId);

            }
            default -> logger.warn("Неизвестная операция: {} для: {}", dto.getAction(), dto.getType());
        }
    }
}
