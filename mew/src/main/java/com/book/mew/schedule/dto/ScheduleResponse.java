package com.book.mew.schedule.dto;

import com.book.mew.schedule.entity.Schedule;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ScheduleResponse {
    // 예약 회원 이름, 시술 타입, 시간
    private Long id; // for Json 형식
    private String userName;
    private String userPhoneNumber;
    private String userBirthDay;
    private String surgeryType;
    private LocalDateTime scheduleTime;
    private Long recordId;
    private String status;

    //
    public Schedule toEntity() {
        return Schedule.builder()
                .build();
    }

}
