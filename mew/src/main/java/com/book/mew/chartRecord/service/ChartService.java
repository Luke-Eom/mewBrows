package com.book.mew.chartRecord.service;

import com.book.mew.chartRecord.dto.ChartRequest;
import com.book.mew.chartRecord.dto.ChartResponse;
import com.book.mew.chartRecord.entity.Chart;
import com.book.mew.chartRecord.exceptions.StatusException;
import com.book.mew.chartRecord.repository.ChartRepository;
import com.book.mew.schedule.entity.Schedule;
import com.book.mew.schedule.enums.Status;
import com.book.mew.schedule.repository.ScheduleRepository;
import com.book.mew.user.exceptions.NotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class ChartService {

    private final ChartRepository chartRepo;
    private final ScheduleRepository scheduleRepo;

    // 차트 생성 (스케쥴 데이터 조인 - 해당 스케쥴id 와 1:1 매칭  == xxx 한 예약당 여러 차트)
        // schedule.status.CONFIRM 확인
    @Transactional
    public ChartResponse createSchedule(ChartRequest request) {

        String msg = "차트 생성";

        // front에서 id를 잘못받아왔을때 대비
        Schedule schedule =scheduleRepo.findById(request.getId())
                .orElseThrow(() -> new NotFoundException("schedule.id()","예약번호 없음 오류"));

        chartRepo.findById(request.getId()).ifPresent(existingChart -> {
            throw new IllegalStateException("해당 예약에 이미 차트가 존재합니다.");});

        // 에러 발생시 msg를 전달할 수 있게 front에서 설정
            if(schedule.getStatus() != Status.CONFIRM) {
                throw new StatusException("schedule.status()","확정된 예약이 아닙니다. 예약 확정 후 차트를 생성해주세요");
            }

            Chart chart = chartRepo.save(
                    Chart.builder()
                            .schedule(schedule)
                            .build());

            return ChartResponse.builder()
                    .id(chart.getId())
                    .baImgUrl(chart.getBaImgUrl())
                    .msg(msg)
                    .build();

    }

    // 차트 조회 (생성과 동일)
    public ChartResponse getChart(Long scheduleId) {

        String msg = "차트 조회";

        Chart chart = chartRepo.findById(scheduleId)
                .orElseThrow(() -> new NotFoundException("ERR_404","차트가 존재하지 않습니다"));

        return ChartResponse.builder()
                .id(chart.getId())
                .baImgUrl(chart.getBaImgUrl())
                .msg(msg)
                .build();

    }

    // 차트 수정 (업데이트 1: b4&after 사진 올리기) -- repo.save()로 바꾸기
    public ChartResponse uploadBaImgUrl (Long id, String baImgUrl) {

        String msg = "비포 애프터 사진 업로드";

        Chart chart = chartRepo.findById(id)
                .orElseThrow(() -> new NotFoundException("chart.id()", "차트가 존재하지 않습니다"));

        chart.setBaImgUrl(baImgUrl);

        return ChartResponse.builder()
                .id(id)
                .baImgUrl(baImgUrl)
                .msg(msg)
                .build();

    }

    // 차트 삭제 (데이터 값이 null이 아니면 -> check)
    public  ChartResponse deleteChart (Long id) {

        Chart chart = chartRepo.findById(id)
                .orElseThrow(() -> new NotFoundException("chart.id()", "차트가 존재하지 않습니다"));

        // chart.getRecord().



    }

}
