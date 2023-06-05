package com.book.mew.chartRecord.service;

import com.book.mew.chartRecord.dto.RecordRequest;
import com.book.mew.chartRecord.dto.RecordResponse;
import com.book.mew.chartRecord.repository.BrowsRecordRepository;
import com.book.mew.chartRecord.repository.EyelashRecordRepository;
import com.book.mew.chartRecord.repository.SmpRecordRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class RecordService {

    private final EyelashRecordRepository elRepo;
    private final BrowsRecordRepository bRepo;
    private final SmpRecordRepository sRepo;

    // 기록 생성
    // surgeryType param으로 받아와서  recordRequest
    // if(schedule.surgeryType.name == @@Chart) {}
    // elif ... 2, 3   (생성)

    public RecordResponse createChart(RecordRequest request) {


        return RecordResponse.builder().build();

    }



    // 기록 조회
    //  처음에

}
