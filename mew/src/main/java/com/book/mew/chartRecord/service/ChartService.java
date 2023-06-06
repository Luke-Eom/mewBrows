package com.book.mew.chartRecord.service;

import com.book.mew.chartRecord.dto.ChartFormatResponse;
import com.book.mew.chartRecord.dto.ChartResponse;
import com.book.mew.chartRecord.entity.Record;
import com.book.mew.chartRecord.entity.Chart;
import com.book.mew.chartRecord.exceptions.ChartRecordNotFoundException;
import com.book.mew.chartRecord.repository.*;
import com.book.mew.surgeryType.enums.SurgeryTypes;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

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

        Chart chart = new Chart();

        SurgeryTypes surgeryType = record.getSchedule().getSurgeryType().getSurgeryType();

        if(surgeryType == surgeryType.EYEBROWS) {


        } else if (surgeryType == surgeryType.EYELASH) {



        } else if (surgeryType == surgeryType.SMP) {



        }


        return ChartFormatResponse.builder()
                .chartId(chart.getId())

                .build();

    }

    public ChartResponse getRecord(Long recordId) {

        Chart chart = chartRepo.findById(recordId)
                .orElse(null);

        if(chart == null) {
            String msg = "기록이 없습니다. 기록 생성 버튼을 눌러주세요.";

            return ChartResponse.builder()
                    .msg(msg)
                    .build();

        }

        return ChartResponse.builder()
                .captureUrl(chart.getCaptureImgUrl())
                .build();

    }

    public ChartResponse saveRecord() {



        return ChartResponse.builder().build();

    }

    public ChartResponse deleteRecord(Long recordId) {

        return ChartResponse.builder().build();

    }


    // 기록 조회
    //  처음에

}
