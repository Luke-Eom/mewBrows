package com.book.mew.schedule.dto;

import lombok.Getter;

import javax.validation.constraints.NotNull;

@Getter
public class ScheduleRequestDto {

    @NotNull
    private String code;


}
