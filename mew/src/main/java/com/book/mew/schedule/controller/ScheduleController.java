package com.book.mew.schedule.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/schedule")
@RequiredArgsConstructor

public class ScheduleController {

    // front 시술 카테고리명 리스트용 -> 시술타입 파일로
//    @GetMapping("/surgery-types")
//    public ResponseEntity<SurgeryType[]> getSurgeryTypes() {
//        SurgeryType[] surgeryTypes = SurgeryType.values();
//
//        return ResponseEntity.ok(surgeryTypes);
//    }

}
