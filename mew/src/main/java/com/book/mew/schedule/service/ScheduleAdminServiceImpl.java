package com.book.mew.schedule.service;

import com.book.mew.schedule.dto.ScheduleRegisterResponse;
import com.book.mew.schedule.dto.ScheduleRequest;
import com.book.mew.schedule.dto.ScheduleResponse;
import com.book.mew.schedule.entity.Schedule;
import com.book.mew.schedule.enums.Status;
import com.book.mew.schedule.repository.ScheduleRepository;
import com.book.mew.user.dto.LoginResponse;
import com.book.mew.user.entity.User;
import com.book.mew.user.exceptions.NotFoundException;
import com.book.mew.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;

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

    // READ (find -> Response로 매핑)

    // 예약 승인 조회
    public ArrayList<ScheduleResponse> selectConfirmedSchedules() {
        ArrayList<Schedule> schedules = (ArrayList<Schedule>) scheduleRepo.findAll();
        ArrayList<ScheduleResponse> scheduleList = new ArrayList<>(schedules.stream()
                .filter(schedule -> schedule.getScheduleTime().isAfter(LocalDateTime.now()))
                .filter(schedule -> schedule.getStatus() == Status.CONFIRM)
                .sorted(Comparator.comparing(Schedule::getScheduleTime))
                .map(Schedule::toDto)
                .collect(Collectors.toList()));

        return scheduleList;

    }

    // 예약 대기 조회
    public ArrayList<ScheduleResponse> selectComfirmWaitSchedules() {
        ArrayList<Schedule> schedules = (ArrayList<Schedule>) scheduleRepo.findAll();
        ArrayList<ScheduleResponse> scheduleList = new ArrayList<>(schedules.stream()
                .filter(schedule -> schedule.getScheduleTime().isAfter(LocalDateTime.now()))
                .filter(schedule -> schedule.getStatus()== Status.CONFIRM_WAIT)
                .sorted(Comparator.comparing(Schedule::getScheduleTime))
                .map(Schedule::toDto)
                .collect(Collectors.toList()));

        return scheduleList;

    }

    // 취소 대기 조회
    public ArrayList<ScheduleResponse> selectCancelWaitSchedules() {
        ArrayList<Schedule> schedules = (ArrayList<Schedule>) scheduleRepo.findAll();
        ArrayList<ScheduleResponse> scheduleList = new ArrayList<>(schedules.stream()
                .filter(schedule -> schedule.getScheduleTime().isAfter(LocalDateTime.now()))
                .filter(schedule -> schedule.getStatus() == Status.CANCEL_WAIT)
                .sorted(Comparator.comparing(Schedule::getScheduleTime))
                .map(Schedule::toDto)
                .collect(Collectors.toList()));

        return scheduleList;

    }

    // UserService 로 추후 변경 (User - List<Schedule>)
    // param
    // user 검색해서 예약 조회 (param)
    public ArrayList<ScheduleResponse> selectScheduleByUserName(Long id) throws Throwable {
        ArrayList<Schedule> schedules = (ArrayList<Schedule>) scheduleRepo.findByUserId(id).orElseThrow(() -> new NotFoundException("ERROR_404","정보가 없습니다"));
        ArrayList<ScheduleResponse> scheduleList = new ArrayList<>(schedules.stream()
                .filter(schedule -> schedule.getScheduleTime().isAfter(LocalDateTime.now()))
                .sorted(Comparator.comparing(Schedule::getScheduleTime))
                .map(Schedule::toDto)
                .collect(Collectors.toList()));

        return scheduleList;

    }

    // 예약 생성
    // param (default status = CONFIRM_WAIT)
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


}


