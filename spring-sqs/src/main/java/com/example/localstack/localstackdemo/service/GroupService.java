package com.example.localstack.localstackdemo.service;

import com.example.localstack.localstackdemo.dto.ActionDto;
import com.example.localstack.localstackdemo.entity.Group;
import com.example.localstack.localstackdemo.exception.DuplicateMessageException;
import com.example.localstack.localstackdemo.exception.NotFoundException;
import com.example.localstack.localstackdemo.mapper.GroupMapper;
import com.example.localstack.localstackdemo.repository.GroupRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class GroupService {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private final GroupMapper groupMapper;
    private final GroupRepository groupRepository;

    public void processAction(ActionDto dto) {
        String externalId = String.valueOf(dto.getId());

        switch (dto.getAction().toLowerCase()) {
            case "create" -> {
                if (groupRepository.findByExternalId(externalId).isPresent()) {
                    throw new DuplicateMessageException(dto.getType(), dto.getId());
                }

                Group group = groupMapper.toEntity(dto);
                groupRepository.save(group);
                logger.info("Создана запись {}: {}", dto.getType(), externalId);
            }
            case "update" -> {
                Group group = groupRepository.findByExternalId(externalId)
                        .orElseThrow(() -> new NotFoundException(dto.getType(), dto.getId(), dto.getAction()));
                group.setUpdatedAt(LocalDateTime.now());
                group.setAction("update");
                groupRepository.save(group);
                logger.info("Обновлена запись {}: {}", dto.getType(), externalId);

            }
            case "delete" -> {
                Group group = groupRepository.findByExternalId(externalId)
                        .orElseThrow(() -> new NotFoundException(dto.getType(), dto.getId(), dto.getAction()));
                group.setAction("delete");
                group.setDeleted(true);
                groupRepository.save(group);
                logger.info("Помечено под удаление {}: {}", dto.getType(), externalId);

            }
            default -> logger.warn("Неизвестная операция: {} для: {}", dto.getAction(), dto.getType());
        }
    }
}