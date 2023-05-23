package com.book.mew.schedule.service;

import com.book.mew.schedule.dto.ScheduleRegisterResponse;
import com.book.mew.schedule.dto.ScheduleRequest;
import com.book.mew.schedule.dto.ScheduleResponse;
import com.book.mew.schedule.entity.Schedule;
import com.book.mew.schedule.enums.Status;
import com.book.mew.schedule.repository.ScheduleRepository;
import com.book.mew.user.entity.User;
import com.book.mew.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class ScheduleAdminServiceImpl implements ScheduleService {
    private final ScheduleRepository scheduleRepo;
    private final UserRepository userRepo;

    // READ (find -> Response 로 매핑)

    // 예약 승인 조회
    public List<ScheduleResponse> selectConfirmedSchedules() {
        List<Schedule> schedules = scheduleRepo.findAll();

        return new ArrayList<>(schedules.stream()
                .filter(schedule -> schedule.getScheduleTime().isAfter(LocalDateTime.now()))
                .filter(schedule -> schedule.getStatus() == Status.CONFIRM)
                .sorted(Comparator.comparing(Schedule::getScheduleTime))
                .map(Schedule::toDto)
                .collect(Collectors.toList()));

    }

    // 예약 대기 조회
    public List<ScheduleResponse> selectConfirmWaitSchedules() {
        List<Schedule> schedules = scheduleRepo.findAll();

        return new ArrayList<>(schedules.stream()
                .filter(schedule -> schedule.getScheduleTime().isAfter(LocalDateTime.now()))
                .filter(schedule -> schedule.getStatus()== Status.CONFIRM_WAIT)
                .sorted(Comparator.comparing(Schedule::getScheduleTime))
                .map(Schedule::toDto)
                .collect(Collectors.toList()));

    }

    // 취소 대기 조회
    public List<ScheduleResponse> selectCancelWaitSchedules() {
        List<Schedule> schedules = scheduleRepo.findAll();

        return new ArrayList<>(schedules.stream()
                .filter(schedule -> schedule.getScheduleTime().isAfter(LocalDateTime.now()))
                .filter(schedule -> schedule.getStatus() == Status.CANCEL_WAIT)
                .sorted(Comparator.comparing(Schedule::getScheduleTime))
                .map(Schedule::toDto)
                .collect(Collectors.toList()));

    }

    // 취소 확정 조회
    public List<ScheduleResponse> selectCancelConfirmSchedules() {
        List<Schedule> schedules = scheduleRepo.findAll();

        return new ArrayList<>(schedules.stream()
                .filter(schedule -> schedule.getScheduleTime().isAfter(LocalDateTime.now()))
                .filter(schedule -> schedule.getStatus() == Status.CANCEL_CONFIRM)
                .sorted(Comparator.comparing(Schedule::getScheduleTime))
                .map(Schedule::toDto)
                .collect(Collectors.toList()));

    }

    // UserService 로 추후 변경 (User - List<Schedule>)
    // param
    // user 검색해서 예약 조회 (param)
    public ArrayList<ScheduleResponse> selectScheduleByUserName(Long id) {
        List<Schedule> schedules = scheduleRepo.findByUserId(id);

        return new ArrayList<>(schedules.stream()
                .filter(schedule -> schedule.getScheduleTime().isAfter(LocalDateTime.now()))
                .sorted(Comparator.comparing(Schedule::getScheduleTime))
                .map(Schedule::toDto)
                .collect(Collectors.toList()));

    }

    // 설정한 날짜 범위 내의 예약들 확인 filter

    // 예약 생성
    // param (default status = CONFIRM_WAIT)
    @Transactional
    public ScheduleRegisterResponse insertSchedule(ScheduleRequest scheduleRequest) {
        Optional<User> user = userRepo.findByUserId(scheduleRequest.getUser());
        Schedule schedule = scheduleRepo.save(
                Schedule.builder()
                        .userId(user.get()) // String code 에서 user 정보 받아오기?
                        .surgeryType(scheduleRequest.getSurgeryType())
                        .scheduleTime(scheduleRequest.getScheduleTime())
                        .build()
        );

        return ScheduleRegisterResponse.builder()
                .id(schedule.getId())
                .build();
    }

    // 예약 수정
    // (관리자 - 승인 status(CONFIRM_WAIT->CONFIRM))
    // if(getStatus == Status.CONFIRM_WAIT)

    // 예약 삭제
    // (관리자 - 승인 status(CANCEL_WAIT-> delete schedule()))
    // if (getStatus == Status.CANCEL_WAIT)


}


