package com.book.mew.chartRecord.controller;

import com.book.mew.chartRecord.dto.ChartFormatResponse;
import com.book.mew.chartRecord.dto.ChartResponse;
import com.book.mew.chartRecord.service.ChartService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/chart")
@RequiredArgsConstructor
public class ChartController {

    private final ChartService cService;

    // 차트 생성
    @PostMapping("/{recordId}")
    public ResponseEntity<ChartFormatResponse> createRecordFormat(@PathVariable Long recordId) {

        ChartFormatResponse response = cService.createRecordFormat(recordId);

        return ResponseEntity.ok(response);

    }

    // 차트 조회
    @GetMapping("/{chartId}")
    public ResponseEntity<ChartResponse> getRecord(@PathVariable Long chartId) { // {recordId}로 받아오기

        ChartResponse response = cService.getChartImg(chartId);

        return ResponseEntity.ok(response);

    }

    // 차트 삭제
    @PostMapping("/{chartId}")
    public ResponseEntity<ChartResponse> deleteRecord(Long recordId) {

        ChartResponse response = cService.deleteChart(recordId);

        return ResponseEntity.ok(response);

    }



}
