package com.example.localstack.localstackdemo.service;

import com.example.localstack.localstackdemo.dto.ActionDto;
import com.example.localstack.localstackdemo.entity.Form;
import com.example.localstack.localstackdemo.exception.DuplicateMessageException;
import com.example.localstack.localstackdemo.exception.NotFoundException;
import com.example.localstack.localstackdemo.mapper.FormMapper;
import com.example.localstack.localstackdemo.repository.FormRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class FormService {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private final FormMapper formMapper;
    private final FormRepository formRepository;

    public void processAction(ActionDto dto) {
        String externalId = String.valueOf(dto.getId());

        switch (dto.getAction().toLowerCase()) {
            case "create" -> {
                if (formRepository.findByExternalId(externalId).isPresent()) {
                    throw new DuplicateMessageException(dto.getType(), dto.getId());
                }

                Form form = formMapper.toEntity(dto);
                formRepository.save(form);
                logger.info("Создана запись {}: {}", dto.getType(), externalId);
            }
            case "update" -> {
                Form form = formRepository.findByExternalId(externalId)
                        .orElseThrow(() -> new NotFoundException(dto.getType(), dto.getId(), dto.getAction()));
                form.setUpdatedAt(LocalDateTime.now());
                form.setAction("update");
                formRepository.save(form);
                logger.info("Обновлена запись {}: {}", dto.getType(), externalId);

            }
            case "delete" -> {
                Form form = formRepository.findByExternalId(externalId)
                        .orElseThrow(() -> new NotFoundException(dto.getType(), dto.getId(), dto.getAction()));
                form.setAction("delete");
                form.setDeleted(true);
                formRepository.save(form);
                logger.info("Помечено под удаление {}: {}", dto.getType(), externalId);

            }
            default -> logger.warn("Неизвестная операция: {} для: {}", dto.getAction(), dto.getType());
        }
    }
}
