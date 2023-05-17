package com.book.mew.schedule.controller;

import com.book.mew.schedule.enums.SurgeryType;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/schedule")
@RequiredArgsConstructor

public class ScheduleController {

    // front 시술 카테고리명 리스트용
    @GetMapping("/surgery-types")
    public ResponseEntity<SurgeryType[]> getSurgeryTypes() {
        SurgeryType[] surgeryTypes = SurgeryType.values();

        return ResponseEntity.ok(surgeryTypes);
    }

}
