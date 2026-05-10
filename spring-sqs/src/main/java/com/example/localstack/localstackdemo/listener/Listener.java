package com.example.localstack.localstackdemo.listener;

import com.example.localstack.localstackdemo.dto.ActionDto;
import com.example.localstack.localstackdemo.exception.DuplicateMessageException;
import com.example.localstack.localstackdemo.exception.NotFoundException;
import com.example.localstack.localstackdemo.model.Action;
import com.example.localstack.localstackdemo.service.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.awspring.cloud.sqs.annotation.SqsListener;
import io.awspring.cloud.sqs.operations.SqsTemplate;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

@Slf4j
@Component
@RequiredArgsConstructor
public class Listener {

    private final ObjectMapper objectMapper;
    private final SpaceService spaceService;
    private final FormService formService;
    private final GroupService groupService;
    private final SiteService siteService;
    private final UserService userService;

    @SqsListener("${cloud.aws.sqs.queue-name}")
    public void listen(String message) throws Exception {
        ActionDto action = objectMapper.readValue(message, ActionDto.class);
        log.info("Received action for type: {}, id: {}, action: {}",
                action.getType(), action.getId(), action.getAction());
        try {
            switch (action.getType().toLowerCase()) {
                case "space" -> spaceService.processAction(action);
                case "form" -> formService.processAction(action);
                case "group" -> groupService.processAction(action);
                case "site" -> siteService.processAction(action, message);
                case "user" -> userService.processAction(action);
                default -> log.warn("Неподдерживаемый тип сообщения: {}", action.getType());
            }
        } catch (DuplicateMessageException e) {
            log.warn(e.getMessage());
        } catch (NotFoundException e) {
            log.warn(e.getMessage());
        } catch (Exception e) {
            log.error("Неподдерживаемая ошибка: ", e);
        }
    }
}
