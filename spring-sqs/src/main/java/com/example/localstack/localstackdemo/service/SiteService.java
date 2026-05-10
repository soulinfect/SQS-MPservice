package com.example.localstack.localstackdemo.service;

import com.example.localstack.localstackdemo.dto.ActionDto;
import com.example.localstack.localstackdemo.entity.Site;
import com.example.localstack.localstackdemo.exception.DuplicateMessageException;
import com.example.localstack.localstackdemo.exception.NotFoundException;
import com.example.localstack.localstackdemo.mapper.SiteMapper;
import com.example.localstack.localstackdemo.repository.SiteRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class SiteService {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private final SiteMapper siteMapper;
    private final SiteRepository siteRepository;
    private final S3Service s3Service;

    public void processAction(ActionDto dto, String rawMessage) {
        String externalId = String.valueOf(dto.getId());

        switch (dto.getAction().toLowerCase()) {
            case "create" -> {
                if (siteRepository.findByExternalId(externalId).isPresent()) {
                    throw new DuplicateMessageException(dto.getType(), dto.getId());
                }

                Site site = siteMapper.toEntity(dto);
                siteRepository.save(site);

                String fileName = String.format("%s-%d-%s.txt",
                        dto.getType(), dto.getId(), dto.getAction());
                s3Service.uploadTextFile(fileName, rawMessage);

                logger.info("Создана запись {}: {}", dto.getType(), externalId);
            }
            case "update" -> {
                Site site = siteRepository.findByExternalId(externalId)
                        .orElseThrow(() -> new NotFoundException(dto.getType(), dto.getId(), dto.getAction()));
                site.setUpdatedAt(LocalDateTime.now());
                site.setAction("update");
                siteRepository.save(site);
                logger.info("Обновлена запись {}: {}", dto.getType(), externalId);

            }
            case "delete" -> {
                Site site = siteRepository.findByExternalId(externalId)
                        .orElseThrow(() -> new NotFoundException(dto.getType(), dto.getId(), dto.getAction()));
                site.setAction("delete");
                site.setDeleted(true);
                siteRepository.save(site);
                logger.info("Помечено под удаление {}: {}", dto.getType(), externalId);

            }
            default -> logger.warn("Неизвестная операция: {} для: {}", dto.getAction(), dto.getType());
        }
    }
}
