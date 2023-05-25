package com.book.mew.schedule.dto;

import com.book.mew.surgeryType.entity.SurgeryType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class ScheduleRegisterRequest {
    private String user;
    private SurgeryType surgeryType;
    private LocalDateTime scheduleTime;
    private String status;

}
