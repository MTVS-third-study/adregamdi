package com.ohgiraffers.adregamdi.place.query.application.controller;

import com.ohgiraffers.adregamdi.place.command.domain.aggregate.entity.Place;
import com.ohgiraffers.adregamdi.place.query.application.dto.PlaceDTO;
import com.ohgiraffers.adregamdi.place.query.application.service.PlaceQueryService;
import com.ohgiraffers.adregamdi.place.query.domain.entity.SearchPlace;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/placeQuery")
public class PlaceQueryController {

    private final PlaceQueryService placeQueryService;
    @Autowired
    public PlaceQueryController(PlaceQueryService placeQueryService){
        this.placeQueryService = placeQueryService;
    }

    @GetMapping("/searchPlace")
    public String searchPlace(@RequestParam("searchKeyword") String keyword, Model model){
        List<PlaceDTO> placeList = placeQueryService.findPlaceByKeyword(keyword);
        model.addAttribute("placeList", placeList);

        return "schedule";
    }
}
