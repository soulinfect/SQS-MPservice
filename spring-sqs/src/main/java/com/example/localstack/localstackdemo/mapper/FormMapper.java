package com.example.localstack.localstackdemo.mapper;

import com.example.localstack.localstackdemo.dto.ActionDto;
import com.example.localstack.localstackdemo.entity.Form;
import org.springframework.stereotype.Component;

@Component
public class FormMapper {

    public Form toEntity(ActionDto dto) {
        Form form = new Form();

        form.setExternalId(String.valueOf(dto.getId()));
        form.setName("Form-" + dto.getId());

        form.setDescription("Получено из очереди");
        form.setStatus("Done");
        form.setAction(dto.getAction());

        form.setPayloadJson("{type:form,id:" + dto.getId() + ",action:" + dto.getAction() + "}");
        return form;
    }
}
