package com.example.localstack.localstackdemo.mapper;

import com.example.localstack.localstackdemo.dto.ActionDto;
import com.example.localstack.localstackdemo.entity.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public User toEntity(ActionDto dto) {
        User user = new User();

        user.setExternalId(String.valueOf(dto.getId()));
        user.setName("User-" + dto.getId());

        user.setDescription("Получено из очереди");
        user.setStatus("Done");
        user.setAction(dto.getAction());

        user.setPayloadJson("{type:user,id:" + dto.getId() + ",action:" + dto.getAction() + "}");
        return user;
    }
}
