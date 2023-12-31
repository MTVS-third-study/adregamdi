package com.ohgiraffers.adregamdi.schedule.command.application.controller;

import com.ohgiraffers.adregamdi.schedule.command.application.dto.DayAndNightResponseDTO;
import com.ohgiraffers.adregamdi.schedule.command.application.dto.DeleteScheduleDTO;
import com.ohgiraffers.adregamdi.schedule.command.application.dto.ScheduleDTO;
import com.ohgiraffers.adregamdi.schedule.command.application.service.ScheduleService;
import com.ohgiraffers.adregamdi.schedule.command.domain.aggregate.vo.ScheduleDayVO;
import com.ohgiraffers.adregamdi.user.command.application.dto.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/schedule/*")
public class ScheduleController {

    private final ScheduleService scheduleService;

    @Autowired
    public ScheduleController(ScheduleService scheduleService) {
        this.scheduleService = scheduleService;
    }

    @PostMapping("insertSchedule")
    public ResponseEntity<String> insertSchedule(HttpSession session, @RequestBody ScheduleDTO scheduleDTO){
        System.out.println("scheduleDTO = " + scheduleDTO);
        if (session.getAttribute("loginUser") == null){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("로그인 후 이용해주세요.");
        }
        Long userNo = ((UserDTO)session.getAttribute("loginUser")).getUserNo();
        scheduleDTO.setUserNo(userNo);
        String insertResult = scheduleService.insertSchedule(scheduleDTO);
        return ResponseEntity.ok(insertResult);
    }

    @GetMapping("deleteSchedule")
    public ResponseEntity<DeleteScheduleDTO> deleteSchedule(@RequestParam("scheduleNo") Long scheduleNo) {
        DeleteScheduleDTO response = new DeleteScheduleDTO();
        try {
            scheduleService.deleteSchedule(scheduleNo);
        } catch (Exception e){
            System.out.println(e.getMessage());
            response.setMessage("예기치 못한 오류가 발생하였습니다.");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
        response.setMessage("성공적으로 삭제되었습니다.");
        return ResponseEntity.ok(response);
    }

    @GetMapping("getDayAndNight")
    public ResponseEntity<DayAndNightResponseDTO> getDayAndNight(@RequestParam("startDay") String startDay,
                                                                 @RequestParam("endDay") String endDay) {
        DayAndNightResponseDTO response = new DayAndNightResponseDTO();
        try {
            ScheduleDayVO scheduleDayVO = new ScheduleDayVO(startDay, endDay);
            String dayAndNight = scheduleDayVO.getDayAndNight();
            response.setDayAndNight(dayAndNight);

            return ResponseEntity.ok(response);
        }catch (Exception e) {
            response.setMessage("예기치 못한 에러가 발생하였습니다.");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }


}