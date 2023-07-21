package com.ohgiraffers.adregamdi.place.command.infra.service;

import com.ohgiraffers.adregamdi.category.command.domain.aggregate.entity.Category;
import com.ohgiraffers.adregamdi.cityanddong.command.domain.aggregate.entity.City;
import com.ohgiraffers.adregamdi.cityanddong.command.domain.aggregate.entity.Dong;
import com.ohgiraffers.adregamdi.place.command.domain.aggregate.entity.*;
import com.ohgiraffers.adregamdi.place.command.domain.service.PlaceAPIService;
import com.ohgiraffers.adregamdi.place.query.application.service.DataQueryService;
import com.ohgiraffers.adregamdi.tag.command.domain.aggregate.entity.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class PlaceInfraAPIService implements PlaceAPIService {

    private final DataQueryService dataQueryService;

    @Autowired
    public PlaceInfraAPIService(DataQueryService dataQueryService) {
        this.dataQueryService = dataQueryService;
    }




}
