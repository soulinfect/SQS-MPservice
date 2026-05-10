package com.example.localstack.localstackdemo.mapper;

import com.example.localstack.localstackdemo.dto.ActionDto;
import com.example.localstack.localstackdemo.entity.Space;
import org.springframework.stereotype.Component;

@Component
public class SpaceMapper {

    public Space toEntity(ActionDto dto) {
        Space space = new Space();

        space.setExternalId(String.valueOf(dto.getId()));
        space.setName("Space-" + dto.getId());

        space.setDescription("Получено из очереди");
        space.setStatus("Done");
        space.setAction(dto.getAction());

        space.setPayloadJson("{type:space,id:" + dto.getId() + ",action:" + dto.getAction() + "}");
        return space;
    }
}
