package com.ohgiraffers.adregamdi.place.command.application.service;

import com.ohgiraffers.adregamdi.data.command.application.dto.DataDTO;
import com.ohgiraffers.adregamdi.place.command.domain.aggregate.entity.Place;
import com.ohgiraffers.adregamdi.place.command.domain.aggregate.vo.CategoryVO;
import com.ohgiraffers.adregamdi.place.command.domain.aggregate.vo.CityVO;
import com.ohgiraffers.adregamdi.place.command.domain.aggregate.vo.CoordinateVO;
import com.ohgiraffers.adregamdi.place.command.domain.aggregate.vo.DongVO;
import com.ohgiraffers.adregamdi.place.command.domain.repository.PlaceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PlaceService {

    private final PlaceRepository placeRepository;

    @Autowired
    public PlaceService(PlaceRepository placeRepository) {
        this.placeRepository = placeRepository;
    }

    public Long insertPlace(DataDTO dataDTO) {
        Place result = placeRepository.save(new Place(
                dataDTO.getPlaceName(),
                new CategoryVO(dataDTO.getCategoryNo()),
                new CityVO(dataDTO.getCityNo()),
                new DongVO(dataDTO.getDongCode()),
                dataDTO.getPlaceIntroduction(),
                dataDTO.getPhoneNo(),
                new CoordinateVO(dataDTO.getLat(), dataDTO.getLng()),
                dataDTO.getPostCode(),
                dataDTO.getAddress(),
                dataDTO.getRoadAddress(),
                dataDTO.getImagePath(),
                dataDTO.getThumbnailPath(),
                0,
                0
        ));
        return result.getPlaceNo();
    }

}
