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
    private String birthDay;
    private String phoneNumber;
    private String surgeryType;
    private String scheduleTime;

}
