package com.book.mew.schedule.service;

import com.book.mew.schedule.dto.ScheduleRegisterResponse;
import com.book.mew.schedule.dto.ScheduleRequest;
import com.book.mew.schedule.dto.ScheduleResponse;

import java.util.List;

public class ScheduleMemberServiceImpl implements ScheduleService{
    @Override
    public List<ScheduleResponse> selectConfirmedSchedules() {
        return null;
    }

    @Override
    public List<ScheduleResponse> selectConfirmWaitSchedules() {
        return null;
    }

    @Override
    public List<ScheduleResponse> selectCancelWaitSchedules() {
        return null;
    }

    @Override
    public List<ScheduleResponse> selectCancelConfirmSchedules() {
        return null;
    }

    @Override
    public ScheduleRegisterResponse insertSchedule(ScheduleRequest scheduleRequest) {
        return null;
    }
}
