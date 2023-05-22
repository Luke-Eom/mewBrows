package com.book.mew.schedule.entity;

import com.book.mew.schedule.dto.ScheduleResponse;
import com.book.mew.schedule.enums.Status;
import com.book.mew.surgeryType.entity.SurgeryType;
import com.book.mew.user.entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class Schedule {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @Column
    private User userId;
    @Column
    @Enumerated(EnumType.STRING)
    private SurgeryType surgeryType;
    @Column
    private LocalDateTime scheduleTime;
    @Column
    private Status status = Status.CONFIRM_WAIT;
    public ScheduleResponse toDto() {
        return ScheduleResponse.builder()
                .id(this.id.toString())
                .userName(this.userId.getUserName())
                .scheduleTime(this.scheduleTime.toString())
                .surgeryType(this.surgeryType.toString())
                .status(this.status.toString())
                .build();
    }

}
