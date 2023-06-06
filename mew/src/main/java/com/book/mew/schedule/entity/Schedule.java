package com.book.mew.schedule.entity;

import com.book.mew.chartRecord.entity.Record;
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
    @JoinColumn(name = "user_id")
    private User user;
    // enum으로 change?
    @ManyToOne
    @JoinColumn(name = "surgery_type_id")
    private SurgeryType surgeryType;

    @Column(name = "schedule_time")
    private LocalDateTime scheduleTime;

    @Column
    @Enumerated(EnumType.STRING)
    @Builder.Default
    private Status status = Status.CONFIRM_WAIT;

    // !!예약 삭제 시 기록은 남기도록 수정할것
    @OneToOne(mappedBy = "schedule", cascade = CascadeType.ALL, orphanRemoval = true)
    private Record record;

    public ScheduleResponse toDto() {
        return ScheduleResponse.builder()
                .id(this.id.toString())
                .userName(this.user.getUserName())
                .scheduleTime(this.scheduleTime.toString())
                .surgeryType(this.surgeryType.toString())
                .status(this.status.toString())
                .build();
    }

    public void updateStatus(Status status) {
        this.status = status;
    }

}
