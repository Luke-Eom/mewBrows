package com.book.mew.surgeryType.controller;

import com.book.mew.surgeryType.dto.SurgeryTypeResponse;
import com.book.mew.surgeryType.service.SurgeryTypeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
@RequestMapping("api/surgery-type")
@RequiredArgsConstructor
public class SurgeryTypeController {
    private final SurgeryTypeService stService;

    @GetMapping("/all")
    public ResponseEntity<ArrayList<SurgeryTypeResponse>> getSurgeryTypeList() {

        return ResponseEntity.ok(stService.getAllSurgeryTypes());

    }
}
