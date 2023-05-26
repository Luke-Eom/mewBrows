package com.book.mew.schedule.controller;

import com.book.mew.schedule.dto.ScheduleRegisterResponse;
import com.book.mew.schedule.dto.ScheduleRequest;
import com.book.mew.schedule.dto.ScheduleResponse;
import com.book.mew.schedule.service.ScheduleAdminServiceImpl;
import com.book.mew.user.exceptions.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("api/schedule")
@RequiredArgsConstructor
public class ScheduleController {
    private final ScheduleAdminServiceImpl sAdminService;

    // 예약 생성
        // (고객 문의 및 대면 신청으로 관리자가 바로 등록할 경우)
    @PostMapping("/create-schedule-by-admin")
    public ResponseEntity<ScheduleRegisterResponse> createSchedule(@RequestBody @Valid ScheduleRequest scheduleRequest) {
        ScheduleRegisterResponse response = sAdminService.insertSchedule(scheduleRequest);

        return ResponseEntity.ok(response);

    }

        // (로그인한 회원이 바로 예약 신청할 경우)

    // 예약 조회

        // 전체 조회 (예약 확정 조회)
    @GetMapping("/confirmed-schedules")
    public List<ScheduleResponse> getConfirmedSchedules() {
        return sAdminService.selectConfirmedSchedules();

    }

        // 특정 일자 확인 (range)

        // 특정 회원 확인 () -> User에서

        // 예약 대기 조회
    @GetMapping("/owait-schedules")
    public List<ScheduleResponse> getConfirmWaitSchedules() {
        return sAdminService.selectConfirmWaitSchedules();

    }

        // 예약 취소 대기 조회
    @GetMapping("/xwait-schedules")
    public List<ScheduleResponse> getCancelWaitSchedules() {
        return sAdminService.selectCancelWaitSchedules();

    }

        // 예약 취소 확정 조회
    @GetMapping("/xconfirmed-schedules")
    public List<ScheduleResponse> getCancelConfirmSchedules() {
        return sAdminService.selectCancelConfirmSchedules();

    }

    // 예약 상태 수정

        // 예약 대기 -> 예약 승인
    @PutMapping("/{scheduleId}/confirm")
    public ResponseEntity<String> confirmSchedule(@PathVariable Long scheduleId) {
        try {
            sAdminService.confirmSchedule(scheduleId);
            return ResponseEntity.ok("Schedule confirmed successfully.");

        } catch (NotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());

        }
    }

        // 예약 취소 대기 -> 취소 확정 (추후 push alarm)
    @PutMapping("/{scheduleId}/cancel")
    public ResponseEntity<String> cancelSchedule(@PathVariable Long scheduleId) {
        try {
            sAdminService.cancelSchedule(scheduleId);
            return ResponseEntity.ok("Schedule canceled successfully.");

        } catch (NotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());

        }
    }


        // 예약 대기 -> 취소 (고객 문의 및 대면 신청으로 관리자가 바로 취소할 경우)

        // 예약 확정 -> 취소 (고객 문의 및 대면 신청으로 관리자가 바로 취소할 경우)

    // 예약 기록 삭제 (요구사항 q)

    // 푸쉬알람 적용시 백엔드 로직?

    // front 시술 카테고리명 리스트용 -> 시술타입 파일로
//    @GetMapping("/surgery-types")
//    public ResponseEntity<SurgeryType[]> getSurgeryTypes() {
//        SurgeryType[] surgeryTypes = SurgeryType.values();
//
//        return ResponseEntity.ok(surgeryTypes);
//    }


}
