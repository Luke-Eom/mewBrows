package com.book.mew.chartRecord.service;

import com.book.mew.chartRecord.dto.ChartFormatResponse;
import com.book.mew.chartRecord.dto.ChartResponse;
import com.book.mew.chartRecord.entity.*;
import com.book.mew.chartRecord.exceptions.ChartRecordNotFoundException;
import com.book.mew.chartRecord.repository.*;
import com.book.mew.surgeryType.enums.SurgeryTypes;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.annotations.NotFound;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@Service
@RequiredArgsConstructor
public class ChartService {

    private final ChartRepository chartRepo;
    private final RecordRepository recordRepo;
    private final EyelashChartRepository elRepo;
    private final BrowsChartRepository bRepo;
    private final SmpChartRepository sRepo;

    // 차트 포맷 생성
    // surgeryType param으로 받아와서  recordRequest
    // if(schedule.surgeryType.name == @@Chart) {}
    // 처음 기록 생성할 때에 미리 생성
    public ChartFormatResponse createRecordFormat(Long recordId) {

        Record record = recordRepo.findById(recordId)
                .orElseThrow(() -> new ChartRecordNotFoundException("",""));

        Chart chart;

        String formatType;

        SurgeryTypes surgeryType = record.getSchedule().getSurgeryType();

        if(surgeryType == surgeryType.EYEBROWS) {

            chart = BrowsChart.builder()
                    .build();

            formatType = "eyebrows";

        } else if (surgeryType == surgeryType.EYELASH) {

            chart = EyelashChart.builder().build();

            formatType = "eyelash";

        } else if (surgeryType == surgeryType.SMP) {

            chart = SmpChart.builder().build();

            formatType = "smp";

        } else {
            throw new ChartRecordNotFoundException("","");
        }

        chartRepo.save(chart);


        return ChartFormatResponse.builder()
                .chartId(chart.getId())
                .surgeryType(formatType)
                .build();

    }

    public ChartResponse getChartImg(Long recordId) {

        Chart chart = chartRepo.findById(recordId)
                .orElse(null);

        if(chart.getCaptureImgUrl() == null) {

            String msg = "저장된 차트가 없습니다. 차트를 생성하고 저장해주세요.";

            return ChartResponse.builder()
                    .msg(msg)
                    .build();

        }

        return ChartResponse.builder()
                .msg("저장된 차트 이미지 보기")
                .captureUrl(chart.getCaptureImgUrl())
                .build();

    }

    public ChartResponse saveChart(Long recordId) {

        Record record = recordRepo.findById(recordId)
                .orElseThrow(() -> new ChartRecordNotFoundException("",""));

        Chart chart;
        Map<String, Object> format;

        SurgeryTypes surgeryType = record.getSchedule().getSurgeryType();

        if(surgeryType == surgeryType.EYEBROWS) {
            Map<String, Object> browsData = new HashMap<>();

            //populate browsData
            browsData.put("key1", "value1");
            browsData.put("key2", "value2");

            format = browsData;

            chart = BrowsChart.builder()
                    .browsData(browsData)
                    .build();


        } else if (surgeryType == surgeryType.EYELASH) {
            Map<String, Object> eyelashData = new HashMap<>();

            //populate eyelashData

            format = eyelashData;

            chart = EyelashChart.builder().build();

        } else if (surgeryType == surgeryType.SMP) {
            Map<String, Object> smpData = new HashMap<>();

            //populate smpData

            format = smpData;

            chart = SmpChart.builder().build();

        } else {
            throw new ChartRecordNotFoundException("","");
        }

        chartRepo.save(chart);


        return ChartResponse.builder().build();

    }

    public ChartResponse deleteChart(Long recordId) {

        return ChartResponse.builder().build();

    }


    // 기록 조회
    //  처음에

}
