package com.example.localstack.localstackdemo.mapper;

import com.example.localstack.localstackdemo.dto.ActionDto;
import com.example.localstack.localstackdemo.entity.Site;
import org.springframework.stereotype.Component;

@Component
public class SiteMapper {

    public Site toEntity(ActionDto dto) {
        Site site = new Site();

        site.setExternalId(String.valueOf(dto.getId()));
        site.setName("Site-" + dto.getId());

        site.setDescription("Получено из очереди");
        site.setStatus("Done");
        site.setAction(dto.getAction());

        site.setPayloadJson("{type:site,id:" + dto.getId() + ",action:" + dto.getAction() + "}");
        return site;
    }
}
