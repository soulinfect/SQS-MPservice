package com.example.localstack.localstackdemo.mapper;

import com.example.localstack.localstackdemo.dto.ActionDto;
import com.example.localstack.localstackdemo.entity.Group;
import org.springframework.stereotype.Component;

@Component
public class GroupMapper {

    public Group toEntity(ActionDto dto) {
        Group group = new Group();

        group.setExternalId(String.valueOf(dto.getId()));
        group.setName("Group-" + dto.getId());

        group.setDescription("Получено из очереди");
        group.setStatus("Done");
        group.setAction(dto.getAction());

        group.setPayloadJson("{type:group,id:" + dto.getId() + ",action:" + dto.getAction() + "}");
        return group;
    }
}
