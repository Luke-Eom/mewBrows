package com.book.mew.schedule.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class ScheduleRegisterRequest {
    private String username;
    private Integer birthDate;
    private Integer phoneNumber;
    private String surgeryType;
    private String scheduleTime;

}
