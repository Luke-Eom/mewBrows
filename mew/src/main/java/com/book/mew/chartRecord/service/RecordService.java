package com.book.mew.chartRecord.service;

import com.book.mew.chartRecord.dto.RecordInsertRequest;
import com.book.mew.chartRecord.dto.RecordResponse;
import com.book.mew.chartRecord.dto.RecordSearchRequest;
import com.book.mew.chartRecord.entity.Record;
import com.book.mew.chartRecord.exceptions.StatusException;
import com.book.mew.chartRecord.repository.RecordRepository;
import com.book.mew.schedule.entity.Schedule;
import com.book.mew.schedule.enums.Status;
import com.book.mew.schedule.repository.ScheduleRepository;
import com.book.mew.chartRecord.exceptions.ChartRecordNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class RecordService {

    private final RecordRepository recordRepo;
    private final ScheduleRepository scheduleRepo;


    // 기록 생성 (스케쥴 데이터 조인 - 해당 스케쥴id 와 1:1 매칭  == xxx 한 예약당 여러 차트)
        // schedule.status.CONFIRM 확인
    @Transactional
    public RecordResponse createSchedule(RecordInsertRequest request) {

        // front에서 id를 잘못받아왔을때 대비
        Schedule schedule = scheduleRepo.findById(request.getScheduleId())
                .orElseThrow(() -> new ChartRecordNotFoundException("예약번호 없음 오류","예약번호 없음 오류"));

        recordRepo.findById(request.getScheduleId()).ifPresent(existingChart -> {
            throw new IllegalStateException("해당 예약에 이미 차트가 존재합니다.");});

        // 에러 발생시 msg를 전달할 수 있게 front에서 설정
            if(schedule.getStatus() != Status.CONFIRM) {
                throw new StatusException("schedule.getstatus()","확정된 예약이 아닙니다. 예약 확정 후 차트를 생성해주세요");
            }

            Record record = recordRepo.save(
                    Record.builder()
                            .schedule(schedule)
                            .build());

            return RecordResponse.builder()
                    .recordId(record.getId())
                    .baImgUrl(record.getBaImgUrl())
                    .msg("차트 생성")
                    .build();

    }

    // 기록 조회 (생성과 동일)
    public RecordResponse getChart(RecordSearchRequest request) {

        Record record = recordRepo.findById(request.getId())
                .orElseThrow(() -> new ChartRecordNotFoundException("차트가 존재하지 않습니다","차트가 존재하지 않습니다"));

        return RecordResponse.builder()
                .recordId(record.getId())
                .baImgUrl(record.getBaImgUrl())
                .captureImgUrl(record.getChart().getCaptureImgUrl())
                .msg("차트 조회")
                .build();

    }

    // 기록 수정 (업데이트 1: b4&after 사진 올리기) -- repo.save()로 바꾸기
    public RecordResponse uploadBaImgUrl (RecordSearchRequest request) {

        Record record = recordRepo.findById(request.getId())
                .orElseThrow(() -> new ChartRecordNotFoundException("차트가 존재하지 않습니다", "차트가 존재하지 않습니다"));

        record.setBaImgUrl(request.getBaImgUrl());

        recordRepo.save(record);

        // 설정 경로에 폴더 사진 파일 저장!!

        return RecordResponse.builder()
                .recordId(record.getId())
                .baImgUrl(record.getBaImgUrl())
                .captureImgUrl(record.getChart().getCaptureImgUrl())
                .msg("비포 애프터 사진 업로드")
                .build();

    }

    // 기록 삭제 (데이터 값이 null이 아니면 -> check)
    public RecordResponse deleteChart (Long id) {

        Record record = recordRepo.findById(id)
                .orElseThrow(() -> new ChartRecordNotFoundException("차트가 존재하지 않습니다", "차트가 존재하지 않습니다"));

        recordRepo.delete(record);

        return null;

    }

}
