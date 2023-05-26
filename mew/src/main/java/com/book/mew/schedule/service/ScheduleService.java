package com.book.mew.schedule.service;

import com.book.mew.schedule.dto.ScheduleRegisterResponse;
import com.book.mew.schedule.dto.ScheduleRequest;
import com.book.mew.schedule.dto.ScheduleResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ScheduleService {
    List<ScheduleResponse> selectConfirmedSchedules();
    List<ScheduleResponse> selectConfirmWaitSchedules();
    List<ScheduleResponse> selectCancelWaitSchedules();
    List<ScheduleResponse> selectCancelConfirmSchedules();
    ScheduleRegisterResponse insertSchedule(ScheduleRequest scheduleRequest);

}
