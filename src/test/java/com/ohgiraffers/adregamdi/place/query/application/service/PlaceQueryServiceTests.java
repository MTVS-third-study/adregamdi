package com.ohgiraffers.adregamdi.place.query.application.service;

import com.ohgiraffers.adregamdi.place.command.application.dto.PlaceDTO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class PlaceQueryServiceTests {

    @Autowired
    private PlaceQueryService placeQueryService;

    @ParameterizedTest
    @ValueSource(strings = {"연돈", "돈가스"})
    void testSearchByKeyword(String keyword) {

        Assertions.assertDoesNotThrow(
                () -> {
                    List<PlaceDTO> places = placeQueryService.findPlaceByKeyword(keyword);
                    places.forEach(System.out::println);
                }
        );

    }

}
