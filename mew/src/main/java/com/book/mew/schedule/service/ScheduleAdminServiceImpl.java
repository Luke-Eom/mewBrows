package com.book.mew.schedule.service;

import com.book.mew.schedule.dto.ScheduleRegisterRequest;
import com.book.mew.schedule.dto.ScheduleRegisterResponse;
import com.book.mew.schedule.dto.ScheduleRequest;
import com.book.mew.schedule.dto.ScheduleResponse;
import com.book.mew.schedule.entity.Schedule;
import com.book.mew.schedule.enums.Status;
import com.book.mew.schedule.repository.ScheduleRepository;
import com.book.mew.surgeryType.enums.SurgeryTypes;
import com.book.mew.user.dto.UserRegisterRequest;
import com.book.mew.user.entity.User;
import com.book.mew.user.exceptions.UserNotFoundException;
import com.book.mew.user.repository.UserRepository;
import com.book.mew.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ScheduleAdminServiceImpl implements ScheduleService {

    private final ScheduleRepository scheduleRepo;
    private final UserRepository userRepo;
    private final UserService userService;

    // READ (find -> Response 로 매핑)

        // 예약 승인 조회
    public List<ScheduleResponse> selectConfirmedSchedules() {

        List<Schedule> schedules = scheduleRepo.findAll();

        return schedules.stream()
                .filter(schedule -> schedule.getScheduleTime().isAfter(LocalDateTime.now()))
                .filter(schedule -> schedule.getStatus() == Status.CONFIRM)
                .sorted(Comparator.comparing(Schedule::getScheduleTime))
                .map(Schedule::toDto).collect(Collectors.toList());

    }

        // 예약 대기 조회
    public List<ScheduleResponse> selectConfirmWaitSchedules() {

        List<Schedule> schedules = scheduleRepo.findAll();

        return schedules.stream()
                .filter(schedule -> schedule.getScheduleTime().isAfter(LocalDateTime.now()))
                .filter(schedule -> schedule.getStatus() == Status.CONFIRM_WAIT)
                .sorted(Comparator.comparing(Schedule::getScheduleTime))
                .map(Schedule::toDto).collect(Collectors.toList());

    }

        // 취소 대기 조회
    public List<ScheduleResponse> selectCancelWaitSchedules() {

        List<Schedule> schedules = scheduleRepo.findAll();

        return schedules.stream()
                .filter(schedule -> schedule.getScheduleTime().isAfter(LocalDateTime.now()))
                .filter(schedule -> schedule.getStatus() == Status.CANCEL_WAIT)
                .sorted(Comparator.comparing(Schedule::getScheduleTime))
                .map(Schedule::toDto).collect(Collectors.toList());

    }

        // 취소 확정 조회
    public List<ScheduleResponse> selectCancelConfirmSchedules() {

        List<Schedule> schedules = scheduleRepo.findAll();

        return schedules.stream()
                .filter(schedule -> schedule.getScheduleTime().isAfter(LocalDateTime.now()))
                .filter(schedule -> schedule.getStatus() == Status.CANCEL_CONFIRM)
                .sorted(Comparator.comparing(Schedule::getScheduleTime))
                .map(Schedule::toDto).collect(Collectors.toList());

    }

    // UserService 로 추후 변경 (User - List<Schedule>)
    // param
    // user 검색해서 예약 조회 (param)
    public List<ScheduleResponse> selectScheduleByUserName(Long id) {

        List<Schedule> schedules = scheduleRepo.findByUserId(id);

        return schedules.stream()
                .filter(schedule -> schedule.getScheduleTime().isAfter(LocalDateTime.now()))
                .sorted(Comparator.comparing(Schedule::getScheduleTime))
                .map(Schedule::toDto).collect(Collectors.toList());

    }

    // 설정한 날짜 범위 내의 예약들 확인 filter

    // 예약 생성
    // user가 등록되어있지 않다면 user 등록후 예약생성
    // param (default status = CONFIRM_WAIT)
    @Transactional
    public ScheduleRegisterResponse insertSchedule(ScheduleRegisterRequest request) {

        if(userRepo.findByUserNameAndPhoneNumber(request.getUsername(), request.getPhoneNumber()).isEmpty()){
            userService.registerUser(UserRegisterRequest.builder()
                            .userName(request.getUsername())
                            .birthDate(request.getBirthDate())
                            .phoneNumber(request.getPhoneNumber())
                            .build());
            System.out.println("회원 등록");
        }

        User user = userRepo.findByUserNameAndPhoneNumber(request.getUsername(), request.getPhoneNumber())
                .orElseThrow(() -> new UserNotFoundException("ERROR_404", "회원 정보를 찾을 수 없습니다"));

        LocalDateTime scheduleTime = LocalDateTime.parse(request.getScheduleTime(), DateTimeFormatter.ISO_DATE_TIME);

        Schedule schedule = scheduleRepo.save(
                Schedule.builder()
                        .user(user)
                        .surgeryType(convertStringToSurgeryType(request.getSurgeryType()))
                        .scheduleTime(scheduleTime)
                        .status(Status.CONFIRM)
                        .build());

        System.out.println("예약 저장");

        return ScheduleRegisterResponse.builder()
                .id(schedule.getId())
                .userName(user.getUserName())
                .build();
    }

    // 예약 수정
    // (관리자 - 승인 status(CONFIRM_WAIT->CONFIRM))
    // if(getStatus == Status.CONFIRM_WAIT), (updateRequestDto)
    // {scheduleId}
    @Transactional
    public void confirmSchedule(Long scheduleId) {
        Optional<Schedule> optionalSchedule = scheduleRepo.findById(scheduleId);

        // should i simplify it?
        if (optionalSchedule.isPresent()) {
            Schedule schedule = optionalSchedule.get();
            if(schedule.getStatus().equals(Status.CONFIRM_WAIT)) {
                schedule.updateStatus(Status.CONFIRM);

            }
        } else {
            throw new UserNotFoundException("ERROR404","Schedule not found");
        }

        // 기존 생성 시 ScheduleRegisterResponse 존재 -> not necessary?
        // return 수정된 정보

    }

    // 취소 대기 - 취소
    @Transactional
    public void cancelSchedule(Long scheduleId) {
        Optional<Schedule> optionalSchedule = scheduleRepo.findById(scheduleId);

        if (optionalSchedule.isPresent()) {
            Schedule schedule = optionalSchedule.get();
            if(schedule.getStatus().equals(Status.CANCEL_WAIT)) {
                schedule.updateStatus(Status.CANCEL_CONFIRM);

            }
        } else {
            throw new UserNotFoundException("ERROR404","Schedule not found");
        }

    }


    // 예약 삭제
    // (관리자 - 승인 status(CANCEL_WAIT-> delete schedule()))
    // if (getStatus == Status.CANCEL_WAIT)

    // enum type convert method
    private SurgeryTypes convertStringToSurgeryType(String surgeryTypeStr) {

        switch (surgeryTypeStr) {
            case "반영구 눈썹":
                return SurgeryTypes.EYEBROWS;
            case "속눈썹":
                return SurgeryTypes.EYELASH;
            case "SMP 두피":
                return SurgeryTypes.SMP;
            default:
                throw new IllegalArgumentException("Invalid surgery type: " + surgeryTypeStr);

        }
    }



}


