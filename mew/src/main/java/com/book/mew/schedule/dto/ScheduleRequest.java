package com.book.mew.schedule.dto;

import com.book.mew.surgeryType.entity.SurgeryType;
import lombok.Getter;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Getter
public class ScheduleRequest {
    private String user;
    private SurgeryType surgeryType;
    private LocalDateTime scheduleTime;
    private String status;

}
